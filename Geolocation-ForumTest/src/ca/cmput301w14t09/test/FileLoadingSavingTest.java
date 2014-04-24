package ca.cmput301w14t09.test;

import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.FileManaging.FileLoading;
import ca.cmput301w14t09.FileManaging.FileSaving;
import ca.cmput301w14t09.Model.User;
import android.test.ActivityInstrumentationTestCase2;

/**
 *  Going to be testing if the file saving and also file loading method works in this junit test
 * @author Chun-Han Lee & ssowemim
 */
public class FileLoadingSavingTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public FileLoadingSavingTest() {
		super(TopCommentsActivity.class);
	}
	
	/**
	 * Testing to see if variables were initialized right.
	 * Testing to see that the user was added successfully, this was done by getting the size
	 * of the array of user name before you add (size) and get the size after you have added (newSize)
	 * then simply compare (size+1) == new size.
	 * @author Chun-Han Lee & ssowemim
	 */
	public void testLoadFromFile(){
		User newUser = new User("hey");
		
		// Testing to see if variables were properly initialized.
		String [] testList = null;
		assertNull("This should return null", testList);
		
		//getting size of list before adding username
		testList = FileLoading.loadFromFile(getActivity());
		int size = testList.length;
		
		//Getting size of list after adding username
		FileSaving.appendUserNameToList(newUser.getUserName(), getActivity());
		testList = FileLoading.loadFromFile(getActivity());
		int newSize = testList.length;
		
		//check to see if the old size+1 equals the new size
		assertEquals(size+1, newSize);
	}
	
	/**
	 * Tests to see if the initialization worked fine.
	 * Tests to see if the user requested is actually the user returned.
	 * @author Chun-Han Lee & ssowemim
	 */
	public void testReturnUser(){
		User newUser = new User("heytesting");
		FileSaving.saveUserFile(newUser, getActivity());
		
		//Checks initialization
		User testUser = null;
		assertNull(testUser);
		
		testUser = FileLoading.returnUser(newUser.getUserName(), getActivity());;
		assertNotNull(testUser);
		
		//checks to see if the user requested matched the user that was saved in
		String userName = testUser.getUserName();
		assertEquals("heytesting", userName);
	}

}
