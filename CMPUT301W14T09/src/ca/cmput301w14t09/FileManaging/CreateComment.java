package ca.cmput301w14t09.FileManaging;

import ca.cmput301w14t09.model.Comment;
import java.util.Date;

public class CreateComment {
	
	public static Comment newComment(String authorname, String commentText, Boolean topComment){
		Comment comment = new Comment();
		comment.setAuthorName(authorname);
		comment.setCommentText(commentText);
		comment.setTopComment(topComment);
		comment.setPostDate(new Date());
		comment.setThreadId((comment.getAuthorName() + " " + comment.getPostDate().toString()));
		return comment;
	}

}
