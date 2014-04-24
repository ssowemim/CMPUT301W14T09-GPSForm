package ca.cmput301w14t09.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.FileManaging.CommentFactory;
import ca.cmput301w14t09.FileManaging.SerializableBitmap;
import ca.cmput301w14t09.Model.Cache;
import ca.cmput301w14t09.Model.Comment;


public class CacheTest extends TestCase {

	/**
	 * testing to see if variable is implemented properly
	 * @author Cameron Alexander & ssowemim
	 */
	public void testcache(){
			final int MAX_LENGTH = 200;
	        ArrayList<Comment> comments = new ArrayList<Comment>(MAX_LENGTH);
	
	        //Checking to see if empty comments array list is implemented
	        assertEquals(0, comments.size());
	        
	        Comment aComment = CommentFactory.buildComment(new LocationController(), "Mister", "How are you", true, 
	        												null, false, "Mister brown");
	        comments.add(aComment);
	        aComment = CommentFactory.buildComment(new LocationController(), "Midsst", "How are you?", true, 
					null, false, "Mister Timmy brown");
	        comments.add(aComment);
	        
	        /*
	         * Checking to see if commentsfactory does keep the comments together
	         * and input into the Comments array list was successful
	         */
	        assertEquals(2, comments.size());
	        assertEquals("Mister brown", comments.get(0).getUserName());
	        assertEquals("How are you?", comments.get(1).getCommentText());
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
	 * Testing the cache to see if the elements are inputed properly and the elements are accessible .
	 * @author Cameron Alexander & ssowemim
	 */
	public void testadd2(){
		Cache cache = new Cache();
		
		//Testing if cache was properly implemented
		assertEquals(true,cache.comments.isEmpty());
		ArrayList<Comment> newComments = new ArrayList<Comment>();

		Comment aComment = CommentFactory.buildComment(new LocationController(), "Mister", "How are you", true, 
				null, false, "Mister brown");
		newComments.add(aComment);
		aComment = CommentFactory.buildComment(new LocationController(), "Midsst", "How are you?", false, 
				null, false, "Mister Timmy brown");
		newComments.add(aComment);
		aComment = CommentFactory.buildComment(new LocationController(), "myname", "How is the weather?", true, 
				null, false, "Mister Bobby Bottoms");
		newComments.add(aComment);
		cache.add(newComments);

		//Checking to see if all the comments were stored in the cache properly
		assertEquals(false, cache.comments.isEmpty());
		assertEquals(3,cache.comments.size());
	}
	
	/**
	 * Checking to see if the top level comments are fully functional in the cache
	 * @author Cameron Alexander & ssowemim
	 */
	public void testGetTopComments(){
		Cache cache = new Cache();
		
		ArrayList<Comment> newComments = new ArrayList<Comment>();

		Comment aComment = CommentFactory.buildComment(new LocationController(), "Mister", "How are you", true, 
				null, false, "Mister brown");
		newComments.add(aComment);
		
		//Checking to see if comment was added properly 
		assertEquals(1, newComments.size());
		aComment = CommentFactory.buildComment(new LocationController(), "Midsst", "How are you?", false, 
				null, false, "Mister Timmy brown");
		newComments.add(aComment);
		
		//Checking to see if comment was added properly 
		assertEquals(2, newComments.size());
		aComment = CommentFactory.buildComment(new LocationController(), "myname", "How is the weather?", true, 
				null, false, "Mister Bobby Bottoms");
		newComments.add(aComment);
		
		//Checking to see if comment was added properly 
		assertEquals(3, newComments.size());
		cache.add(newComments);
		
		//Checking if right amount of top comments are returned
		ArrayList<Comment> topComments = cache.getTopComments();
		assertEquals(2, topComments.size());
	
		//Checking to see if the proper top level comments were returned
		assertEquals("Mister brown", topComments.get(0).getUserName());
		assertEquals("How is the weather?", topComments.get(1).getCommentText());
		
	}

	/**
	 * Tests to see if correct reply comments are returned in all different scenarios
	 * @author Cameron Alexander & ssowemim
	 */
	public void testGetSubComments(){
		Cache cache = new Cache();

		ArrayList<Comment> newComments = new ArrayList<Comment>();

		Comment aComment = CommentFactory.buildReplyComment(new LocationController(), "authorname", 
											"commentText", true, null, "threadId", false, "userNametest01");
		newComments.add(aComment);
		
		//Checking to see if comment was added properly 
		assertEquals(1, newComments.size());
		aComment = CommentFactory.buildReplyComment(new LocationController(), "authorname_one", 
				"commentText_one", false, null, "threadId", false, "userNametest02");
		newComments.add(aComment);

		//Checking to see if comment was added properly 
		assertEquals(2, newComments.size());
		aComment = CommentFactory.buildReplyComment(new LocationController(), "authorname_two", 
				"commentText_two", false, null, "threadId_two", false, "userNametest03");
		newComments.add(aComment);
		cache.add(newComments);

		//Checking to see if last comment was successfully added
		assertEquals(3, newComments.size());

		//Checks to see if the reply comments received matches threadId correctly
		ArrayList<Comment> replyComments = cache.getSubComments("threadId");
		assertEquals(2, replyComments.size());
		assertEquals("authorname_one", replyComments.get(1).getAuthorName());

		//Checking to see if reply comments matches properly
		replyComments.clear();
		replyComments = cache.getSubComments("threadId_two");
		assertEquals(1, replyComments.size());
		assertEquals("userNametest03", replyComments.get(0).getUserName());

	}
}
