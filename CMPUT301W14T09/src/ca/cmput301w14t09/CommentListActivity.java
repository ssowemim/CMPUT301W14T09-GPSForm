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
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Controller.SortingController;
import ca.cmput301w14t09.FileManaging.FileSaving;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.CommentAdapter;
import ca.cmput301w14t09.Model.GeoLocation;
import ca.cmput301w14t09.Model.User;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import ca.cmput301w14t09.elasticSearch.Server;

import com.mapquest.android.Geocoder;
import com.mapquest.android.maps.AnnotationView;
import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.MyLocationOverlay;

/**
 * 
 * @author Conner
 * @editor ChunHan
 * This activity shows the top comment that was selected in a 
 * previous activity and displays all the replies to that comment
 * This activity shows the top comment that was selected in a 
 * previous activity and displays all the replies to that comment
 *
 * Need to send the user back as a Activity result so the favorite
 * saves to the user profile are sent back as well and favorites
 * are shown immediately
 */
public class CommentListActivity extends ListActivity {

	//Activity request codes to take pictures
	public static final int OBTAIN_PIC_REQUEST_CODE = 117;
	public static final int MEDIA_TYPE_IMAGE = 1;
	protected Intent intent;
	protected User user;
	protected Dialog dialog;
	protected ListView aCommentList;

	//initialize variables for map 
	protected MapView map;
	private MyLocationOverlay myLocationOverlay;
	AnnotationView annotation;
	GeoPoint currentLocation;
	int id = 0;
	Geocoder code = null;
	Context context = null;

	//initialize popups
	protected PopUpReply popUpReply = new PopUpReply(this);
	protected PopUpSelect popUpSelect = new PopUpSelect(this);

	//comment vars
	protected CommentAdapter adapter;
	Comment comment;
	protected ListView favList;
	protected String firstComment;

	//init handlers for  caching
	private Handler  updateHandler;
	private Runnable updateFunction;

	//File uri to store Images
	private Uri fileUri;

	//selected geolocation object used for when person selects geolocation from
	GeoLocation selectedgeo = new GeoLocation();
	GeoLocation selectedgeosort = new GeoLocation();

	//new Location Controller 
	final LocationController lc1 = new LocationController();
	
