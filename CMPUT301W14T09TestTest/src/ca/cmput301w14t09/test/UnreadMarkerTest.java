package ca.cmput301w14t09.test;

import java.util.Date;

import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.UnreadMarker;
import junit.framework.TestCase;

/**
 * 
 * @author chunhan
 *
 */
public class UnreadMarkerTest extends TestCase {

	public UnreadMarkerTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testUnreadMarker(){
		Comment comment = new Comment();
		comment.setAuthorName("tester");
		comment.setPostDate(new Date());
		Comment comment1 = new Comment();
		comment1.setAuthorName("tester1");
		comment1.setPostDate(new Date());
		UnreadMarker unread = new UnreadMarker(true, comment);
		UnreadMarker unread1 = new UnreadMarker(true, comment1);
		unread.compareTo(unread1);
	}
}
