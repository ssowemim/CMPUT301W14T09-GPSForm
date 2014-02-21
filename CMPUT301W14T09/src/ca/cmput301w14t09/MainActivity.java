package ca.cmput301w14t09;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import ca.cmput301w14t09.FileManaging.FileLoading;
import ca.cmput301w14t09.model.User;


public class MainActivity extends Activity {
	
	protected ListView UserList;
	protected EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		editText = (EditText) findViewById(R.id.editText1);
		UserList = (ListView) findViewById(R.id.UserList);
		
		return true;
	}

	public void onStart(){
		super.onStart();
		String[] listName = FileLoading.loadFromFile(this);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_view, listName);
		UserList.setAdapter(adapter);

	}
	
	public void newUser(View v){
		User user = new User();
		user.setUserName(editText.getText().toString());
		topComments(user);
	}
	
	public void topComments(User user){
		Intent intent = new Intent( this, TopCommentsActivity.class);
		intent.putExtra("CURRENT_USER", user);
		startActivity(intent);
	}

	public void guestUser(View v){
		User user = new User();
		user.setUserName("Guest");
		topComments(user);
	}

}
