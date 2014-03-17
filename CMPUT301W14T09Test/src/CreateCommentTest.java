import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.FileManaging.CreateComment;
import ca.cmput301w14t09.Model.Comment;
import junit.framework.TestCase;

/**
 * 
 * @author chunhan
 * Testing for CreateComment functionality
 *
 */

public class CreateCommentTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testCreateCommentFunction () {
		CreateComment newComment = new CreateComment();
		LocationController lc = new LocationController();
		Comment anewComment = newComment.newComment(lc, "Tester", "Testing", false);
		
		assertNotNull(anewComment);
	}
}
