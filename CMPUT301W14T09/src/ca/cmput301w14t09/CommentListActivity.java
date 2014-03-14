package ca.cmput301w14t09;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * 
 * @author Conner
 *This activity will need to be implemented when we can generate comments 
 *and add them to the list view on the TopComments Activity
 *
 */
public class CommentListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comment_list, menu);
		return true;
	}

}
