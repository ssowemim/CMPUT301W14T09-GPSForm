package ca.cmput301w14t09.FileManaging;

import ca.cmput301w14t09.model.Comment;

public class CreateComment {
	
	public static Comment newComment(String authorname, String commentText, Boolean topComment){
		Comment comment = new Comment();
		comment.setAuthorName(authorname);
		comment.setCommentText(commentText);
		comment.setBoolean(topComment);
		comment.setPostDate();
		comment.setThreadId((comment.getAuthorName() + " " + comment.getPostDate().toString()));
		return comment;
	}

}
