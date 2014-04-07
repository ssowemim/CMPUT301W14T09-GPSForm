/**

License GPLv3: GNU GPL Version 3
<http://gnu.org/licenses/gpl.html>.
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package ca.cmput301w14t09;

import java.util.ArrayList;
import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Controller.SortingController;
import ca.cmput301w14t09.FileManaging.FileSaving;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.GeoLocation;
import ca.cmput301w14t09.Model.ThreadAdapter;
import ca.cmput301w14t09.Model.User;
import ca.cmput301w14t09.Model.UserProfileModel;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import ca.cmput301w14t09.elasticSearch.Server;
import com.mapquest.android.Geocoder;
import com.mapquest.android.maps.AnnotationView;
import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.MyLocationOverlay;


/**
 * 
 * @author ssowemim, Conner, Cameron, Chun-Han
 * TopCommentsActivity handles all the functions that the pop_up_comment.xml has to offer.
 * Controlling the longitude & latitude of a comment by receiving information from the ChooseLocationActivity.
 * Attaching a picture to a comment.
 * Most of the code referring to handling the picture is taken from
 * http://www.androidhive.info/2013/09/android-working-with-camera-api/
 * Making a comment with an author.
 *
 */

public class TopCommentsActivity extends ListActivity {
	public static final int OBTAIN_PIC_REQUEST_CODE = 117;
	public static final int FAVORITE_LIST = 42;

	protected Intent intent;
	protected User user;
	protected Dialog dialog;
	protected ListView aCommentList;
	protected Uri fileUri;

	protected PopUpComment popUpComment = new PopUpComment(this);

	protected ThreadAdapter adapter1;

	//selected geolocation object used for when person selects geolocation and sortbyselectedgeolocation
	GeoLocation selectedgeo = new GeoLocation();
	GeoLocation selectedgeosort = new GeoLocation();

	final LocationController lc1 = new LocationController();

	//initialize variables for map 
	protected MapView map;
	private MyLocationOverlay myLocationOverlay;
	AnnotationView annotation;
	GeoPoint currentLocation;
	int id = 0;
	Geocoder code = null;
	Context context = null;

	private Handler  updateHandler;
	private Runnable updateFunction;

