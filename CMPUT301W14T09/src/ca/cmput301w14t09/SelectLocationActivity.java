package ca.cmput301w14t09;

import android.app.Activity;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class SelectLocationActivity extends Activity {
	private static final String GPS_MOCK_PROVIDER = "GpsMockProvider";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		
		
		// Obtain LocationManager service 
		TextView tv = (TextView) findViewById(R.id.locationtext);
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		//new Location Controller hello
		LocationController lc = new LocationController();
		
		//new Listener for location change
		LocationListener locationListener = lc.NewListener(tv);
		
		//set up location update request.
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
		
		
	}


}
