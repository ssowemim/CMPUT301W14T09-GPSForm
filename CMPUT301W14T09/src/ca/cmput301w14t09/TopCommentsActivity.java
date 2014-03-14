package ca.cmput301w14t09;



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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Controller.PictureController;
import ca.cmput301w14t09.FileManaging.CreateComment;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import ca.cmput301w14t09.model.Comment;
import ca.cmput301w14t09.model.PictureModelList;
import ca.cmput301w14t09.model.User;
import ca.cmput301w14t09.view.PictureAdapter;

public class TopCommentsActivity extends Activity {

	public static final int OBTAIN_PIC_REQUEST_CODE = 117;
	
	protected Intent intent;
	protected User user;
	protected Dialog dialog;
	protected ListView aCommentList;
	Comment comment;
	
	ImageView addPicImageView;
	
	private Bitmap currentPicture;
	PictureController picController;
	PictureAdapter picAdapter;
	PictureModelList picModel;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_top_comments);

		aCommentList = (ListView) findViewById(R.id.aCommentList);

		aCommentList.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				String getCommentText = (String) (aCommentList.getItemAtPosition(arg2)); 

				try {
					comment = ElasticSearchOperations.loadComment(getCommentText);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				commentThread(comment);


			}

		});
		
		this.picModel = new PictureModelList();
		this.picAdapter = new PictureAdapter(this, R.layout.pic_post, picModel.getList());
		this.picController = new PictureController(this.picModel, this);
		
		this.picModel.setAdapter(this.picAdapter);
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode == OBTAIN_PIC_REQUEST_CODE && resultCode == RESULT_OK){
			this.currentPicture = (Bitmap)data.getExtras().get("data");
			this.addPicImageView.setImageBitmap(this.currentPicture);
		}
	}
	
	
	public void obtainPicture(View view) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, OBTAIN_PIC_REQUEST_CODE);
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
			topComments = ElasticSearchOperations.pullTopComments();
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					R.layout.list_view, topComments);
			aCommentList.setAdapter(adapter);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
						
			
		addPicImageView = (ImageView)this.findViewById(R.id.add_pic_image_view);
		
		authorText.setText(user.getAuthorName());
		Button save=(Button)dialog.findViewById(R.id.save);
		Button btnCancel=(Button)dialog.findViewById(R.id.cancel);
		//update location button
		Button btnSimple2 = (Button)dialog.findViewById(R.id.changebutton);
		
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
				user.setAuthorName(text2);
				comment = CreateComment.newComment(text2, text1, true);
				ElasticSearchOperations.postTopComment(comment);
				dialog.dismiss();

			}
		});
		
		


	}

	public void viewFavorites(View v){
		if(user.getUserName().equals("Guest")){

			dialog = new Dialog(this);
			dialog.setContentView(R.layout.guest_box);
			dialog.setTitle("ALERT!");

			Button button =(Button)dialog.findViewById(R.id.button1);
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

	public void commentThread(Comment comment){


	}
	
	

	
}