	public enum Filter {
		DATE, PICTURE, LOCATION, DIFFLOCATION, NONE;
	}
	private Filter filter = Filter.DATE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_top_comments);
		context = getApplicationContext();

		aCommentList = (ListView) findViewById(android.R.id.list);

		aCommentList.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				customOptionsDialog(arg2);
			}
		});

		//setup map
		setupMapView();
		setupMyLocation();


		// Handler polling
		updateHandler = new Handler();
		updateFunction = new Runnable() {
			public void run() {
				populateListView();
			}
		};

		Thread update = new Thread() {
			public void run() {
				while(true) {
					try {
						updateHandler.post(updateFunction);
						sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
				}
			}
		}; 

		update.start();
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.top_comments, menu);

		intent = getIntent();
		user = (User) intent.getSerializableExtra("CURRENT_USER");
		populateListView();
		return true;
	}

	/**
	 * 
	 * @author Chunhan
	 * Allows for selection of sorting on the action bar
	 * https://developer.android.com/training/basics/actionbar/adding-buttons.html
	 * 
	 */
	@Override 
	public boolean onOptionsItemSelected(MenuItem item){

		boolean sorted = true;

		switch (item.getItemId()) {
			case R.id.userProfile:
				Intent intent = new Intent(this, UserProfileActivity.class);
				intent.putExtra("CURRENT_USER", user);   
				startActivity(intent);
				break;

			case R.id.sortLocation:     
				sortByLocation();
				break;
			case R.id.sortDate:
				sortByDate();
				break;
			case R.id.sortPicture:
				sortByPicture();
				break;	
			case R.id.sortByDiffLocation:
				sortByDiffLocation();
				break;
			default:
				filter = Filter.NONE;
				sorted = false;
				break;
		}

		return sorted;
	}

	/**
	 * Sorts the list by location.
	 */
	private void sortByLocation() {
		SortingController sorting = new SortingController();
		filter = Filter.LOCATION;

		setupMyLocation();
		ArrayList<Comment> sortedList = sorting.sortTopComments(lc1, null, user.profile.cache.getTopComments(true));
		adapter1 = new ThreadAdapter(this,R.layout.thread_view, sortedList);
		aCommentList.setAdapter(adapter1);
		adapter1.notifyDataSetChanged();
	}

	/**
	 * Sorts the list by post-date.
	 */
	private void sortByDate() {
		filter = Filter.DATE;

		adapter1 = new ThreadAdapter(this,R.layout.thread_view, user.profile.cache.getTopComments(true));
		aCommentList.setAdapter(adapter1);
		adapter1.notifyDataSetChanged();
	}

	/**
	 * Sorts the list by picture.
	 */
	private void sortByPicture() {
		SortingController sorting1 = new SortingController();
		filter = Filter.PICTURE;

		ArrayList<Comment> commentList = null;
		commentList = user.profile.cache.getTopComments(true);
		commentList = sorting1.sortPicTopComments(commentList);
		adapter1 = new ThreadAdapter(this,R.layout.thread_view, commentList);
		aCommentList.setAdapter(adapter1);
		adapter1.notifyDataSetChanged();	
	}

	/**
	 * Sorts the list by a different location.
	 */
	private void sortByDiffLocation() {
		filter = Filter.DIFFLOCATION;

		Intent intentdiff = new Intent(getApplicationContext(), ChooseLocationActivity.class);
		startActivityForResult(intentdiff, 123);
	}

	/**
	 * re-applies filter to results brought back from poll.
	 */
	private void reapplyFilter() {
		switch (filter) {
			case LOCATION:     
				sortByLocation();
				break;
			case DATE:
				sortByDate();
				break;
			case PICTURE:
				sortByPicture();
				break;
			default:
				filter = Filter.NONE;
				sortByDate();
				sortByLocation();
				break;
		}
	}

	/**
	 * onResume populates the listview with results from
	 * elasticSearch, finding all of the top comments
	 * @param thread
	 */
	@Override
	public void onResume() {
		super.onResume();
	}


	/**
	 * When a user wants to make a new comment this method is called, which in turn
	 * calls the class popUpCommnt/
	 * @param v
	 * @throws InterruptedException
	 */
	public void popUp(View v) throws InterruptedException {
		setupMyLocation();
		popUpComment.popUp(v, this, fileUri, lc1, user, "New Top Comment");
	}


	/**
	 * This pulls all the threads from server and puts them all into a list.
	 * This list is now what is being referred too than calling from elasticOprations all 
	 * the time.
	 */
	public void populateListView() {
		ArrayList<Comment> topComments = null;
		if(user != null) {
			if(Server.getInstance().isServerReachable(this)) {
				try {
					topComments = ElasticSearchOperations.pullThreads();

					user.profile.cache.add(topComments);
					
					FileSaving.saveUserFile(user, this);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			topComments = user.profile.cache.getTopComments(true);
			adapter1 = new ThreadAdapter(this,
					R.layout.thread_view,
					user.profile.cache.getTopComments(true));
			aCommentList.setAdapter(adapter1);

			reapplyFilter();

			adapter1.notifyDataSetChanged();
		}
	}

	/**
	 * viewFavorites checks to see if you are guest or not
	 * since guest cannot have favorites.
	 * Then the method starts up the FavoritesListActivity.
	 * This activity has yet to be written
	 * @param v
	 */
	public void viewFavorites(View v) {
		if(user.getUserName().equals("Guest")) {
			Toast.makeText(getApplicationContext(),
					"Guest Can Not Have Favourites", Toast.LENGTH_LONG).show();
		}
		else {
			Intent intent = new Intent(this, FavoriteActivity.class);
			intent.putExtra("CURRENT_USER", user);   
			startActivity(intent);
		}
	}

	/**
	 * commentThread takes in thread and then starts a new
	 * activity while passing the contents of thread to the 
	 * activity
	 * @param thread
	 */
	public void commentThread(Comment thread) {
		String stringId = new String();
		stringId = thread.getThreadId();

		setupMyLocation();

		Intent intent = new Intent(this, CommentListActivity.class);
		intent.putExtra("THREAD_ID", stringId);
		intent.putExtra("CURRENT_USER", user);    
		intent.putExtra("CURRENT_LOCATION", lc1.getGeodefault());

		startActivityForResult(intent, FAVORITE_LIST);
	}

	//http://stackoverflow.com/questions/17242713/how-to-pass-parcelable-object-from-child-to-parent-activity
	public void onBackPressed() {
		finish();
	}


	/**
	 *set your map and enable default zoom controls 
	 *http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	protected void setupMapView() {
		this.map = (MapView) findViewById(R.id.map);
		map.setBuiltInZoomControls(true);
	}

	/**
	 * set up a MyLocationOverlay and execute the runnable once we have a location fix 
	 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	private void setupMyLocation() {
		this.myLocationOverlay = new MyLocationOverlay(this, map);
		myLocationOverlay.enableMyLocation();
		myLocationOverlay.runOnFirstFix(new Runnable() {
			@Override
			public void run() {
				currentLocation = myLocationOverlay.getMyLocation();
				lc1.setGeodefault(currentLocation.getLatitude(), currentLocation.getLongitude());
				map.getController().animateTo(currentLocation);
				map.getController().setZoom(14);
				map.getOverlays().add(myLocationOverlay);
				myLocationOverlay.setFollowing(true);
			}
		});
	}

	/**
	 * onSaveInstanceState stores the file url as
	 * it will be null after returning from camera app
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// save file url in bundle as it will be null on screen orientation changes
		outState.putParcelable("file_uri",fileUri);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		fileUri = savedInstanceState.getParcelable("file_uri");
	}


	/**
	 * onActivityResult will Receive the activity result
	 * method and will be called after closing the camera,
	 * after the ChooseLocationActivity is closed to get geolocation, or the commentList. this method.
	 * Used for updating selectedgeolocation
	 * //http://stackoverflow.com/questions/17242713/how-to-pass-parcelable-object-from-child-to-parent-activity
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == FAVORITE_LIST && resultCode == Activity.RESULT_OK) {
			user = (User) data.getSerializableExtra("CURRENT_USER");
		}

		if (requestCode == 123 && resultCode == Activity.RESULT_OK){
			ArrayList<Comment> topComments = null;


			selectedgeosort = (GeoLocation) data.getExtras().get("SomeUniqueKey");
			SortingController sorting2 = new SortingController();
			try {
				topComments = ElasticSearchOperations.pullThreads();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ArrayList<Comment> sortedList1 = sorting2.sortTopComments(null, selectedgeosort, topComments);
			adapter1 = new ThreadAdapter(this,R.layout.thread_view, sortedList1);
			aCommentList.setAdapter(adapter1);
			adapter1.notifyDataSetChanged();
		}
		if (requestCode == 122 && resultCode == Activity.RESULT_OK) {
			//successfully get updated selected geolocation
			selectedgeo = (GeoLocation) data.getExtras().get("SomeUniqueKey");
			Toast.makeText(this.getApplicationContext(),"Comment Location Updated.", Toast.LENGTH_LONG).show(); 
		}

		// if the result is capturing Image
		if (requestCode == OBTAIN_PIC_REQUEST_CODE) {
			fileUri = popUpComment.getFleUri();
			if (resultCode == RESULT_OK) {
				popUpComment.pictureResult(fileUri);

			} else if (resultCode == RESULT_CANCELED) {
				// user cancelled Image capture
				Toast.makeText(this.getApplicationContext(),
						"User cancelled image capture", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}


	/**
	 * This customdialog method bring up the three options of viewing attachment,
	 * viewing user profile and viewing replies.
	 * @param arg2
	 * @author ssowemim
	 */
	private void customOptionsDialog(final int arg2){
		final Comment comment = (Comment)(aCommentList.getItemAtPosition(arg2));
		
		//Creates a dialog with the 'ialog_options' layout
		final Dialog dialog = new Dialog(TopCommentsActivity.this);
		dialog.setTitle( user.getUserName()+ ": " +comment.getAuthorName().toString() + ": Options");
		dialog.setContentView(R.layout.dialog_options);

		Window window = dialog.getWindow();
		WindowManager.LayoutParams wlp = window.getAttributes();

		window.setAttributes(wlp);
		dialog.show();

		Button dialogRepliesButton = (Button)dialog.findViewById(R.id.buttonReplies);
		ImageButton dialogAttachButton = (ImageButton)dialog.findViewById(R.id.imButtonAttachment);
		ImageButton dialogProfileButton = (ImageButton)dialog.findViewById(R.id.imButtonProfile);

		//reply button takes you to the thread that shows you all the replies.
		dialogRepliesButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Comment thread = (Comment)(aCommentList.getItemAtPosition(arg2));
				commentThread(thread);
				dialog.dismiss();
			}
		});

		//Attachment button, lets you see an picture if there was one attached to the comment
		dialogAttachButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

						final Dialog dialog1 = new Dialog(TopCommentsActivity.this);
						dialog1.setTitle("Attachment");
						dialog1.setContentView(R.layout.dialog_attachment);
						dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
						dialog1.show();

						ImageView imageView = (ImageView)dialog1.findViewById(R.id.imageViewAttachment);
						Comment thread = (Comment)(aCommentList.getItemAtPosition(arg2));
						Bitmap attachment = thread.getPicture().bitmap;

						if (thread.getHasPicture()){
							attachment = Bitmap.createScaledBitmap(attachment, 500, 500, false);
							imageView.setImageBitmap(attachment);
							dialog.dismiss();
						}
						else{
							dialog.dismiss();
							dialog1.dismiss();
							Toast.makeText(getApplicationContext(),"No Attachment picture with Comment.", Toast.LENGTH_LONG).show();
						}
					}
					
				});

				dialogProfileButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						final Dialog dialog2 = new Dialog(TopCommentsActivity.this);
						dialog2.setTitle(comment.getAuthorName() + " | Profile");
						dialog2.setContentView(R.layout.dialog_user_profile);
						dialog2.show();
					}
				});

		//profile button lets you see the user names profile
		dialogProfileButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Comment thread = (Comment)(aCommentList.getItemAtPosition(arg2));
				ArrayList<UserProfileModel> userProfile = null;
				int size, lastItem;
				if (thread.getUserName().equalsIgnoreCase("guest")){
					Toast.makeText(getApplicationContext(),"Guests don't have a user profile", Toast.LENGTH_LONG).show();
				}

				else {
					try {
						userProfile = ElasticSearchOperations.pullUserProfile(thread.getUserName());

					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					//Size of how many changes have been made to that user profile
					size = userProfile.size();
					lastItem = size-1;	
					
					//Creates dialog to display all those information of the user profile
					final Dialog dialog2 = new Dialog(TopCommentsActivity.this);
					dialog2.setTitle(comment.getAuthorName() + " | Profile");
					dialog2.setContentView(R.layout.dialog_user_profile);
					dialog2.show();

					//Sets all buttons and textviews
					Button returnButton = (Button)dialog2.findViewById(R.id.button_return);
					TextView tVName = (TextView)dialog2.findViewById(R.id.textView_fLastname);
					TextView tVPhone = (TextView)dialog2.findViewById(R.id.textView_phone);
					TextView tVEmail = (TextView)dialog2.findViewById(R.id.textView_email);
					TextView tVSex = (TextView)dialog2.findViewById(R.id.textView_sex);
					ImageView iVPic = (ImageView)dialog2.findViewById(R.id.imageView_profilePicture);
					TextView tVBio = (TextView)dialog2.findViewById(R.id.textView_bio);
					Bitmap pic;

					returnButton.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							dialog2.dismiss();
						}
					});

					//if a profile exists picks the last one and displays it
					if(!(userProfile.isEmpty() || size == 0)){
						tVName.setText("Name: "+userProfile.get(lastItem).getFirstLastName());
						tVPhone.setText("Phone: "+userProfile.get(lastItem).getPhone());
						tVEmail.setText("Email: "+userProfile.get(lastItem).getEmail());
						tVSex.setText("Sex: "+userProfile.get(lastItem).getSex());
						pic = Bitmap.createScaledBitmap(userProfile.get(lastItem).getPicture(), 200, 200, false);
						iVPic.setImageBitmap(pic);
						tVBio.setText("Bio: "+userProfile.get(lastItem).getBiography());
					}
					//else just put a set of default information in the textview
					else {
						tVName.setText("Name: ");
						tVPhone.setText("Phone: ");
						tVEmail.setText("Email: ");
						tVSex.setText("Sex: ");
						tVBio.setText("Bio: ");
					}
				}


			}
		});
	}

	/**
	 * Return the selectedgeo object
	 * @author Cameron Alexander
	 * @return
	 */
	public GeoLocation getSelectedGeolocation() {
		return selectedgeo;
	}

	/**
	 * Reset selected geolocation object
	 * @author Cameron Alexander
	 */
	public void resetSelectedLocation() {
		double latitude = 0.0;
		double longitude = 0.0;
		selectedgeo.setLatitude(latitude);
		selectedgeo.setLongitude(longitude);
	}
}



