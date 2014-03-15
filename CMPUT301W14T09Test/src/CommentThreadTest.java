import org.junit.Test;

import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.FileManaging.CreateComment;
import ca.cmput301w14t09.model.Comment;
import ca.cmput301w14t09.model.CommentThread;

import junit.framework.TestCase;


public class CommentThreadTest extends TestCase {

	@Test
	public void testAddToThread(){
		Comment testingComment;
		CreateComment testComment = new CreateComment();
		LocationController lc = new LocationController();
		CommentThread cThread = new CommentThread();
		testingComment = CreateComment.newComment(lc, "testingauthor", "testing comment Text", true);
		cThread.addToThread(testingComment);
		assertNotNull(cThread);
	}

	@Test 
	public void testNullInThread() {
		Comment testingComment, testingCommentOne;
		
		LocationController lc = new LocationController();
		
		CommentThread cThread = new CommentThread();
		CommentThread cThreadOne = new CommentThread();
		
		testingComment = CreateComment.newComment(lc, null, null, true);
		testingCommentOne = CreateComment.newComment(lc, null, null, true);
		
		cThread.addToThread(testingComment);
		cThreadOne.addToThread(testingCommentOne);
		
		assertFalse(cThread.equals(cThreadOne));
	} 


}
