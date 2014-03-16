package ca.cmput301w14t09;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;


/**
 * 
 * @author Conner
 *  to do
 */
public class FavoritesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favorites, menu);
		return true;
	}

}
