package ca.cmput301w14t09.model;

import java.util.Collection;
import java.util.Date;

import ca.cmput301w14t09.view.EditCommentActivity;




public class Comment {

	
	private Favorite favorite;

	
	public Favorite getFavorite() {
		return favorite;
	}

	
	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}

	
	private Picture attachment;

	
	public Picture getAttachment() {
		return attachment;
	}

	
	public void setAttachment(Picture attachment) {
		this.attachment = attachment;
	}

	
	private Comment parent;

	
	public Comment getParent() {
		return parent;
	}


	private Date postDate;

	
	public Date getPostDate() {
		return postDate;
	}


	private GeoLocation geoLocation;

	
	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	
	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	
	private Area area;

	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	
	private String authorName;

	
	public String getAuthorName() {
		return authorName;
	}

	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	
	private String commentText;

	public String getCommentText() {
		return commentText;
	}

	
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	
	private Favorite favorite1;

	
	public Favorite getFavorite1() {
		return favorite1;
	}

	
	public void setFavorite1(Favorite favorite1) {
		this.favorite1 = favorite1;
	}


	private UnreadMarker unreadMarker;

	
	public UnreadMarker getUnreadMarker() {
		return unreadMarker;
	}

	
	public void setUnreadMarker(UnreadMarker unreadMarker) {
		this.unreadMarker = unreadMarker;
	}

	
	private Collection<GeoLocation> geoLocation1;

	
	public Collection<GeoLocation> getGeoLocation1() {
		return geoLocation1;
	}

	public void setGeoLocation1(Collection<GeoLocation> geoLocation1) {
		this.geoLocation1 = geoLocation1;
	}

	
	private Picture picture;

	
	public Picture getPicture() {
		return picture;
	}

	
	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	private int favoriteCount = 0;

	public int getFavoriteCount() {
		return favoriteCount;
	}

	
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	
	private EditCommentActivity editCommentActivity;

	
	public EditCommentActivity getEditCommentActivity() {
		return editCommentActivity;
	}

	
	public void setEditCommentActivity(EditCommentActivity editCommentActivity) {
		this.editCommentActivity = editCommentActivity;
	}

}
