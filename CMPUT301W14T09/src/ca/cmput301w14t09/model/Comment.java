package ca.cmput301w14t09.model;

import java.util.Collection;
import java.util.Date;


public class Comment {

	
	private Favorite favorite;
	private Picture attachment;
	private Comment parent;
	private Date postDate;
	private String authorName;
	private String commentText;
	private UnreadMarker unreadMarker;
	private Picture picture;
	private int favoriteCount = 0;
	private Collection<GeoLocation> geoLocation1;
	private Boolean topComment;
	private String threadId;
	
	public Comment(){
		super();
		postDate = new Date();
	}
	
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
	
	public void setPostDate() {
		this.postDate = postDate;
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

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
}
