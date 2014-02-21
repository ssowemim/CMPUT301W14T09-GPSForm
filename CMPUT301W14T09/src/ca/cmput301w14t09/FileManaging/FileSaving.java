package ca.cmput301w14t09.FileManaging;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import ca.cmput301w14t09.model.User;

import com.google.gson.Gson;

public class FileSaving {

	private static final String FILENAME = "Username.sav";
	protected static User user;


	public static void saveInFile(String text, Activity main) {
		try {
			FileOutputStream fos = main.openFileOutput(FILENAME,
					Context.MODE_APPEND);
			fos.write(new String(text + "\n")
			.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void userInFile(String text, int count, Activity main) {
		user = new User();
		user.setUserName(text);
		
		Gson gson = new Gson();
		String jsonIn = gson.toJson(user);           

		try{
			FileOutputStream fos = main.openFileOutput(text + ".sav",
					Context.MODE_PRIVATE );
			fos.write(jsonIn.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
