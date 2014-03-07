package ca.cmput301w14t09.FileManaging;

import ca.cmput301w14t09.model.Comment;

public class CreateComment {
	
	public static Comment newComment(String authorname, String commentText){
		Comment comment = new Comment();
		comment.setAuthorName(authorname);
		comment.setCommentText(commentText);
		return comment;
	}

}
