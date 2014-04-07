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
import java.util.List;

import ca.cmput301w14t09.Controller.UserProfileController;
import ca.cmput301w14t09.Model.User;
import ca.cmput301w14t09.Model.UserProfileModel;
import ca.cmput301w14t09.Model.UserProfileModelList;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

/**
 * This class is in full control of the UserPofile interface.
 * Controlling all its input and display.
 * It pulls all its information from the elasticsearchOperations
 * and also saves all its information to elasticsearchOperations as soon as save is clicked.
 * All coding was done by myself.
 * @author ssowemim
 *
 */
public class UserProfileActivity extends Activity{

	//Used to obtain the picture request from activity request
	public static final int OBTAIN_PIC_REQUEST_CODE = 117;
	
	//Initialize variable
	protected Spinner maleOrFemale;
	protected EditText firstLastName;
	protected EditText phoneText;
	protected EditText emailText;
	protected EditText biographyText;
	protected Button saveButton;
	protected ImageView userProfilePicture;
	protected TextView usernameText;
	
	private Bitmap currentPicture = null;
	
	User user;
	ArrayList<UserProfileModel> userProfile = null;
	UserProfileModelList uPModelList;
	UserProfileController uPController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		
		this.maleOrFemale = (Spinner)this.findViewById(R.id.spinnerSex);
		this.firstLastName = (EditText)this.findViewById(R.id.editTextFirstLastName);
		this.emailText= (EditText)this.findViewById(R.id.editTextEmail);
		this.phoneText=(EditText)this.findViewById(R.id.editTextPhone);
		this.biographyText= (EditText)this.findViewById(R.id.editTextBio);
		this.saveButton = (Button)this.findViewById(R.id.buttonSave);
		this.usernameText = (TextView)this.findViewById(R.id.textViewUsername);
		this.userProfilePicture = (ImageView)this.findViewById(R.id.imageViewUserProfile);
		
		uPModelList = new UserProfileModelList();
		uPController = new UserProfileController(uPModelList);
		
		maleFemaleSpinner();
		initializeVariables();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.user_profile, menu);
		
		Intent intent = getIntent();
		user = (User)intent.getSerializableExtra("CURRENT_USER");
		
		
		usernameText.setText("@/"+user.getUserName().toString());
		if (user.getUserName().toString().equalsIgnoreCase("guest"))
			userToProfile("guest");
		else 
			userToProfile("notguest");
		
		return true;
	}
	
	/**
	 * This method retrieves the picture taken by the phone and runs it thought ForResult
	 * @param v
	 */
	public void retrievePicture(View v){
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, OBTAIN_PIC_REQUEST_CODE);
	}
	
	/**
	 * Takes in a string text, this either being guest or notguest.
	 * guest is not allowed to have a user profile so this closes this dialog and displays
	 * a toast. But if it is not a guest, it goes through all the operations to find a userprofiel
	 * if one exists.
	 * @param text
	 */
	public void userToProfile(String text){

		if (!text.equals("guest")){
			int size, lastItem;
			try {
				userProfile = ElasticSearchOperations.pullUserProfile(user.getUniqueID());

				if(!userProfile.isEmpty()){
					size = userProfile.size();
					lastItem = size-1;

					this.currentPicture = userProfile.get(lastItem).getPicture();
					this.userProfilePicture.setImageBitmap(this.currentPicture);
					this.firstLastName.setText(userProfile.get(lastItem).getFirstLastName().toString());
					if(userProfile.get(lastItem).getSex().equalsIgnoreCase("male"))
						this.maleOrFemale.setSelection(0);
					else
						this.maleOrFemale.setSelection(1);

					this.phoneText.setText(userProfile.get(lastItem).getPhone().toString());
					this.emailText.setText(userProfile.get(lastItem).getEmail().toString());
					this.biographyText.setText(userProfile.get(lastItem).getBiography().toString());
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			Toast.makeText(getApplicationContext(),"Guest can't have userprofile.", Toast.LENGTH_LONG).show();
			finish();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == OBTAIN_PIC_REQUEST_CODE && resultCode == RESULT_OK){
			this.currentPicture = (Bitmap)data.getExtras().get("data");
			this.userProfilePicture.setImageBitmap(this.currentPicture);
		}
	}
	
	/**
	 * This initializes the spinner for male/female
	 */
	public void maleFemaleSpinner(){
		List<String>list = new ArrayList<String>();
		list.add("Male");
		list.add("Female");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		this.maleOrFemale.setAdapter(adapter);
	}
	
	/**
	 * The process of saving the user profile to elastic search.
	 * First passes it through the finalize variables in the user profile controller.
	 * @param v
	 */
	public void saveUserProfile(View v){

		uPController.finalizeVariables(user.getUniqueID().toString(), firstLastName.getText().toString(), 
									   maleOrFemale.getSelectedItem().toString(), phoneText.getText().toString(),emailText.getText().toString(), 
									   biographyText.getText().toString(), this.currentPicture);
		finish();
	}

	/**
	 * This gives all the textfield an initial value, just to avoid a nullpointerexception.
	 */
	public void initializeVariables(){
		this.phoneText.setText("");
		this.firstLastName.setText("");
		this.emailText.setText("");
		this.biographyText.setText("");
		this.maleOrFemale.setSelection(0);
		this.currentPicture = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_user_profile);

	}

}
