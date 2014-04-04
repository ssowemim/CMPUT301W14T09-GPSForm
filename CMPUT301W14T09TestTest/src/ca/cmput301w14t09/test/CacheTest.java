package ca.cmput301w14t09.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.FileManaging.CommentFactory;
import ca.cmput301w14t09.Model.Cache;
import ca.cmput301w14t09.Model.Comment;


public class CacheTest extends TestCase {
	
	public void testcache(){
			final int MAX_LENGTH = 200;
	        ArrayList<Comment> comments = new ArrayList<Comment>(MAX_LENGTH);
	        assertTrue(comments.size() == 0);
	    }
	
	
	
	public void testadd(){
		Cache cache = new Cache();
	    LocationController lc1 = new LocationController();
	    String text2 = "cameron";
	    String text1 = "this is a comment";
	    
	    Comment comment = CommentFactory.buildComment(lc1, text2, text1, true, null);
	    
	    cache.add(comment);
	    
	    assertTrue(cache.getCache().size() == 1);
	    
	    
	    
	    
		
	}
	
	


}
