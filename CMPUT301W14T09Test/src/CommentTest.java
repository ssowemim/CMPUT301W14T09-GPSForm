import static org.junit.Assert.*;
import junit.framework.TestCase;
import org.junit.Test;

import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.FileManaging.CreateComment;


public class CommentTest extends TestCase{

	@Test
	public void testAddComment() {
		
		
		LocationController lc = new LocationController();
		lc.setGeoLocation();
		assertNotNull(CreateComment.newComment(lc, "tester", "testingcommentcache", true));
		
	}
	
	@Test
	public void testAddNullComment() {
		LocationController lc = new LocationController();
		LocationController lcOne = new LocationController();
		
		assertFalse(CreateComment.newComment(lc, "testerUser", null, true).equals(CreateComment.newComment(lcOne,  "testerUser", "Bobby", true)));
	}
	
	public void testIfTopComment() {
		LocationController lc = new LocationController();
		LocationController lcOne = new LocationController();
		
		assertFalse(CreateComment.newComment(lc, "testerUser", "testerComment", false).equals(CreateComment.newComment(lcOne, "testerUser", "testerComment", true)));
	}
	
	

}
