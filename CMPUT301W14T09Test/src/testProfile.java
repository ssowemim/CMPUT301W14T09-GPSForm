import ca.cmput301w14t09.model.Profile;
import junit.framework.TestCase;


public class testProfile extends TestCase {

	public testProfile(String name) {
		super(name);
	}

	public void testProfileName(){
		Profile profile = new Profile();
		
		String username = "testing author name";
		
		profile.setAuthorName(username);
		
		String testName = profile.getAuthorName();
		
		assertTrue(testName.equals(testName));
	}
}
