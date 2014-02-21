package ca.cmput301w14t09.FileManaging;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import ca.cmput301w14t09.model.User;

public class FileLoading {
	
	private static final String FILENAME = "Username.sav";
	protected static User user;
	protected String name;
	
	public static String[] loadFromFile(Activity main){
		 ArrayList<String> usernames = new ArrayList<String>();
	        try {
	            FileInputStream fis = main.openFileInput(FILENAME);
	            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
	            String line = in.readLine();
	            while (line != null) {
	                usernames.add(line);
	                line = in.readLine();
	            }

	        } catch (FileNotFoundException e) {

	            e.printStackTrace();
	        } catch (IOException e) {

	            e.printStackTrace();
	        }
	        return usernames.toArray(new String[usernames.size()]);
	}

}
