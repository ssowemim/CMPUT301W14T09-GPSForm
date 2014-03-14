package ca.cmput301w14t09;


import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import ca.cmput301w14t09.model.User;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * 
 * @author Conner
 * @editor Chun-Han Lee
 *This activity will need to be implemented when we can generate comments 
 *and add them to the list view on the TopComments Activity
 *
 */
public class CommentListActivity extends Activity implements OnClickListener{

	protected ListView favList;
	protected Dialog dialog;
	protected User user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment_list);
		favList = (ListView) findViewById(R.id.commentView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comment_list, menu);
		return true;
	} 
	// From TopCommentsActivity code 
	@Override
	protected void onStart(){
		super.onStart();
		String[] topComments;
		try {
			topComments = ElasticSearchOperations.pullThreads();
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_view, topComments);
			favList.setAdapter(adapter);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public void saveFavorites(View v){
		
	}

	// Needs to be present for clicking 
	@Override
	public void onClick(View arg0) {
	
	}


}
