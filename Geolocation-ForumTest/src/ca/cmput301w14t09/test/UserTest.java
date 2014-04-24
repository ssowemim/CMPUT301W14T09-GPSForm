package ca.cmput301w14t09.test;

import android.test.ActivityInstrumentationTestCase2;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Model.User;

/**
 * 
 * @author Chun-Han Lee & ssowemim
 * Test User methods functionality.
 */
public class UserTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public UserTest() {
		super(TopCommentsActivity.class);
	}
	
	/**
	 * This test case tests to see if a user was successfully created
	 * @author Chun-Han Lee & ssowemim
	 */
	public void testCreateUser(){
		User newUser = new User();
		newUser.setDeviceId("dev1");
		newUser.setUserName("Tester");	
		
		//Testing to see if the devideId and username were set properly
		assertTrue(newUser.getDeviceId() == "dev1" && newUser.getUserName() == "Tester");
	}
	
	/**
	 * This method tests to see if the uniqe ID returns the appropriate ID
	 * @author ssowemim
	 */
	public void testUniqueID(){
		User newUser = new User("Bobby Brown");
		
		//Unique Id is the same as the username
		assertEquals("Bobby Brown", newUser.getUniqueID());
		
		newUser = new User("Guest");
		
		//Unique iD for guest is '00000'
		assertEquals("00000", newUser.getUniqueID());
		
		//testing the username
		assertEquals("Guest", newUser.getUserName());
	}
	
	/**
	 * This method tests to see if the profile with a user is created properly
	 * also goes on to check if setting a different author name works.
	 * @author ssowemim
	 */
	public void testProfile(){
		User newUser = new User("Profile");
		
		//Unique Id is the same as the username
		assertEquals("Profile", newUser.getUniqueID());
		
		assertEquals("Profile", newUser.getProfile().getAuthorName());
		//Testing to see if user are really created and is easily in reach
		newUser.getProfile().setAuthorName("Femi Sowemimo");
		assertEquals("Femi Sowemimo", newUser.getProfile().getAuthorName());
	}
	
}
