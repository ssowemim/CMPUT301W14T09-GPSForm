package ca.cmput301w14t09.test;

import ca.cmput301w14t09.R;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Model.UserProfileModel;
import android.graphics.BitmapFactory;
import android.test.ActivityInstrumentationTestCase2;

/**
 * 
 * @author ssowemim
 *
 */
public class UserProfileModelTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public UserProfileModelTest() {
		super(TopCommentsActivity.class);
	}
	
	UserProfileModel testUPModel = new UserProfileModel("UNIQUEID", "Benjamin Button", "Male", 
											"1234567899", "benjamin@gutton.com", "something long", 
											null);
	
	/**
	 * Testing to see if the unique Id were added properly
	 */
	public void testUniqueID(){
		//check if the unique id matches the one created with the user profile
		assertEquals("UNIQUEID", testUPModel.getUniqueID());
		
		//going to change the uniqueID then check if it matches the changed one.
		testUPModel.setUniqueID("CHANGEUNIQUEID");
		assertEquals("CHANGEUNIQUEID", testUPModel.getUniqueID());
	}
	
	/**
	 * Testing to see if the name were added properly to the model
	 */
	public void testName(){
		assertEquals("Benjamin Button", testUPModel.getFirstLastName());
		
		testUPModel.setFirstLastName("Button Benjamin");
		assertEquals("Button Benjamin", testUPModel.getFirstLastName());
	}
	
	/**
	 * Testing to see if the sex of the model are added properly
	 */
	public void testSex(){
		assertEquals("Male", testUPModel.getSex());
		
		testUPModel.setSex("Female");
		assertEquals("Female", testUPModel.getSex());
	}
	
	/**
	 * Testing to see if the phone number are added to the model properly
	 */
	public void testPhone(){
		assertEquals("1234567899", testUPModel.getPhone());
		
		testUPModel.setPhone("9987654321");
		assertEquals("9987654321", testUPModel.getPhone());
	}
	
	/**
	 * Testing to see if the email are added to the model properly
	 */
	public void testEmail(){
		assertEquals("benjamin@gutton.com", testUPModel.getEmail());
		
		testUPModel.setEmail("bobby@brown.com");
		assertEquals("bobby@brown.com", testUPModel.getEmail());
	}
	
	/**
	 * Testing to see if bio are added to the model properly
	 */
	public void testBio(){
		assertEquals("something long", testUPModel.getBiography());
		
		testUPModel.setBiography("biography");
		assertEquals("biography", testUPModel.getBiography());
	}
	
	/**
	 * Testing to see if pictures are added to the model properly
	 */
	public void testPicture(){
		assertNull(testUPModel.getPicture());
		
		testUPModel.setPicture(BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.no_img));
		assertNotNull(testUPModel.getPicture());
	}
	
	/**
	 * Testing to see if the user profile model was made
	 */
	public void testUserModel(){
		assertNotNull(testUPModel);
	}
}
