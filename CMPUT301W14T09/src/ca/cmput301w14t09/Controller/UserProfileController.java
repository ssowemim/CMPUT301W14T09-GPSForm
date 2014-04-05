package ca.cmput301w14t09.Controller;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import ca.cmput301w14t09.R;
import ca.cmput301w14t09.UserProfileActivity;
import ca.cmput301w14t09.Model.UserProfileModelList;

public class UserProfileController{

	public static final int MAX_BIO_LENGTH = 100;
	public static final int MAX_PHONE_LENGTH = 12;
	public static final int MAX_BITMAP_DIMENSIONS = 50;
	
	private UserProfileActivity activity;
	private UserProfileModelList model;
	
	public UserProfileController(UserProfileModelList uPModelList,
			UserProfileActivity userProfileActivity) {
		// TODO Auto-generated constructor stub
		this.model = uPModelList;
		this.activity = userProfileActivity;
		
	}

	public void finalizeVariables(String uniqueID, String fLName,
			String sex, String phone, String email,
			String bio, Bitmap pic) {
		// TODO Auto-generated method stub
		//Log.e("PICTURE11",pic.toString());
		
		if (pic.getWidth() > MAX_BITMAP_DIMENSIONS || pic.getHeight() > MAX_BITMAP_DIMENSIONS){
			double scalingFactor = pic.getWidth() * 1.0 / MAX_BITMAP_DIMENSIONS;
			if(pic.getHeight() > pic.getWidth())
				scalingFactor = pic.getHeight() * 1.0 / MAX_BITMAP_DIMENSIONS;
			
			int newWidth = (int)Math.round(pic.getWidth() / scalingFactor);
			int newHeight = (int)Math.round(pic.getHeight() / scalingFactor);
			
			pic = Bitmap.createScaledBitmap(pic, newWidth, newHeight, false);
		} 
		model.addUserProfile(uniqueID, fLName, sex, phone, email, bio, pic);
	}
	
	public void clearModel() {
		this.model.clear();
	}
}
