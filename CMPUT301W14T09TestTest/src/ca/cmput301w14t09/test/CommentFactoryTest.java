package ca.cmput301w14t09.test;

import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.FileManaging.CommentFactory;
import ca.cmput301w14t09.Model.Comment;
import junit.framework.TestCase;

/**
 * 
 * @author Chun-Han Lee
 * Testing CommentFactory
 * 
 */
public class CommentFactoryTest extends TestCase {

	/**
	 * @author Chun-Han Lee
	 */
	public void testBuildComment(){
		LocationController lc = new LocationController();
		Comment comment = CommentFactory.buildComment(lc, "Tester", "testing", true, null, true, null);
		assertNotNull(comment);
		assertTrue(comment.getAuthorName().contains("Tester"));
		assertTrue(comment.getCommentText().contains("testing"));
		assertTrue(comment.getTopComment().equals(true));
		assertTrue(comment.getHasPicture().equals(true));

	}
	
	/**
	 * @author Chun-Han Lee
	 */
	public void testBuildReplies(){
		LocationController lc = new LocationController();
		Comment comment = CommentFactory.buildReplyComment(lc, "Tester", "testing", true, null, "Tester testing", true, null);
		assertNotNull(comment);
		assertTrue(comment.getAuthorName().contains("Tester"));
		assertTrue(comment.getCommentText().contains("testing"));
		assertTrue(comment.getTopComment().equals(true));
		assertTrue(comment.getThreadId().equals("Tester testing"));
		assertTrue(comment.getHasPicture().equals(true));
	}
}
