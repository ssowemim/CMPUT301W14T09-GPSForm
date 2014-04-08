package ca.cmput301w14t09.test;

import junit.framework.TestCase;
import ca.cmput301w14t09.Model.Comment;

/**
 * 
 * @author Chun-Han Lee
 * Tests to see if comments are manipulable 
 *
 */

public class CommentTest extends TestCase{

	/**
	 * This tests to see if comments are added successfully
	 * @author Chun-Han Lee & ssowemim
	 */
	public void testAddComment() {
		
		Comment newComment = new Comment();
		newComment.setAuthorName("tester");
		newComment.setCommentText("testing add comment");
		newComment.setFavoriteCount(0);
		
		assertTrue(newComment.getAuthorName() == "tester");
		assertTrue(newComment.getCommentText() == "testing add comment");
		assertTrue(newComment.getFavoriteCount() == 0);
	}

	/**
	 * This tests to see a comment can be made into a top comment.
	 * @author Chun-Han Lee
	 */
	public void testIfTopComment() {
		Comment newComment = new Comment();
		newComment.setAuthorName("tester");
		newComment.setTopComment(true);
		
		assertTrue(newComment.getTopComment());
	}
	
	/**
	 * This tests to see if a comment can not be made into a top comment
	 * @author Chun-Han Lee
	 */
	public void testNotTopComment(){
		Comment newComment = new Comment();
		newComment.setAuthorName("tester");
		newComment.setTopComment(false);
		
		assertFalse(newComment.getTopComment());
	}
}
