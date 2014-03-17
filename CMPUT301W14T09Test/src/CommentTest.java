import static org.junit.Assert.*;
import junit.framework.TestCase;
import org.junit.Test;
import ca.cmput301w14t09.model.Comment;

/**
 * 
 * @author chunhan
 * Tests to see if comments are manipulable 
 *
 */
public class CommentTest extends TestCase{

	@Test
	public void testAddComment() {
		
		Comment newComment = new Comment();
		newComment.setAuthorName("tester");
		newComment.setCommentText("testing add comment");
		newComment.setFavoriteCount(0);
		
		assertTrue(newComment.getAuthorName() == "tester");
		assertTrue(newComment.getCommentText() == "testing add comment");
		assertTrue(newComment.getFavoriteCount() == 0);
	}

	@Test
	public void testIfTopComment() {
		Comment newComment = new Comment();
		newComment.setAuthorName("tester");
		newComment.setTopComment(true);
		
		assertTrue(newComment.getTopComment());
	}
	
	@Test
	public void testNotTopComment(){
		Comment newComment = new Comment();
		newComment.setAuthorName("tester");
		newComment.setTopComment(false);
		
		assertFalse(newComment.getTopComment());
	}
	
	

}
