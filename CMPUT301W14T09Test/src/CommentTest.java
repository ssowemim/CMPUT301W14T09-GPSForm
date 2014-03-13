import static org.junit.Assert.*;
import junit.framework.TestCase;
import org.junit.Test;

import ca.cmput301w14t09.FileManaging.CreateComment;


public class CommentTest extends TestCase{

	@Test
	public void testaddComment() {
		
		
		assertNotNull(CreateComment.newComment("tester", "testingcommentcache", true));
		
	}

}
