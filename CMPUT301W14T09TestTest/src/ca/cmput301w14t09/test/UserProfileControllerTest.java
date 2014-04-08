package ca.cmput301w14t09.test;

import java.util.ArrayList;
import java.util.List;

import ca.cmput301w14t09.R;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Controller.UserProfileController;
import ca.cmput301w14t09.Model.UserProfileModel;
import ca.cmput301w14t09.Model.UserProfileModelList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.ActivityInstrumentationTestCase2;

/**
 * This test case tests the user profile controller.
 * @author ssowemim
 *
 */
public class UserProfileControllerTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public UserProfileControllerTest() {
		super(TopCommentsActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	UserProfileModelList testUPModel = new UserProfileModelList();
	UserProfileController testUPController = new UserProfileController(testUPModel);
	List<UserProfileModel> list = new ArrayList<UserProfileModel>();
	
	public void testFinalizeVariables() {
		
		Bitmap picture = BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.no_img);
		picture = Bitmap.createScaledBitmap(picture, 70, 70, false);
		String phone = "12312345678912";
		String bio="recursion";
		
		while(bio.length() < 101)
			bio=bio.concat(bio);
		
		
		//Checking to make sure the width and height of the picture are infact 70.
		assertTrue(picture.getHeight() == 70);
		assertEquals(picture.getWidth(), 70);
		
		//Checking to make sure the length of bio is in fact greater than 101
		assertTrue(bio.length() > 101);
		//Checking to make sure the length of phone is in fact greater than 12
		assertTrue(phone.length() > 12);
		
		testUPController.finalizeVariables("UniqueID", "Name", "Female", phone, "email@facebook.com", 
										bio, picture);
		assertTrue(list.isEmpty());
		list = testUPModel.getList();
		assertNotNull(list);
		assertEquals(1, list.size());
	}
	
	public void testNewBio(){
		Bitmap picture = BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.no_img);
		picture = Bitmap.createScaledBitmap(picture, 70, 70, false);
		String phone = "12312345678912";
		String bio="recursion";
		
		while(bio.length() < 201)
			bio=bio.concat(bio);
		
		//Checking to make sure the length of bio is in fact greater than 101
		assertTrue(bio.length() > 200);
		
		testUPController.finalizeVariables("UniqueID", "Name", "Female", phone, "email@facebook.com", 
				bio, picture);
		bio = testUPController.getNewBio();
		//Checking to see that the length of the bio is now less than its inital length
		assertTrue(bio.length() < 150);
		
		//Checking to see the length was reduced to an exact length of 100
		assertEquals(100, bio.length());
		
	}
	
	public void testNewPhone(){
		Bitmap picture = BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.no_img);
		picture = Bitmap.createScaledBitmap(picture, 70, 70, false);
		String phone = "12312345678912";
		String bio="recursion";
		
		//Checking to make sure the length of phone is in fact greater than 12
		assertEquals(14,phone.length());
		
		testUPController.finalizeVariables("UniqueID", "Name", "Female", phone, "email@facebook.com", 
				bio, picture);
		phone = testUPController.getNewPhone();
		//Checking to see that the length of the phone is now infact 12
		assertEquals(12,phone.length());

		
	}


}