	//filter used for sorting
	public enum Filter {
		DATE, PICTURE, LOCATION, DIFFLOCATION, NONE;
	}
	private Filter filter = Filter.DATE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment_list);
		favList = (ListView) findViewById(android.R.id.list);
		final Activity commentActivity = this;

		//setup map
		setupMapView();
		setupMyLocation();

		favList.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

				Comment thread = (Comment)(favList.getItemAtPosition(arg2)); 
				optionsDialog(thread);

			}
			
		});

		// Handler polling
		updateHandler = new Handler();
		updateFunction = new Runnable() {
			@Override
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
	
	/**
	 * This function inflates the menu and adds items to the action bar if it is present.
	 * It also intialized Handler polling and populates the listview with reply comments
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.comment_list, menu);
		intent = getIntent();
		user = (User) intent.getSerializableExtra("CURRENT_USER");
		firstComment = (String) getIntent().getSerializableExtra("THREAD_ID");
	
		updateHandler = new Handler();
		updateFunction = new Runnable() {
			@Override
			public void run() {
				populateListView();
			}
		};

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
		ArrayList<Comment> topComments = null;
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

		currentLocation = myLocationOverlay.getMyLocation();
		lc1.setGeodefault(currentLocation.getLatitude(), currentLocation.getLongitude());
		ArrayList<Comment> sortedList = sorting.sortTopComments(lc1, null, user.profile.cache.getSubComments(firstComment));
		adapter = new CommentAdapter(this,R.layout.comment_view, sortedList);
		favList.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	
	/**
	 * Sorts the list by post-date.
	 */
	private void sortByDate() {
		filter = Filter.DATE;

		adapter = new CommentAdapter(this,R.layout.comment_view, user.profile.cache.getSubComments(firstComment));
		favList.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	
	/**
	 * Sorts the list by picture.
	 */
	private void sortByPicture() {
		SortingController sorting1 = new SortingController();
		filter = Filter.PICTURE;

		ArrayList<Comment> commentList = null;
		commentList = user.profile.cache.getSubComments(firstComment);
		commentList = sorting1.sortPicTopComments(commentList);
		adapter = new CommentAdapter(this,R.layout.comment_view, commentList);
		favList.setAdapter(adapter);
		adapter.notifyDataSetChanged();	
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
			//return super.onOptionsItemSelected(item);
			//sorted = false;\
			break;
		}
	}


	/**
	 * this function calls the popup reply to be generated when the reply button is pressed
	 * @param v
	 * @throws InterruptedException
	 */
	public void popUp(View v) throws InterruptedException {
		popUpReply.popUp(this, fileUri, lc1, selectedgeo, user, firstComment, "Comment Reply");
	}


	/**
	 * onStart populates the listview with results from the elasticSearch
	 *  query found in ElasticSearchOperations.pullOneThread(firstComment)
	 */
	@Override
	protected void onStart() {
		super.onStart();

	}

	/**
	 * This function is responsible for pulling the thread of comments and getting the comment replies
	 * It then populates the listview to display the replies, it reapplies the default filter to sort comments
	 * which by default is date, The filter changes if user has selected to sort by a different way.
	 */
	public void populateListView() {
		ArrayList<Comment> commentThread = null;

		if(user != null) {
			if(Server.getInstance().isServerReachable(this)) {
				try {
					commentThread = ElasticSearchOperations.pullOneThread(firstComment);
					user.profile.cache.add(commentThread);
					FileSaving.saveUserFile(user, this);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			adapter = new CommentAdapter(this,
					R.layout.comment_view,
					user.profile.cache.getSubComments(firstComment));

			favList.setAdapter(adapter);
			reapplyFilter();
			adapter.notifyDataSetChanged();
		}
	}

	/**
	 * onActivityResult will Receive the activity result method and will be called after closing 
	 * the camera and after the ChooseLocationActivity is closed to get geolocation, or the commentList. 
	 * //http://stackoverflow.com/questions/17242713/how-to-pass-parcelable-object-from-child-to-parent-activity
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 122 && resultCode == Activity.RESULT_OK){

			selectedgeo = (GeoLocation) data.getExtras().get("SomeUniqueKey");

			//Toast.makeText(getApplicationContext(),"Comment Location Updated.", Toast.LENGTH_LONG).show();
		}

		if (requestCode == 123 && resultCode == Activity.RESULT_OK){

			selectedgeosort = (GeoLocation) data.getExtras().get("SomeUniqueKey");

		}

		// if the result is capturing Image
		if (requestCode == OBTAIN_PIC_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
					popUpReply.pictureResult(fileUri);
			} else if (resultCode == RESULT_CANCELED) {
				// user cancelled Image capture
				Toast.makeText(this.getApplicationContext(),
						"User cancelled image capture", Toast.LENGTH_SHORT)
						.show();
			}
		} else {
			// failed to capture image
			Toast.makeText(this.getApplicationContext(),
					"Sorry! Failed to capture image", Toast.LENGTH_SHORT)
					.show();
		}
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


	/**
	 * This function restores the saved instance state using a file_uri
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		fileUri = savedInstanceState.getParcelable("file_uri");
	}


	/**
	 * On back pressed pack up the geolocation that was selected and send back to parent activity to be processed
	 * http://stackoverflow.com/questions/17242713/how-to-pass-parcelable-object-from-child-to-parent-activity
	 */
	public void onBackPressed() {
		Intent intent = getIntent();
		intent.putExtra("CURRENT_USER", user);
		setResult(Activity.RESULT_OK, intent);
		super.onBackPressed();
		finish();
	}

	public void optionsDialog(Comment thread) {
		popUpSelect.popUpSelect(this, fileUri, lc1, selectedgeo, user, firstComment, "Comment Reply", thread);

	}
	
	/**
	 * Return the selectedgeo object
	 * @author Cameron Alexander
	 * @return
	 */
	public GeoLocation getSelectedGeolocation(){
		return selectedgeo;
	}

	/**
	 * Reset selected geolocation object
	 * @author Cameron Alexander
	 */
	public void resetSelectedLocation(){
		double latitude = 0.0;
		double longitude = 0.0;
		selectedgeo.setLatitude(latitude);
		selectedgeo.setLongitude(longitude);

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
}
