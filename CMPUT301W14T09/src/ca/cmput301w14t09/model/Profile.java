package ca.cmput301w14t09.model;

import java.util.Collection;





public class Profile {

	private java.util.ArrayList<Favorite> favorites;
	private java.util.ArrayList<UnreadMarker> unreadComments;
	private Favorite favorite;
	private Collection<Favorite> favorite1;

	
	public java.util.ArrayList<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(java.util.ArrayList<Favorite> favorites) {
		this.favorites = favorites;
	}

	public java.util.ArrayList<UnreadMarker> getUnreadComments() {
		return unreadComments;
	}

	
	public void setUnreadComments(java.util.ArrayList<UnreadMarker> unreadComments) {
		this.unreadComments = unreadComments;
	}

	public Favorite getFavorite() {
		return favorite;
	}

	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}

	public Collection<Favorite> getFavorite1() {
		return favorite1;
	}
}