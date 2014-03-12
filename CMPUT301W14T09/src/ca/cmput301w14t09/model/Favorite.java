package ca.cmput301w14t09.model.comment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;

/**
 * Marks the member variable Comment as favorited.  Favorites can have their own favorite names.
 * Primarily included for flexible design going forward.
 * @author mcmorris
 */
public class Favorite implements ICacheable<Favorite>, Comparable<Favorite> {
	private String name;
	private Comment comment;

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
	public Favorite load(String userName, String name, Activity main) {
        Gson gson = new Gson();
        Favorite favorite = null;
        
        try{
            FileInputStream fis = main.openFileInput(userName + ".sav");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader buff = new BufferedReader(isr);
            String jsonOut = buff.readLine();
            favorite = gson.fromJson(jsonOut, Favorite.class);
            buff.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return favorite;
	}
	
	/**
	 * Compares this Favorite to another Favorite.
	 * @param otherFavorite - other Favorite object to compare to.
	 * @return - this or otherFavorite, whichever has earlier date.
	 */
	public int compareTo(Favorite otherFavorite) {
		Date compareDate = ((Favorite) otherFavorite).comment.getPostDate();
		return this.comment.getPostDate().compareTo(compareDate);
	}
	
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}