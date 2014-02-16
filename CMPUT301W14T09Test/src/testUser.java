import ca.cmput301w14t09.model.User;
import junit.framework.TestCase;


public class testUser extends TestCase {

	public testUser(String name) {
		super(name);
	}
	
	public void testUserName(){
		User user = new User();
		
		String username = "testusername";
		
		user.setUserName(username);
		
		String getUserName = user.getUserName();
		
		assertTrue(getUserName.equals(getUserName));
	}

}
