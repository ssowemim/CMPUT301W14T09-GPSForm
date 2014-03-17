import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.FileManaging.CreateComment;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import ca.cmput301w14t09.Model.Comment;


public class ElasticSearchTest {

	public ElasticSearchTest() {
	}

	@Test
	public void Posttest() {
		
		ElasticSearchOperations test = new ElasticSearchOperations();
		ArrayList<Comment> comment2;
		String Threadid = null;
		
		boolean topComment = true;
		String authorname = "";
		String commentText = "hey how are you";
		
		LocationController lc = new LocationController();
		
		Comment comment = CreateComment.newComment(lc, authorname, commentText, topComment);
		comment.setThreadId("65");
	
		
		test.postThread(comment);
		
		try {
			 comment2 =test.pullThreads();
			for(int i=0;i<comment2.size();i++){
				
				Comment cam2 = comment2.get(i);
		    	String strincheck = cam2.getThreadId();
		    	
		    	
		    	if(strincheck.equals("65")){
		    		Threadid = strincheck;
		    	}
				
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		assertTrue(Threadid == "65"); 
	}

}
