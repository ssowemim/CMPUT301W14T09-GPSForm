package ca.cmput301w14t09;



import ElasticSearch.ElasticSearchOperations;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import ca.cmput301w14t09.model.Comment;
import ca.cmput301w14t09.model.User;

public class TopCommentsActivity extends Activity {
	
	protected Intent intent;
	protected User user;
	protected Dialog dialog;
	protected ListView commentList;
	Comment comment;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_top_comments);
		
		commentList = (ListView) findViewById(R.id.CommentList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.top_comments, menu);
		
		intent = getIntent();
		user = (User) intent.getSerializableExtra("CURRENT_USER");
		
		return true;
		
		
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		String[] topComments = ElasticSearchOperations.pullTopComments();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_view, topComments);
		commentList.setAdapter(adapter);
		
	}
	
	public void popUp(View v){
		
		dialog = new Dialog(this);

        dialog.setContentView(R.layout.pop_up_comment);
        dialog.setTitle("New Top Comment");

        EditText editText=(EditText)dialog.findViewById(R.id.editText);
        Button save=(Button)dialog.findViewById(R.id.save);
        Button btnCancel=(Button)dialog.findViewById(R.id.cancel);
        dialog.show();
        
        btnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				
			}
		});
        
        save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				comment = new Comment();
				comment.setCommentText("this is a test top comment");
				ElasticSearchOperations.postTopComment(comment);
				dialog.dismiss();
				
			}
		});
        
        
	}
	
	public void viewFavorites(View v){
		if(user.getUserName().equals("Guest")){
			
			dialog = new Dialog(this);
			dialog.setContentView(R.layout.guest_box);
			dialog.setTitle("ALERT!");
			
			Button button =(Button)dialog.findViewById(R.id.button1);
			dialog.show();
			button.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					
				}
			});
			
			
			
			
		}
		else{
		Intent intent = new Intent(this, FavoritesActivity.class);
		startActivity(intent);
		}
	}

}
