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
		
		 UserList.setOnItemClickListener(new OnItemClickListener(){

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

	public void onStart(){
		super.onStart();
		String[] listName = FileLoading.loadFromFile(this);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_view, listName);
		UserList.setAdapter(adapter);

	}
	
	public void newUser(View v){
		user = new User();
		user.setUserName(editText.getText().toString());
		FileSaving.saveInFile(user.getUserName(), this);
		topComments(user);
	}
	
	public void topComments(User user){
		Intent intent = new Intent( this, TopCommentsActivity.class);
		intent.putExtra("CURRENT_USER", user);
		startActivity(intent);
	}

	public void guestUser(View v){
		user = new User();
		user.setUserName("Guest");
		topComments(user);
	}
	
	public User loadUser(String text){
		user = FileLoading.returnUser(text, this);
		return user;
	}

}
