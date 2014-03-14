package ca.cmput301w14t09.model;

import java.util.Date;
import java.util.LinkedList;

/**
 * Thread holds a LinkedList of LinkNodes, each of which is added with a comment.
 * List accounts for LinkedList traversal intelligence, and tracks size and other
 * relevant data.
 * @author mcmorris
 */
public class CommentThread implements Comparable<CommentThread> {
	private LinkedList<Comment> comments;
	private Date lastUpdated;
	private String name;

	public CommentThread() {
		comments = new LinkedList<Comment>();
		lastUpdated = new Date();
		name = "";
	}
	
	/**
	 * 
	 * @param comment - comment to attach the thread.
	 */
	public void addToThread(Comment comment) {
		// Set comment thread.
		this.lastUpdated = comment.getPostDate();
		//comment.setThread(this);
		comment.setPostDate(new Date());
		
		comments.addLast(comment);
	}
	
	/**
	 * Compares this Comment to another Comment.
	 */
	public int compareTo(CommentThread otherThread) {
		Date compareDate = ((CommentThread) otherThread).getLastUpdated();
		return this.getLastUpdated().compareTo(compareDate);
	}

	/**
	 * @return the comments
	 */
	public LinkedList<Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(LinkedList<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * @return the lastUpdated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}