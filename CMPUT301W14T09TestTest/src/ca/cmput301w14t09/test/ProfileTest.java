package ca.cmput301w14t09.test;


import ca.cmput301w14t09.Model.Profile;

import junit.framework.TestCase;

/**
 * 
 * @author Chun-Han Lee
 * Test for profile object 
 *
 */
public class ProfileTest extends TestCase {

	/**
	 * @author Chun-Han Lee
	 */
	public void testProfile(){
		Profile newprofile = new Profile("Tester");
		assertNotNull(newprofile);
	}
}
