package ca.cmput301w14t09.test;

import junit.framework.TestCase;
import ca.cmput301w14t09.Model.Comment;

/**
 * 
 * @author chunhan
 * Tests to see if comments are manipulable 
 *
 */

public class CommentTest extends TestCase{


	public void testAddComment() {
		
		Comment newComment = new Comment();
		newComment.setAuthorName("tester");
		newComment.setCommentText("testing add comment");
		newComment.setFavoriteCount(0);
		
		assertTrue(newComment.getAuthorName() == "tester");
		assertTrue(newComment.getCommentText() == "testing add comment");
		assertTrue(newComment.getFavoriteCount() == 0);
	}


	public void testIfTopComment() {
		Comment newComment = new Comment();
		newComment.setAuthorName("tester");
		newComment.setTopComment(true);
		
		assertTrue(newComment.getTopComment());
	}
	

	public void testNotTopComment(){
		Comment newComment = new Comment();
		newComment.setAuthorName("tester");
		newComment.setTopComment(false);
		
		assertFalse(newComment.getTopComment());
	}
	
	

}
