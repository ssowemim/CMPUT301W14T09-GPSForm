package ca.cmput301w14t09;


import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.FileManaging.CreateComment;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import ca.cmput301w14t09.model.CommentThread;
import ca.cmput301w14t09.model.User;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

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
		
		favList.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

				String getCommentText = (String) (favList.getItemAtPosition(arg2)); 
				dialog = new Dialog(CommentListActivity.this);

				dialog.setContentView(R.layout.pop_up_favorite);
				dialog.setTitle("Favorite Dialog");


				Button favorites=(Button)dialog.findViewById(R.id.favButton);
				

				
				dialog.show();
				
				//cancel button
				favorites.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();

					}
				});
			}

		});
		
		
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
		/*CommentThread[] topComments;
		try {
			topComments = ElasticSearchOperations.pullThreads();
			ArrayAdapter<CommentThread> adapter = new ArrayAdapter<CommentThread>(this,R.layout.list_view, topComments);
			favList.setAdapter(adapter);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/

	}
	
	public void saveFavorites(View v){
		
	}

	// Needs to be present for clicking 
	@Override
	public void onClick(View arg0) {
	
	}


}
