package ca.cmput301w14t09.test;

import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.FileManaging.CommentFactory;
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
		CommentFactory newComment = new CommentFactory();
		LocationController lc = new LocationController();
		Comment anewComment = newComment.buildComment(lc, "Tester", "Testing", false, null, null, null);

		assertNotNull(anewComment);
	}
}
