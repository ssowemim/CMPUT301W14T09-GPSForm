import ca.cmput301w14t09.model.Comment;
import ca.cmput301w14t09.model.UnreadMarker;
import junit.framework.TestCase;


public class testUnreadMarker extends TestCase {

	public testUnreadMarker(String name) {
		super(name);
	}
	
	
	public void testUnreadMarker(){
		
		Comment comments = new Comment();
		
		UnreadMarker marker = comments.getUnreadMarker();
		
		boolean isunread = marker.isUnread();
		
		//if marker of comment is unread return true
		assertTrue(isunread);

	}
}
