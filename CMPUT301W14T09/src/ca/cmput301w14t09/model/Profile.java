package ca.cmput301w14t09.model.user;

import java.util.Collection;

import ca.cmput301w14t09.model.comment.Favorite;
import ca.cmput301w14t09.model.comment.UnreadMarker;





public class Profile {

	/**
	 * @uml.property  name="favorites"
	 */
	private java.util.ArrayList<Favorite> favorites;

	/**
	 * Getter of the property <tt>favorites</tt>
	 * @return  Returns the favorites.
	 * @uml.property  name="favorites"
	 */
	public java.util.ArrayList<Favorite> getFavorites() {
		return favorites;
	}

	/**
	 * Setter of the property <tt>favorites</tt>
	 * @param favorites  The favorites to set.
	 * @uml.property  name="favorites"
	 */
	public void setFavorites(java.util.ArrayList<Favorite> favorites) {
		this.favorites = favorites;
	}

	/**
	 * @uml.property  name="unreadComments"
	 */
	private java.util.ArrayList<UnreadMarker> unreadComments;

	/**
	 * Getter of the property <tt>unreadComments</tt>
	 * @return  Returns the unreadComments.
	 * @uml.property  name="unreadComments"
	 */
	public java.util.ArrayList<UnreadMarker> getUnreadComments() {
		return unreadComments;
	}

	/**
	 * Setter of the property <tt>unreadComments</tt>
	 * @param unreadComments  The unreadComments to set.
	 * @uml.property  name="unreadComments"
	 */
	public void setUnreadComments(java.util.ArrayList<UnreadMarker> unreadComments) {
		this.unreadComments = unreadComments;
	}

	/**
	 * @uml.property   name="favorite"
	 * @uml.associationEnd   inverse="profile:model.Favorite"
	 */
	private Favorite favorite;

	/**
	 * Getter of the property <tt>favorite</tt>
	 * @return  Returns the favorite.
	 * @uml.property  name="favorite"
	 */
	public Favorite getFavorite() {
		return favorite;
	}

	/**
	 * Setter of the property <tt>favorite</tt>
	 * @param favorite  The favorite to set.
	 * @uml.property  name="favorite"
	 */
	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}

	/**
	 * @uml.property   name="favorite1"
	 * @uml.associationEnd   multiplicity="(0 -1)" aggregation="composite" inverse="profile1:model.Favorite"
	 */
	private Collection<Favorite> favorite1;

	/** 
	 * Getter of the property <tt>favorite1</tt>
	 * @return  Returns the favorite1.
	 * @uml.property  name="favorite1"
	 */
	public Collection<Favorite> getFavorite1() {
		return favorite1;
	}

	/** 
	 * Setter of the property <tt>favorite1</tt>
	 * @param favorite1  The favorite1 to set.
	 * @uml.property  name="favorite1"
	 */
	public void setFavorite1(Collection<Favorite> favorite1) {
		this.favorite1 = favorite1;
	}

	/**
	 * @uml.property   name="unreadMarker"
	 * @uml.associationEnd   multiplicity="(0 -1)" aggregation="composite" inverse="profile:model.UnreadMarker"
	 */
	private Collection<UnreadMarker> unreadMarker;

	/** 
	 * Getter of the property <tt>unreadMarker</tt>
	 * @return  Returns the unreadMarker.
	 * @uml.property  name="unreadMarker"
	 */
	public Collection<UnreadMarker> getUnreadMarker() {
		return unreadMarker;
	}

	/**
	 * @uml.property  name="authorName"
	 */
	private String authorName;

	/**
	 * Getter of the property <tt>authorName</tt>
	 * @return  Returns the authorName.
	 * @uml.property  name="authorName"
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * Setter of the property <tt>authorName</tt>
	 * @param authorName  The authorName to set.
	 * @uml.property  name="authorName"
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/** 
	 * Setter of the property <tt>unreadMarker</tt>
	 * @param unreadMarker  The unreadMarker to set.
	 * @uml.property  name="unreadMarker"
	 */
	public void setUnreadMarker(Collection<UnreadMarker> unreadMarker) {
		this.unreadMarker = unreadMarker;
	}

	/**
	 * @uml.property   name="user"
	 * @uml.associationEnd   aggregation="composite" inverse="profile1:model.User"
	 */
	private User user;

	/** 
	 * Getter of the property <tt>user</tt>
	 * @return  Returns the user.
	 * @uml.property  name="user"
	 */
	public User getUser() {
		return user;
	}

	/** 
	 * Setter of the property <tt>user</tt>
	 * @param user  The user to set.
	 * @uml.property  name="user"
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
