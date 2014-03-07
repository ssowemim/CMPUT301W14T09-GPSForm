package ca.cmput301w14t09.model;

import java.util.Collection;



public class Picture {

	private static int MAX_SIZE = 100;
	private Collection<Comment> comment;

	public boolean validatePictureSize(android.graphics.Bitmap picture){
		return false;	
	}

	public Collection<Comment> getComment() {
		return comment;
	}

	public void setComment(Collection<Comment> comment) {
		this.comment = comment;
	}

}
