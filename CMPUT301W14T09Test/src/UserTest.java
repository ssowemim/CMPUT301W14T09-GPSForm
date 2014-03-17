import org.junit.Test;
import ca.cmput301w14t09.Model.Profile;
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
	
	@Test
	public void testCreateUser(){
		User newUser = new User();
		newUser.setDeviceId("dev1");
		newUser.setUserName("Tester");
		
		assertTrue(newUser.getDeviceId() == "dev1" && newUser.getUserName() == "Tester");
		
	}
	
	@Test
	public void testCreateUserWithProfile () {
		User newUser = new User();
		newUser.setUserName("Tester");
		Profile newProfile = new Profile(newUser.getUserName());
		newUser.setDeviceId("dev1");
		Profile profile2 = newUser.getProfile();
		
		assertTrue(profile2 == newProfile);
		assertTrue(newUser.getDeviceId() == "dev1");
		assertTrue(newUser.getUserName() == "Tester");
		
	}

}
