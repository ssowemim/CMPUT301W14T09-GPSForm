package ca.cmput301w14t09.test;

import ca.cmput301w14t09.R;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Model.UserProfileModel;
import android.graphics.BitmapFactory;
import android.test.ActivityInstrumentationTestCase2;
import junit.framework.TestCase;

/**
 * 
 * @author ssowemim
 *
 */
public class UserProfileModelTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public UserProfileModelTest(Class<TopCommentsActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}
	
	UserProfileModel testUPModel = new UserProfileModel("UNIQUEID", "Benjamin Button", "Male", 
											"1234567899", "benjamin@gutton.com", "something long", 
											null);
	
	public void testUniqueID(){
		//check if the unique id matches the one created with the user profile
		assertEquals("UNIQUEID", testUPModel.getUniqueID());
		
		//going to change the uniqueID then check if it matches the changed one.
		testUPModel.setUniqueID("CHANGEUNIQUEID");
		assertEquals("CHANGEUNIQUEID", testUPModel.getUniqueID());
	}
	
	public void testName(){
		assertEquals("Benjamin Button", testUPModel.getFirstLastName());
		
		testUPModel.setFirstLastName("Button Benjamin");
		assertEquals("Button Benjamin", testUPModel.getFirstLastName());
	}
	
	public void testSex(){
		assertEquals("Male", testUPModel.getSex());
		
		testUPModel.setSex("Female");
		assertEquals("Female", testUPModel.getSex());
	}
	
	public void testPhone(){
		assertEquals("1234567899", testUPModel.getPhone());
		
		testUPModel.setPhone("9987654321");
		assertEquals("9987654321", testUPModel.getPhone());
	}
	
	public void testEmail(){
		assertEquals("benjamin@gutton.com", testUPModel.getEmail());
		
		testUPModel.setEmail("bobby@brown.com");
		assertEquals("benjamin@gutton.com", testUPModel.getEmail());
	}
	
	public void testBio(){
		assertEquals("something long", testUPModel.getBiography());
		
		testUPModel.setBiography("biography");
		assertEquals("biography", testUPModel.getBiography());
	}
	
	public void testPicture(){
		assertNull(testUPModel.getPicture());
		
		testUPModel.setPicture(BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.no_img));
		assertNotNull(testUPModel.getPicture());
	}
}
