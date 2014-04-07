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

import java.util.ArrayList;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import ca.cmput301w14t09.Controller.PictureController;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.ThreadAdapter;
import ca.cmput301w14t09.Model.User;

/**
 * 
 * @author Conner, Micheal
 * This activity is responsible for the favourite activity that is displayed when favourite button is clicked
 * by the user in TopCommentsActivity. It creates the menu and the list in which the users favourites are displayed.
 * 
 */
public class FavoriteActivity extends ListActivity
{

	//Initialize all activity vars
	public static final int OBTAIN_PIC_REQUEST_CODE = 117;
	public static final int MEDIA_TYPE_IMAGE = 1;
	PictureController pictureController;
	protected Intent intent;
	protected User user;
	protected Dialog dialog;
	protected ListView favList;
	Comment comment;
	ImageButton addPicImageButton;
	ImageView picImagePreview;
	Bitmap picture = null;
	EditText authorText;
	EditText commentText;
	ThreadAdapter adapter1;


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite);
		favList = (ListView)findViewById(android.R.id.list);

	}

	/**
	 * Function responsible for creating the menu and creating the list of the users 
	 * Favorites. 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favorite, menu);

		intent = getIntent();
		user = (User) intent.getSerializableExtra("CURRENT_USER"); 
		setList();

		return true;
	}

	/**
	 * onResume popluates the listview with results from
	 * elasticSearch, finding all of the top comments
	 * @param thread
	 */

	public void setList() {
		ArrayList<Comment> comments = user.getProfile().getFavorites();
		adapter1 = new ThreadAdapter(this,
				R.layout.thread_view, comments);
		favList.setAdapter(adapter1);
		adapter1.notifyDataSetChanged();

	}
}
