package ca.cmput301w14t09.model;

import java.util.Collection;




public class UnreadMarker {

	/**
	 * @uml.property  name="unread"
	 */
	private boolean unread = true;

	/**
	 * Getter of the property <tt>unread</tt>
	 * @return  Returns the unread.
	 * @uml.property  name="unread"
	 */
	public boolean isUnread() {
		return unread;
	}

	/**
	 * Setter of the property <tt>unread</tt>
	 * @param unread  The unread to set.
	 * @uml.property  name="unread"
	 */
	public void setUnread(boolean unread) {
		this.unread = unread;
	}

	/**
	 * @uml.property  name="comment"
	 */
	private Comment comment;

	/**
	 * Getter of the property <tt>comment</tt>
	 * @return  Returns the comment.
	 * @uml.property  name="comment"
	 */
	public Comment getComment() {
		return comment;
	}

	/**
	 * Setter of the property <tt>comment</tt>
	 * @param comment  The comment to set.
	 * @uml.property  name="comment"
	 */
	public void setComment(Comment comment) {
		this.comment = comment;
	}

	/**
	 * @uml.property   name="profile"
	 * @uml.associationEnd   multiplicity="(0 -1)" inverse="unreadMarker:model.Profile"
	 */
	private Collection<Profile> profile;

	/** 
	 * Getter of the property <tt>profile</tt>
	 * @return  Returns the profile.
	 * @uml.property  name="profile"
	 */
	public Collection<Profile> getProfile() {
		return profile;
	}

	/** 
	 * Setter of the property <tt>profile</tt>
	 * @param profile  The profile to set.
	 * @uml.property  name="profile"
	 */
	public void setProfile(Collection<Profile> profile) {
		this.profile = profile;
	}

	/**
	 * @uml.property   name="comment1"
	 * @uml.associationEnd   aggregation="composite" inverse="unreadMarker:model.Comment"
	 */
	private Comment comment1;

	/** 
	 * Getter of the property <tt>comment1</tt>
	 * @return  Returns the comment1.
	 * @uml.property  name="comment1"
	 */
	public Comment getComment1() {
		return comment1;
	}

	/** 
	 * Setter of the property <tt>comment1</tt>
	 * @param comment1  The comment1 to set.
	 * @uml.property  name="comment1"
	 */
	public void setComment1(Comment comment1) {
		this.comment1 = comment1;
	}

		
		/**
		 */
		public void generateNewMarkers(){
		}

}
