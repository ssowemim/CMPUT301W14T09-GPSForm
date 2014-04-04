package ca.cmput301w14t09.test;

import ca.cmput301w14t09.Model.User;
import junit.framework.TestCase;

/**
 * 
 * @author chunhan
 * Test User methods functionality.
 */
public class UserTest extends TestCase {

	public UserTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}
	

	public void testCreateUser(){
		User newUser = new User();
		newUser.setDeviceId("dev1");
		newUser.setUserName("Tester");
		
		assertTrue(newUser.getDeviceId() == "dev1" && newUser.getUserName() == "Tester");
		
	}
	

}
