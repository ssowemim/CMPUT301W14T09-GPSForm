package ca.cmput301w14t09.FileManaging;

import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.model.Comment;
import ca.cmput301w14t09.model.GeoLocation;

import java.util.Date;

public class CreateComment {
	
	public static Comment newComment(LocationController lc, String authorname, String commentText, Boolean topComment){
		Comment comment = new Comment();
		comment.setAuthorName(authorname);
		comment.setCommentText(commentText);
		
		GeoLocation geo = lc.getGeoLocation();
		comment.setGeoLocation(geo);
		
		comment.setTopComment(topComment);
		comment.setPostDate(new Date());
		comment.setThreadId((comment.getAuthorName() + " " + comment.getPostDate().toString()));
		return comment;
	}

}
