package ca.cmput301w14t09;

import java.util.Date;


import android.app.Activity;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class Location extends Activity {
	private static final String GPS_MOCK_PROVIDER = "GpsMockProvider";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		
		
		
		// Obtain LocationManager service and set up location update request.
		// To Do
		
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
		
		
	}

	
	// Retrieve location updates through LocationListener interface
	private final LocationListener locationListener = new LocationListener(){
	// To Do: override the four methods.
		
		
		public void onProviderDisabled (String provider){
			
		}
		
		public void onProviderEnabled (String provider){
			
			
		}
		
		public void onStatusChanged (String provider, int status, Bundle extras){
			
			
		}

		@Override
		public void onLocationChanged(android.location.Location location) {
			// TODO Auto-generated method stub
			TextView tv = (TextView) findViewById(R.id.locationtext);
			
			if(location != null){
				
				double lat = location.getLatitude();
				double lng = location.getLongitude();
				Date date = new Date(location.getTime());
				tv.setText("your location is " +lat+"\n" + lng +"\n" + date.toString());
				System.out.println("got here");
				
			}else{
				tv.setText("no location information");
			}
		}
	};

}
