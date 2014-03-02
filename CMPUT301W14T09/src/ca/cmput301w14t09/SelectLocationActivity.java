package ca.cmput301w14t09;

import android.app.Activity;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SelectLocationActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		
		
		//Get textviews
		TextView tv = (TextView) findViewById(R.id.locationtext);
		EditText tv2 = (EditText) findViewById(R.id.longtext3);
		EditText tv3 = (EditText) findViewById(R.id.lattext3);
		
		// Obtain LocationManager service 
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		//new Location Controller hello
		LocationController lc = new LocationController();
		
		//new Listener for location change
		LocationListener locationListener = lc.NewListener(tv,tv2,tv3);
		
		//set up location update request.
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
		
		
	}


}
