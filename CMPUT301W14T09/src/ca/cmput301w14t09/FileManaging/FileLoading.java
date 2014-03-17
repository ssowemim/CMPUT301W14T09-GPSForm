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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.google.gson.Gson;
import android.app.Activity;
import ca.cmput301w14t09.model.User;

/**
 * 
 * @author Conner
 * FileLoading loads data stored on the device when 
 * requested by a method
 * 
 */

public class FileLoading {

	private static final String FILENAME = "Username.sav";
	protected static User user;
	protected String name;

	/**
	 * loadFromFile loads in a list of usernames
	 * from the device
	 * @param main
	 * @return
	 */

	public static String[] loadFromFile(Activity main) {
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

	/**
	 * returnUser loads a specific user and 
	 * user data from the device
	 * @param name
	 * @param main
	 * @return
	 */

	public static User returnUser(String name, Activity main) {
		Gson gson = new Gson();

		try{
			FileInputStream fis = main.openFileInput(name + ".sav");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buff = new BufferedReader(isr);
			String jsonOut = buff.readLine();
			user = gson.fromJson(jsonOut, User.class);
			buff.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return user;
	}

}
