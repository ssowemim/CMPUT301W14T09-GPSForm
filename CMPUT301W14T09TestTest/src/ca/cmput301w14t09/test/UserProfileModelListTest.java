package ca.cmput301w14t09.test;

import ca.cmput301w14t09.Model.UserProfileModelList;
import junit.framework.TestCase;

/**
 * 
 * @author Chun-Han Lee
 *
 */
public class UserProfileModelListTest extends TestCase {

	/**
	 * @author Chun-Han Lee
	 */
	public void testUserProfileModelList(){
		UserProfileModelList upml = new UserProfileModelList();
		upml.addUserProfile("123", "Tester", "Unisex", "12345678", "tester@tester.test", "hahahahatest", null);

	}
}
