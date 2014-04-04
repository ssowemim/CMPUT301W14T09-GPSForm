package ca.cmput301w14t09.test;

import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.FileManaging.FileLoading;
import ca.cmput301w14t09.FileManaging.FileSaving;
import ca.cmput301w14t09.Model.User;
import android.test.ActivityInstrumentationTestCase2;

/**
 * 
 * @author chunhan
 * Testing the fileloading & filesaving methods 
 */
public class FileLoadingSavingTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public FileLoadingSavingTest() {
		super(TopCommentsActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	/**
	 * 
	 * @author chunhan
	 * Testing Saving and Loading at the same time 
	 * 
	 */
	public void testLoadFromFile(){
		User newuser = new User("hey");
		FileSaving.appendUserNameToList("hey", getActivity());
		String [] testList = FileLoading.loadFromFile(getActivity());
		assertNotNull(testList);
	}
	
	public void testReturnUser(){
		User newuser = new User("hey");
		FileSaving.saveUserFile(newuser, getActivity());
		User testuser = FileLoading.returnUser("hey", getActivity());
		assertNotNull(testuser);
		String username = newuser.getUserName();
		assertTrue(username.equals("hey"));
	}

}
