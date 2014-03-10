package ca.cmput301w14t09.model;

import java.util.Collection;
import java.util.Date;

import ca.cmput301w14t09.view.EditCommentActivity;




public class Comment {

	
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
	private Picture picture;
	private int favoriteCount = 0;
	private EditCommentActivity editCommentActivity;
	private Collection<GeoLocation> geoLocation1;
	
	private Boolean topComment;
	
	public Favorite getFavorite() {
		return favorite;
	}

	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}
	
	public Boolean getBoolean() {
		return topComment;
	}

	public void setBoolean(Boolean topcomment) {
		this.topComment = topcomment;
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
	
	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getCommentText() {
		return commentText;
	}
	
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Favorite getFavorite1() {
		return favorite1;
	}
	
	public void setFavorite1(Favorite favorite1) {
		this.favorite1 = favorite1;
	}
	
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
