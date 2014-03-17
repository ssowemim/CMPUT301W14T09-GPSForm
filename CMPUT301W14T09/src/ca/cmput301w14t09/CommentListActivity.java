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
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.CommentAdapter;
import ca.cmput301w14t09.Model.User;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;

/**
 * 
 * @author Conner
 * @editor Chun-Han Lee
 *This activity shows the top comment that was selected in a 
 *previous activity and displays all the replies to that comment
 *
 */
public class CommentListActivity extends ListActivity {

	protected ListView favList;
	protected Dialog dialog;
	protected User user;
	protected Intent intent;
	protected String firstComment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment_list);
		favList = (ListView) findViewById(android.R.id.list);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comment_list, menu);

		firstComment = (String) getIntent().getSerializableExtra("THREAD_ID");
		System.out.println(firstComment);


		return true;
	}

	/**
	 * onStart populates the listview with results from the elasticSearch
	 *  query found in ElasticSearchOperations.pullOneThread(firstComment)
	 */
	@Override
	protected void onStart() {
		super.onStart();

		try {
			ArrayList<Comment> comment = ElasticSearchOperations.pullOneThread(firstComment);

			CommentAdapter adapter = new CommentAdapter(this,
					R.layout.comment_view, comment);
			favList.setAdapter(adapter);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}



	public void saveFavorites(View v) {
		onStart();
	}

	
}
