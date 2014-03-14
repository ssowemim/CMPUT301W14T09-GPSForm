package ca.cmput301w14t09;



import java.util.Date;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Controller.PictureController;
import ca.cmput301w14t09.FileManaging.CreateComment;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import ca.cmput301w14t09.model.Comment;
import ca.cmput301w14t09.model.CommentThread;
import ca.cmput301w14t09.model.GeoLocation;
import ca.cmput301w14t09.model.PictureModelList;
import ca.cmput301w14t09.model.User;
import ca.cmput301w14t09.view.PictureAdapter;


public class TopCommentsActivity extends Activity implements OnClickListener{
	
	public static final int OBTAIN_PIC_REQUEST_CODE = 117;
	
	protected Intent intent;
	protected User user;
	protected Dialog dialog;
	protected ListView aCommentList;
	Comment comment;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_top_comments);

		aCommentList = (ListView) findViewById(R.id.aCommentList);

		aCommentList.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

				String getCommentText = (String) (aCommentList.getItemAtPosition(arg2)); 

				try {
					comment = ElasticSearchOperations.loadComment(getCommentText);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				// Pass in comment object
				commentThread(comment);


			}

		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.top_comments, menu);

		intent = getIntent();
		user = (User) intent.getSerializableExtra("CURRENT_USER");	
		
		
		return true;


	}

	@Override
	protected void onStart(){
		super.onStart();
		String[] topComments;
		try {
			topComments = ElasticSearchOperations.pullThreads();
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					R.layout.list_view, topComments);
			aCommentList.setAdapter(adapter);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void popUp(View v){

		dialog = new Dialog(this);

		dialog.setContentView(R.layout.pop_up_comment);
		dialog.setTitle("New Top Comment");

		final EditText authorText=(EditText)dialog.findViewById(R.id.authorText);
		final EditText commentText=(EditText)dialog.findViewById(R.id.commentText);
		final EditText tv2 = (EditText)dialog.findViewById(R.id.longtext3);
		final EditText tv3 = (EditText)dialog.findViewById(R.id.lattext3);
		
		//new Location Controller 
		final LocationController lc = new LocationController();
		
		//get Location Manager setup

		//lc.setLocationManager(dialog.getContext());
	
		LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
						
			
	//	addPicImageView = (ImageView)this.findViewById(R.id.add_pic_image_view);
		//System.out.println(user.getAuthorName());
		
		authorText.setText(user.getProfile().getAuthorName());
		Button save=(Button)dialog.findViewById(R.id.save);
		Button btnCancel=(Button)dialog.findViewById(R.id.cancel);
		//update location button
		Button btnSimple2 = (Button)dialog.findViewById(R.id.changebutton);
		
		// ImageView img = (ImageView) findViewById(R.id.add_pic_image_view);
		ImageButton img = (ImageButton) dialog.findViewById(R.id.imageButton1);
		img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		//		Intent intent = new Intent(TopCommentsActivity.this, PictureActivity.class);
			//	startActivity(intent);
				
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, OBTAIN_PIC_REQUEST_CODE);
				
				Toast.makeText(TopCommentsActivity.this, "Image has been changed", Toast.LENGTH_LONG).show();
			}
			
			
		});  
		
		dialog.show();
		
		// Retrieve location updates through LocationListener interface
		LocationListener locationListener = new LocationListener(){				
								
				public void onProviderDisabled (String provider){
									
					}
								
				public void onProviderEnabled (String provider){
									
									
					}
								
				public void onStatusChanged (String provider, int status, Bundle extras){
									
									
					}

				@Override
				public void onLocationChanged(android.location.Location location) {
									
				    lc.locationchanged(location, tv2, tv3);
									
									
					}
				};
				
				//set up location update request.
				//lc.requestLocationUpdates(locationListener);
		
		// Retrieve location updates through LocationListener interface
				
						
						//set up location update request.
						
		
						dialog.show();
		
		
					//lc.requestLocationUpdates(locationListener);
					lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
			
				
				//update location button
				btnSimple2.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
							
						
						lc.updatelocation(dialog.getContext(),tv2, tv3);
						
						 
					}
			 });
		
		
		//cancel button
		btnCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();

			}
		});

		//save button
		save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String text1 = commentText.getText().toString();
				String text2 = authorText.getText().toString();
				user.getProfile().setAuthorName(text2);
				comment = CreateComment.newComment(lc, text2, text1, true);
				
				CommentThread newThread = new CommentThread();
				newThread.addToThread(comment);
				newThread.setName(comment.getCommentText());
				newThread.setLastUpdated(comment.getPostDate());
				
				ElasticSearchOperations.postThread(newThread);
				dialog.dismiss();

			}
		});

	}

	public void viewFavorites(View v){
		if(user.getUserName().equals("Guest")){

			dialog = new Dialog(this);
			dialog.setContentView(R.layout.guest_box);
			dialog.setTitle("ALERT!");

			Button button =(Button)dialog.findViewById(R.id.favorite1);
			dialog.show();
			button.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					dialog.dismiss();

				}
			});
		}

		else{
			Intent intent = new Intent(this, FavoritesActivity.class);
			startActivity(intent);
		}
	}

	// Sends comment object to new activity
	public void commentThread(Comment comment){
		Intent intent = new Intent(this, CommentListActivity.class);
		startActivity(intent);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}


	
	

	
}