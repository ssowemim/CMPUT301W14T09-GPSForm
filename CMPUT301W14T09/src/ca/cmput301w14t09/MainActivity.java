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

package ca.cmput301w14t09;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import ca.cmput301w14t09.FileManaging.FileLoading;
import ca.cmput301w14t09.FileManaging.FileSaving;
import ca.cmput301w14t09.model.User;

/**
 * 
 * @author Conner
 * MainActivity of the app.
 * displays a list of current users on the device
 * and allows you to create a new user or sign
 * in as a guest
 */
public class MainActivity extends Activity {

	protected ListView UserList;
	protected EditText editText;
	protected User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editText = (EditText) findViewById(R.id.editUsername);
		UserList = (ListView) findViewById(R.id.UserList);

		//listener that loads the selected user from the list
		UserList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				String getUser = (String) (UserList.getItemAtPosition(arg2));
				user = new User();
				user = loadUser(getUser);
				topComments(user);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * onStart populates the listview with clickable usernames
	 * that have already been created on the device
	 */
	public void onStart() {
		super.onStart();
		String[] listName = FileLoading.loadFromFile(this);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_view, listName);
		UserList.setAdapter(adapter);

	}

	/**
	 * newUser is called when continue button is pressed
	 * this method is run.
	 * It grabs the username entered in the text field and
	 * creates a new user with it. Then starts the next 
	 * activity with the newly created user
	 * @param v
	 */
	public void newUser(View v) {
		user = new User(editText.getText().toString());
		FileSaving.appendUserNameToList(user.getUserName(), this);
		FileSaving.saveUserFile(user, this);
		topComments(user);
	}

	/**
	 * topComments takes in a username selected in the listview
	 * and starts the next activity with that user
	 * @param user
	 */
	public void topComments(User user) {
		Intent intent = new Intent(this, TopCommentsActivity.class);
		intent.putExtra("CURRENT_USER", user);
		startActivity(intent);
	}

	/**
	 * guestUser is called when the guest button is pressed
	 * Sets user to guest user and then calls
	 * topComments with the guest user
	 * @param v
	 */
	public void guestUser(View v) {
		user = new User("Guest");
		topComments(user);
	}

	/**
	 * loadUser calls FileLoading.returnUser(text, this)
	 * which gets a user and then returns the user that
	 * FileLoading.returnUser(text, this) gets
	 * @param text
	 * @return
	 */
	public User loadUser(String text) {
		user = FileLoading.returnUser(text, this);
		return user;
	}

}
