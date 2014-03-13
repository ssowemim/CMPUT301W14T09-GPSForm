import org.junit.Test;

import ca.cmput301w14t09.model.Comment;

import junit.framework.TestCase;


public class ThreadTest extends TestCase {

	@Test
	public void testAddToThread(){
		Comment comment = new Comment();
		Thread thread = new Thread();
		comment.setAttachment(null);
		comment.setAuthorName("Tester");
		comment.setCommentText("testing comments");
		comment.setFavoriteCount(0);
		comment.setGeoLocation(null);
	
		
		
	}

}
