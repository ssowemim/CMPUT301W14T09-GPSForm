package ca.cmput301w14t09.model;

import java.util.Collection;




public class UnreadMarker {

	private boolean unread = true;
	private Comment comment;
	private Collection<Profile> profile;
	private Comment comment1;

	public boolean isUnread() {
		return unread;
	}

	public void setUnread(boolean unread) {
		this.unread = unread;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Collection<Profile> getProfile() {
		return profile;
	}

	public void setProfile(Collection<Profile> profile) {
		this.profile = profile;
	}

	public Comment getComment1() {
		return comment1;
	}

	public void setComment1(Comment comment1) {
		this.comment1 = comment1;
	}
	
	public void generateNewMarkers(){
	}

}
