package ca.cmput301w14t09.model;

import java.util.Collection;
import java.util.Date;


import ca.cmput301w14t09.GeoLocation;
import ca.cmput301w14t09.view.EditCommentActivity;




public class Comment {

	/**
	 * @uml.property   name="favorite"
	 * @uml.associationEnd   inverse="comment1:model.Favorite"
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
	 * @uml.property  name="attachment"
	 */
	private Picture attachment;

	/**
	 * Getter of the property <tt>attachment</tt>
	 * @return  Returns the attachment.
	 * @uml.property  name="attachment"
	 */
	public Picture getAttachment() {
		return attachment;
	}

	/**
	 * Setter of the property <tt>attachment</tt>
	 * @param attachment  The attachment to set.
	 * @uml.property  name="attachment"
	 */
	public void setAttachment(Picture attachment) {
		this.attachment = attachment;
	}

	/**
	 * @uml.property  name="parent" readOnly="true"
	 */
	private Comment parent;

	/**
	 * Getter of the property <tt>parent</tt>
	 * @return  Returns the parent.
	 * @uml.property  name="parent"
	 */
	public Comment getParent() {
		return parent;
	}

	/**
	 * @uml.property  name="postDate" readOnly="true"
	 */
	private Date postDate;

	/**
	 * Getter of the property <tt>postDate</tt>
	 * @return  Returns the postDate.
	 * @uml.property  name="postDate"
	 */
	public Date getPostDate() {
		return postDate;
	}

	/**
	 * @uml.property  name="geoLocation"
	 */
	private GeoLocation geoLocation;

	/**
	 * Getter of the property <tt>geoLocation</tt>
	 * @return  Returns the geoLocation.
	 * @uml.property  name="geoLocation"
	 */
	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
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

	/** 
	 * Setter of the property <tt>unreadMarker</tt>
	 * @param unreadMarker  The unreadMarker to set.
	 * @uml.property  name="unreadMarker"
	 */
	public void setUnreadMarker(UnreadMarker unreadMarker) {
		this.unreadMarker = unreadMarker;
	}

	/**
	 * @uml.property   name="geoLocation1"
	 * @uml.associationEnd   multiplicity="(0 -1)" aggregation="composite" inverse="comment:model.GeoLocation"
	 */
	private Collection<GeoLocation> geoLocation1;

	/** 
	 * Getter of the property <tt>geoLocation1</tt>
	 * @return  Returns the geoLocation1.
	 * @uml.property  name="geoLocation1"
	 */
	public Collection<GeoLocation> getGeoLocation1() {
		return geoLocation1;
	}

	/** 
	 * Setter of the property <tt>geoLocation1</tt>
	 * @param geoLocation1  The geoLocation1 to set.
	 * @uml.property  name="geoLocation1"
	 */
	public void setGeoLocation1(Collection<GeoLocation> geoLocation1) {
		this.geoLocation1 = geoLocation1;
	}

	/**
	 * @uml.property   name="picture"
	 * @uml.associationEnd   aggregation="composite" inverse="comment:model.Picture"
	 */
	private Picture picture;

	/** 
	 * Getter of the property <tt>picture</tt>
	 * @return  Returns the picture.
	 * @uml.property  name="picture"
	 */
	public Picture getPicture() {
		return picture;
	}

	/** 
	 * Setter of the property <tt>picture</tt>
	 * @param picture  The picture to set.
	 * @uml.property  name="picture"
	 */
	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	/**
	 * @uml.property  name="favoriteCount"
	 */
	private int favoriteCount = 0;

	/**
	 * Getter of the property <tt>favoriteCount</tt>
	 * @return  Returns the favoriteCount.
	 * @uml.property  name="favoriteCount"
	 */
	public int getFavoriteCount() {
		return favoriteCount;
	}

	/**
	 * Setter of the property <tt>favoriteCount</tt>
	 * @param favoriteCount  The favoriteCount to set.
	 * @uml.property  name="favoriteCount"
	 */
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	/**
	 * @uml.property  name="editCommentActivity"
	 * @uml.associationEnd  inverse="comment:view.EditCommentActivity"
	 */
	private EditCommentActivity editCommentActivity;

	/**
	 * Getter of the property <tt>editCommentActivity</tt>
	 * @return  Returns the editCommentActivity.
	 * @uml.property  name="editCommentActivity"
	 */
	public EditCommentActivity getEditCommentActivity() {
		return editCommentActivity;
	}

	/**
	 * Setter of the property <tt>editCommentActivity</tt>
	 * @param editCommentActivity  The editCommentActivity to set.
	 * @uml.property  name="editCommentActivity"
	 */
	public void setEditCommentActivity(EditCommentActivity editCommentActivity) {
		this.editCommentActivity = editCommentActivity;
	}

}
