package ca.cmput301w14t09.model;

import java.util.ArrayList;

import ca.cmput301w14t09.model.Favorite;
import ca.cmput301w14t09.model.UnreadMarker;


/**
 * Tracks the settings of the associated user.
 * @author mcmorris
 */
public class Profile {
	private ArrayList<Favorite> favorites;
	private java.util.ArrayList<UnreadMarker> unreadComments;
	private User user;
	
	private String authorName;

	public Profile() {
		
	}

	/**
	 * @return the favorites
	 */
	public ArrayList<Favorite> getFavorites() {
		return favorites;
	}

	/**
	 * @param favorites the favorites to set
	 */
	public void setFavorites(ArrayList<Favorite> favorites) {
		this.favorites = favorites;
	}

	/**
	 * @return the unreadComments
	 */
	public java.util.ArrayList<UnreadMarker> getUnreadComments() {
		return unreadComments;
	}

	/**
	 * @param unreadComments the unreadComments to set
	 */
	public void setUnreadComments(java.util.ArrayList<UnreadMarker> unreadComments) {
		this.unreadComments = unreadComments;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	
}
