/**

License GPLv3: GNU GPL Version 3
<http://gnu.org/licenses/gpl.html>.
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package ca.cmput301w14t09.Model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Date;
import android.app.Activity;
import android.content.Context;

import ca.cmput301w14t09.FileManaging.SerializableBitmap;

import com.google.gson.Gson;

/**
 * 
 * @author mcmorris
 * Comment is an instance of any posted comment.  Support for node navigation is provided by Thread.
 * Implements
 * ICacheable<Comment> - Serialization and Load support from file.
 * Comparable<Comment> - Allows for sorting on Comments
 * 
 */
public class Comment implements Comparable<Comment>, Serializable {

	private static final long serialVersionUID = 3L;
	private GeoLocation geoLocation;
	private SerializableBitmap picture;
	private String authorName;
	private String commentText;
	private Date postDate;
	private int favoriteCount;
	private Boolean topComment;
	private String threadId;
	private Boolean hasPicture;
	private String userName;
	private String uuid; 

	public Comment() {
		super();
		geoLocation = null;
		picture = null;
		authorName = "";
		commentText = "";
		favoriteCount = 0;
	}

	/**
	 * serialize writes this object to cache using GSon.
	 * https://github.com/Mrbilec/CMPUT301W14T09-GPSForm/blob/saveBranch/CMPUT301W14T09/src/ca/cmput301w14t09/FileManaging/FileSaving.java
	 * @param userName - name of current user (cache is user-based)
	 * @param main - activity calling this function.
	 */

	public void serialize(String userName, Activity main) {
		Gson gson = new Gson();
		String jsonIn = gson.toJson(this);           

		try {
			FileOutputStream fos = main.openFileOutput(userName + ".sav",
					Context.MODE_PRIVATE );
			fos.write(jsonIn.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * load loads this object, specified by name, from cache with userName.sav
	 * @param userName - name of current user (cache is user-based)
	 * @param name - name of the file object itself?
	 * @param main - activity calling this function.
	 * @return - the loaded comment.
	 */

	public Comment load(String userName, String name, Activity main) {
		Gson gson = new Gson();
		Comment comment = null;
		try{
			FileInputStream fis = main.openFileInput(userName + ".sav");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buff = new BufferedReader(isr);
			String jsonOut = buff.readLine();
			comment = gson.fromJson(jsonOut, Comment.class);
			buff.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return comment;
	}

	/**
	 * compareTo compares this Comment to another Comment.
	 * @param otherComment - other comment object to compare to.
	 * @return - this or otherComment, whichever has earlier date.
	 */
	public int compareTo(Comment otherComment) {
		Date compareDate = ((Comment) otherComment).getPostDate();
		return this.postDate.compareTo(compareDate);
	}

	/**
	 * returns geolocation
	 * @return the geoLocation
	 */
	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	/**
	 * set geolocation
	 * @param geoLocation the geoLocation to set
	 */
	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	/**
	 * return picture
	 * @return
	 */
	public SerializableBitmap getPicture(){
		return picture;
	}

	/**
	 * set picture
	 * @param picture
	 */
	public void setPicture(SerializableBitmap picture){
		this.picture = picture;
	}
	
	/**
	 * get author name
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * set author name
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * get comment text
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * set comment text
	 * @param commentText the commentText to set
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 * get favorite count
	 * @return the favoriteCount
	 */
	public int getFavoriteCount() {
		return favoriteCount;
	}

	/**
	 * set favorite count
	 * @param favoriteCount the favoriteCount to set
	 */
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	/**
	 * get post date
	 * @return the postDate
	 */
	public Date getPostDate() {
		return postDate;
	}

	/**
	 * Set the post date variable. 
	 * @param now
	 */
	public void setPostDate(Date now) {
		this.postDate = now;
	}

	/**
	 * get if topComment = true, else false;
	 * @return the topComment
	 */
	public Boolean getTopComment() {
		return topComment;
	}

	/**
	 * set 'if top comment'
	 * @param topComment the topComment to set
	 */
	public void setTopComment(Boolean topComment) {
		this.topComment = topComment;
	}

	/**
	 * get threadId
	 * @return the threadId
	 */
	public String getThreadId() {
		return threadId;
	}

	/**
	 * set threadID
	 * @param threadId the threadId to set
	 */
	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	/**
	 * set hasPicture
	 * @param hasPicture
	 */
	public void setHasPicture(Boolean hasPicture){
		this.hasPicture = hasPicture;
	}
	
	/**
	 * get hasPicture
	 * @return Boolean
	 */
	public Boolean getHasPicture(){
		return hasPicture;
	}

	/**
	 * get user name
	 * @return the userName
	 */
	public String getUserName(){
		return userName;
	}

	/**
	 * set user name
	 * @param userName the userName to set
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}

	/**
	 * get unique ID
	 * @return the uuid
	 */
	public String getUuid(){
		return uuid;
	}

	/**
	 * set unique ID
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid){
		this.uuid = uuid;
	}
}
