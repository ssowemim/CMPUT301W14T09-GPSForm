package ca.cmput301w14t09.Controller;


import android.graphics.Bitmap;
import ca.cmput301w14t09.Model.UserProfileModelList;

public class UserProfileController{

	public static final int MAX_BIO_LENGTH = 100;
	public static final int MAX_PHONE_LENGTH = 12;
	public static final int MAX_BITMAP_DIMENSIONS = 50;
	
	private UserProfileModelList model;
	
	public UserProfileController(UserProfileModelList uPModelList) {
		// TODO Auto-generated constructor stub
		this.model = uPModelList;
		
	}

	public void finalizeVariables(String uniqueID, String fLName,
			String sex, String phone, String email,
			String bio, Bitmap pic) {
		
		if (pic.getWidth() > MAX_BITMAP_DIMENSIONS || pic.getHeight() > MAX_BITMAP_DIMENSIONS){
			double scalingFactor = pic.getWidth() * 1.0 / MAX_BITMAP_DIMENSIONS;
			if(pic.getHeight() > pic.getWidth())
				scalingFactor = pic.getHeight() * 1.0 / MAX_BITMAP_DIMENSIONS;
			
			int newWidth = (int)Math.round(pic.getWidth() / scalingFactor);
			int newHeight = (int)Math.round(pic.getHeight() / scalingFactor);
			
			pic = Bitmap.createScaledBitmap(pic, newWidth, newHeight, false);
		} 
		
		if (bio.length() > MAX_BIO_LENGTH)
			bio=bio.substring(0, MAX_BIO_LENGTH);
		
		if (phone.length() > MAX_PHONE_LENGTH)
			phone = phone.substring(0, MAX_PHONE_LENGTH);
		
		model.addUserProfile(uniqueID, fLName, sex, phone, email, bio, pic);
	}
}
