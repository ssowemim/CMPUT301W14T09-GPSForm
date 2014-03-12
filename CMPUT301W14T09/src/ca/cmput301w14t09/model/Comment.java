package ca.cmput301w14t09.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;

import ca.cmput301w14t09.model.GeoLocation;


/**
 * Comment is an instance of any posted comment.  Support for node navigation is provided by Thread.
 * Implements
 * ICacheable<Comment> - Serialization and Load support from file.
 * Comparable<Comment> - Allows for sorting on Comments
 * @author mcmorris
 */
public class Comment implements ICacheable<Comment>, Comparable<Comment> {
	private Thread thread;
	private GeoLocation geoLocation;
	private Picture attachment;
	private String authorName;
	private String commentText;
	private Date postDate;
	
	private int favoriteCount;
	private Boolean topComment;
	private String threadId;
	
	public Comment() {
		super();
		geoLocation = null;
		attachment = null;
		authorName = "";
		commentText = "";
		favoriteCount = 0;
	}
	
	/**
	 * Write this object to cache using GSon.
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
	 * Loads this object, specified by name, from cache with userName.sav
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
	 * Compares this Comment to another Comment.
	 * @param otherComment - other comment object to compare to.
	 * @return - this or otherComment, whichever has earlier date.
	 */
	public int compareTo(Comment otherComment) {
		Date compareDate = ((Comment) otherComment).getPostDate();
		return this.postDate.compareTo(compareDate);
	}
	
	/**
	 * @return the geoLocation
	 */
	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	/**
	 * @param geoLocation the geoLocation to set
	 */
	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	/**
	 * @return the attachment
	 */
	public Picture getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(Picture attachment) {
		this.attachment = attachment;
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

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the favoriteCount
	 */
	public int getFavoriteCount() {
		return favoriteCount;
	}

	/**
	 * @param favoriteCount the favoriteCount to set
	 */
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	
	/**
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
	 * @return the thread
	 */
	public Thread getThread() {
		return thread;
	}

	/**
	 * @param thread the thread to set
	 */
	public void setThread(Thread thread) {
		this.thread = thread;
	}

	/**
	 * @return the topComment
	 */
	public Boolean getTopComment() {
		return topComment;
	}

	/**
	 * @param topComment the topComment to set
	 */
	public void setTopComment(Boolean topComment) {
		this.topComment = topComment;
	}

	/**
	 * @return the threadId
	 */
	public String getThreadId() {
		return threadId;
	}

	/**
	 * @param threadId the threadId to set
	 */
	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

}
