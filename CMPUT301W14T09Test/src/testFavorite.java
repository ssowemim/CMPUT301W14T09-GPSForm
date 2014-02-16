import ca.cmput301w14t09.model.Comment;
import ca.cmput301w14t09.model.Favorite;
import junit.framework.TestCase;


public class testFavorite extends TestCase {

	public testFavorite(String name) {
		super(name);
	}

	public void testGetFavorite(){
		Favorite favorite = new Favorite();
		
		Comment comment = new Comment();
		
		String content = "favorite comment";
		
		comment.setCommentText(content);
		
		favorite.setComment1(comment);
		
		Comment commentFavorite = favorite.getComment1();
		
		String commentString = commentFavorite.getCommentText();
		
		assertTrue(commentString.equals(content));
	}
}
