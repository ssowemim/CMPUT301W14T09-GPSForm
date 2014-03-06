package ca.cmput301w14t09.model;

import java.util.Collection;
import java.util.Date;


import ca.cmput301w14t09.GeoLocation;
import ca.cmput301w14t09.view.EditCommentActivity;




public class Comment {

	private EditCommentActivity editCommentActivity;
	private Favorite favorite;
	private Picture attachment;
	private Comment parent;
	private Date postDate;
	private GeoLocation geoLocation;
	private Area area;
	private String authorName;
	private String commentText;
	private Favorite favorite1;
	private UnreadMarker unreadMarker;
	private Collection<GeoLocation> geoLocation1;
	private Picture picture;
	private int favoriteCount = 0;
	
	public Favorite getFavorite() {
		return favorite;
	}

	
	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}

	public Picture getAttachment() {
		return attachment;
	}

	public void setAttachment(Picture attachment) {
		this.attachment = attachment;
	}

	public Comment getParent() {
		return parent;
	}

	public Date getPostDate() {
		return postDate;
	}

	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

<<<<<<< HEAD

=======
>>>>>>> geobranch
	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

<<<<<<< HEAD
	public Area getArea() {
		return area;
	}


	public void setArea(Area area) {
		this.area = area;
	}

=======
	private String authorName;

	
>>>>>>> geobranch
	public String getAuthorName() {
		return authorName;
	}

	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

<<<<<<< HEAD
=======
	
	private String commentText;

	
>>>>>>> geobranch
	public String getCommentText() {
		return commentText;
	}

	
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

<<<<<<< HEAD
=======
	
	private Favorite favorite1;

	
>>>>>>> geobranch
	public Favorite getFavorite1() {
		return favorite1;
	}

<<<<<<< HEAD
=======
	
>>>>>>> geobranch
	public void setFavorite1(Favorite favorite1) {
		this.favorite1 = favorite1;
	}

<<<<<<< HEAD
=======
	
	private UnreadMarker unreadMarker;

	
>>>>>>> geobranch
	public UnreadMarker getUnreadMarker() {
		return unreadMarker;
	}

	
	public void setUnreadMarker(UnreadMarker unreadMarker) {
		this.unreadMarker = unreadMarker;
	}

	public Collection<GeoLocation> getGeoLocation1() {
		return geoLocation1;
	}

	
	public void setGeoLocation1(Collection<GeoLocation> geoLocation1) {
		this.geoLocation1 = geoLocation1;
	}

	public Picture getPicture() {
		return picture;
	}

	
	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	
	public int getFavoriteCount() {
		return favoriteCount;
	}

	
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}


	public EditCommentActivity getEditCommentActivity() {
		return editCommentActivity;
	}

	public void setEditCommentActivity(EditCommentActivity editCommentActivity) {
		this.editCommentActivity = editCommentActivity;
	}

}
