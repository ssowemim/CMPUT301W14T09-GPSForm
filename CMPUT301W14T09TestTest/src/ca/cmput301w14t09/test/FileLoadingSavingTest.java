package ca.cmput301w14t09.test;

import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.FileManaging.FileLoading;
import ca.cmput301w14t09.FileManaging.FileSaving;
import ca.cmput301w14t09.Model.User;
import android.test.ActivityInstrumentationTestCase2;

/**
 * 
 * @author Chun-Han Lee
 * Testing the fileloading & filesaving methods 
 */
public class FileLoadingSavingTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public FileLoadingSavingTest() {
		super(TopCommentsActivity.class);
	}
	
	/**
	 * 
	 * @author Chun-Han Lee
	 * Testing Saving and Loading at the same time 
	 * 
	 */
	public void testLoadFromFile(){
		User newuser = new User("hey");
		FileSaving.appendUserNameToList("hey", getActivity());
		String [] testList = FileLoading.loadFromFile(getActivity());
		assertNotNull(testList);
	}
	
	/**
	 * 
	 * @author Chun-Han Lee
	 * Test to return User 
	 * 
	 */
	public void testReturnUser(){
		User newuser = new User("hey");
		FileSaving.saveUserFile(newuser, getActivity());
		User testuser = FileLoading.returnUser("hey", getActivity());
		assertNotNull(testuser);
		String username = newuser.getUserName();
		assertTrue(username.equals("hey"));
	}

}
