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

package ca.cmput301w14t09.FileManaging;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.content.Context;
import ca.cmput301w14t09.Model.User;

import com.google.gson.Gson;

/**
 * 
 * @author Conner
 * FileSaving has all saving functions for storing data on the 
 * current device
 * 
 */
public class FileSaving {
	private static final String FILENAME = "Username.sav";
	protected static User user;

	/**
	 * appendUserNameToList saves a new username to a list
	 * of usernames
	 * @param text
	 * @param main
	 */
	public static void appendUserNameToList(String text, Activity main) {
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

	/**
	 * saveUserFile saves all user critical data on
	 * the current device
	 * @param user
	 * @param main
	 */
	public static void saveUserFile(User user, Activity main) {
		Gson gson = new Gson();
		try {
			String jsonIn = gson.toJson(user);
			FileOutputStream fos = main.openFileOutput(user.getUserName() + ".sav",
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
