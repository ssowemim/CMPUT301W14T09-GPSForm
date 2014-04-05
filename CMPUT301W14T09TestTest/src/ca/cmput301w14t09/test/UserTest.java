package ca.cmput301w14t09.test;

import ca.cmput301w14t09.Model.User;
import junit.framework.TestCase;

/**
 * 
 * @author Chun-Han Lee
 * Test User methods functionality.
 */
public class UserTest extends TestCase {

	/**
	 * @author Chun-Han Lee
	 */
	public void testCreateUser(){
		User newUser = new User();
		newUser.setDeviceId("dev1");
		newUser.setUserName("Tester");
		
		assertTrue(newUser.getDeviceId() == "dev1" && newUser.getUserName() == "Tester");
		
	}
	

}
