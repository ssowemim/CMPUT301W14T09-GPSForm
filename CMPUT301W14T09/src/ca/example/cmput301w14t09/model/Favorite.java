package model;

import java.util.Collection;




public class Favorite {

	/**
	 * @uml.property  name="comment" readOnly="true"
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
	 * @uml.property   name="comment1"
	 * @uml.associationEnd   inverse="favorite:model.Comment"
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
	 * @uml.property  name="name"
	 */
	private String name;

	/**
	 * Getter of the property <tt>name</tt>
	 * @return  Returns the name.
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter of the property <tt>name</tt>
	 * @param name  The name to set.
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @uml.property   name="profile"
	 * @uml.associationEnd   inverse="favorite:model.Profile"
	 */
	private Profile profile;

	/**
	 * Getter of the property <tt>profile</tt>
	 * @return  Returns the profile.
	 * @uml.property  name="profile"
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * Setter of the property <tt>profile</tt>
	 * @param profile  The profile to set.
	 * @uml.property  name="profile"
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * @uml.property   name="profile1"
	 * @uml.associationEnd   multiplicity="(0 -1)" inverse="favorite1:model.Profile"
	 */
	private Collection<Profile> profile1;

	/** 
	 * Getter of the property <tt>profile1</tt>
	 * @return  Returns the profile1.
	 * @uml.property  name="profile1"
	 */
	public Collection<Profile> getProfile1() {
		return profile1;
	}

	/** 
	 * Setter of the property <tt>profile1</tt>
	 * @param profile1  The profile1 to set.
	 * @uml.property  name="profile1"
	 */
	public void setProfile1(Collection<Profile> profile1) {
		this.profile1 = profile1;
	}

	/**
	 * @uml.property   name="comment2"
	 * @uml.associationEnd   aggregation="composite" inverse="favorite1:model.Comment"
	 */
	private Comment comment2;

	/** 
	 * Getter of the property <tt>comment2</tt>
	 * @return  Returns the comment2.
	 * @uml.property  name="comment2"
	 */
	public Comment getComment2() {
		return comment2;
	}

	/** 
	 * Setter of the property <tt>comment2</tt>
	 * @param comment2  The comment2 to set.
	 * @uml.property  name="comment2"
	 */
	public void setComment2(Comment comment2) {
		this.comment2 = comment2;
	}

}
