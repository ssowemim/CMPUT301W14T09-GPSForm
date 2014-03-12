package ca.cmput301w14t09.model.comment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;

/**
 * UnreadMarker is currently a mildly glorified boolean flag that sets whether a comment has been
 * read, and generates new markers for new comments with no related UnreadMarker.  This is done
 * on a per Profile basis.
 * @author mcmorris
 */
public class UnreadMarker implements ICacheable<UnreadMarker>, Comparable<UnreadMarker> {
	private boolean unread;
	private Comment comment;
	
	public UnreadMarker() {
		unread = true;
	}
	
	/**
	 * Constructor.
	 * @param unread - is this comment unread?  T/F
	 * @param comment - The comment this unread marker marks.
	 */
	public UnreadMarker(boolean unread, Comment comment) {
		this.unread = unread;
		this.comment = comment;
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
	public UnreadMarker load(String userName, String name, Activity main) {
        Gson gson = new Gson();
        UnreadMarker unreadMarker = null;
        
        try{
            FileInputStream fis = main.openFileInput(userName + ".sav");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader buff = new BufferedReader(isr);
            String jsonOut = buff.readLine();
            unreadMarker = gson.fromJson(jsonOut, UnreadMarker.class);
            buff.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return unreadMarker;
	}
	
	/**
	 * Compares this UnreadMarker to another UnreadMarker.
	 * @param otherComment - other comment object to compare to.
	 * @return - this or otherComment, whichever has earlier date.
	 */
	public int compareTo(UnreadMarker otherMarker) {
		Date compareDate = ((UnreadMarker) otherMarker).comment.getPostDate();
		return this.comment.getPostDate().compareTo(compareDate);
	}

	public boolean isUnread() {
		return unread;
	}


	public void setUnread(boolean unread) {
		this.unread = unread;
	}

	public Comment getComment() {
		return comment;
	}


	public void setComment(Comment comment) {
		this.comment = comment;
	}


	/**
	 * Go through all comments, check a read flag exists for each one.
	 * TODO: I'm pretty sure this won't actually work - oldMarkers is made up of UnreadMarkers, so it is logical
	 * that it won't say it "contains" a Comment.
	*/
	public ArrayList<UnreadMarker> generateNewMarkers(ArrayList<UnreadMarker> oldMarkers, ArrayList<Comment> allComments) {
		ArrayList<UnreadMarker> markers = new ArrayList<UnreadMarker>();
		boolean isRead = false;
		
		// Compare each comment node, and mark if read only if it is already read in oldComments.
		for(int index = 0; index < allComments.size(); index++) {
			isRead = false;
			
			if (oldMarkers.contains(allComments.get(index)) == true)
					isRead = true;

			markers.add(new UnreadMarker(isRead, comment));
		}
		
		return markers;
	}

}