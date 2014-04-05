package ca.cmput301w14t09;

import java.util.ArrayList;
import java.util.List;

import ca.cmput301w14t09.Controller.UserProfileController;
import ca.cmput301w14t09.Model.User;
import ca.cmput301w14t09.Model.UserProfileModelList;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class UserProfileActivity extends Activity{

	public static final int OBTAIN_PIC_REQUEST_CODE = 117;
	
	protected Spinner maleOrFemale;
	protected EditText firstLastName;
	protected EditText phoneText;
	protected EditText emailText;
	protected EditText biographyText;
	protected Button saveButton;
	protected ImageView userProfilePicture;
	protected TextView usernameText;
	protected Bitmap currentPicture;
	
	User user;
	UserProfileModelList uPModelList;
	UserProfileController uPController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		
		this.maleOrFemale = (Spinner)findViewById(R.id.spinnerSex);
		this.firstLastName = (EditText)findViewById(R.id.editTextFirstLastName);
		this.emailText= (EditText)findViewById(R.id.editTextEmail);
		this.biographyText= (EditText)findViewById(R.id.editTextBio);
		this.saveButton = (Button)findViewById(R.id.buttonSave);
		this.usernameText = (TextView)findViewById(R.id.textViewUsername);
		this.userProfilePicture = (ImageView)findViewById(R.id.imageViewUserProfile);
		
		uPModelList = new UserProfileModelList();
		uPController = new UserProfileController(uPModelList, this);
		
		maleFemaleSpinner();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.user_profile, menu);
		
		Intent intent = getIntent();
		user = (User)intent.getSerializableExtra("CURRENT_USER");
		
		usernameText.setText("@/"+user.getUserName().toString());
		return true;
	}
	
	public void retrievePicture(View v){
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, OBTAIN_PIC_REQUEST_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == OBTAIN_PIC_REQUEST_CODE && resultCode == RESULT_OK){
			currentPicture = (Bitmap)data.getExtras().get("data");
			userProfilePicture.setImageBitmap(currentPicture);
		}
	}
	
	public void maleFemaleSpinner(){
		List<String>list = new ArrayList<String>();
		list.add("Male");
		list.add("Female");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		this.maleOrFemale.setAdapter(adapter);
	}
	
	public void saveUserProfile(View v){
		finish();
	}

}
