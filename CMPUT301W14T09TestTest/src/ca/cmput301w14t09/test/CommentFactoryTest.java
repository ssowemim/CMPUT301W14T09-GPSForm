package ca.cmput301w14t09.test;

import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.FileManaging.CommentFactory;
import ca.cmput301w14t09.Model.Comment;
import junit.framework.TestCase;

/**
 * 
 * @author chunhan
 * Testing CommentFactory
 */
public class CommentFactoryTest extends TestCase {

	public CommentFactoryTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testBuildComment(){
		LocationController lc = new LocationController();
		Comment comment = CommentFactory.buildComment(lc, "Tester", "testing", true, null, true);
		assertNotNull(comment);
		assertTrue(comment.getAuthorName().contains("Tester"));
		assertTrue(comment.getCommentText().contains("testing"));
		assertTrue(comment.getTopComment().equals(true));
		//assertNotNull(comment.getPicture());
		assertTrue(comment.getHasPicture().equals(true));

	}
	
	public void testBuildReplies(){
		LocationController lc = new LocationController();
		Comment comment = CommentFactory.buildReplyComment(lc, "Tester", "testing", true, null, "Tester testing", true);
		assertNotNull(comment);
		assertTrue(comment.getAuthorName().contains("Tester"));
		assertTrue(comment.getCommentText().contains("testing"));
		assertTrue(comment.getTopComment().equals(true));
		//assertNotNull(comment.getPicture());
		assertTrue(comment.getThreadId().equals("Tester testing"));
		assertTrue(comment.getHasPicture().equals(true));
	}
}
