package ca.cmput301w14t09;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TopCommentsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_top_comments);
		
		//location button, this proboly needs to be taken out but for testing I want to make this button go to my geolocation
		//activity to test to see if geolocation can be found 
		//This will proboly be part of the location controller. 
	    Button btnSimple3 = (Button) findViewById(R.id.locationbutton);
	    btnSimple3.setOnClickListener(new View.OnClickListener() {
					
		@Override
		public void onClick(View v) {
			//git clock off check test test test
			Intent intent2 = new Intent(TopCommentsActivity.this,SelectLocationActivity.class);
			startActivity(intent2);  
											
	   }
 });
	    
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.top_comments, menu);
		return true;
	}
	
	public void popUp(View v){
		
		Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.pop_up_comment);
        dialog.setTitle("New Top Comment");

        EditText editText=(EditText)dialog.findViewById(R.id.editText);
        Button save=(Button)dialog.findViewById(R.id.save);
        Button btnCancel=(Button)dialog.findViewById(R.id.cancel);
        dialog.show();
	}

}
