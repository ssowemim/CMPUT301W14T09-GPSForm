package ca.cmput301w14t09.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.FileManaging.CommentFactory;
import ca.cmput301w14t09.FileManaging.SerializableBitmap;
import ca.cmput301w14t09.Model.Cache;
import ca.cmput301w14t09.Model.Comment;


public class CacheTest extends TestCase {
	
	private static final int ArrayList = 0;
	private static final int Comment = 0;

	/**
	 * @author Cameron Alexander
	 */
	public void testcache(){
			final int MAX_LENGTH = 200;
	        ArrayList<Comment> comments = new ArrayList<Comment>(MAX_LENGTH);
	        assertTrue(comments.size() == 0);
	    }
	
	
	/**
	 * @author Cameron Alexander
	 */
	public void testadd(){
		Cache cache = new Cache();
	    LocationController lc1 = new LocationController();
	    String authorname = "cameron";
	    String commentText = "this is a comment";
	    Boolean TopComment = true;
	    SerializableBitmap pic = null;
	    Boolean haspic = false;
	    
	    Comment comment = CommentFactory.buildComment(lc1, authorname, commentText, TopComment, pic, haspic, authorname);
	    
	    cache.add(comment);
	    
	    assertTrue(cache.getCache().size() == 1);
	    
		
	}
	
	/**
	 * @author Cameron Alexander
	 */
	public void testadd2(){
		Cache cache = new Cache();
		final int MAX_LENGTH = 200;
		ArrayList<Comment> newComments = null;
		LocationController lc1 = new LocationController();
	    String text2 = "cameron";
	    String text1 = "this is a comment";
	    
	    cache.add(newComments);
		
	}
	
	/**
	 * @author Cameron Alexander
	 */
	public void testGetTopComments(){
		Cache cache = new Cache();
		
		ArrayList<Comment> comments = cache.getTopComments(true);
		ArrayList<Comment> comments2 = cache.getTopComments(false);
		
		assertTrue(comments2 != comments);
		
	}

	/**
	 * @author Cameron Alexander
	 */
	public void testGetSubComments(){
		Cache cache = new Cache();

		ArrayList<Comment> subcomments = cache.getSubComments("12345");
		ArrayList<Comment> subcomments2 = cache.getSubComments("1234");

		assertTrue(subcomments2 != subcomments);
	}
	
	
}
