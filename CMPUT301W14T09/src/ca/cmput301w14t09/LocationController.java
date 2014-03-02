package ca.cmput301w14t09;

import java.util.ArrayList;
import java.util.Date;

import model.GeoLocation;
import view.SelectLocationActivity;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.TextView;


public class LocationController {

		private GeoLocation geoLocation;
		private SelectLocationActivity selectLocationActivity;
		
		public GeoLocation getGeoLocation() {
			return geoLocation;
		}

		
		public ArrayList<String> getLocationNames(){
			return null;
		}

		public SelectLocationActivity getSelectLocationActivity() {
			return selectLocationActivity;
		}

		
		public void setSelectLocationActivity(
				SelectLocationActivity selectLocationActivity) {
					this.selectLocationActivity = selectLocationActivity;
				}

	

		public void setGeoLocation(GeoLocation geoLocation) {
			this.geoLocation = geoLocation;
		}
		
		public LocationListener NewListener(final TextView tv){
			
			// Retrieve location updates through LocationListener interface
			final LocationListener locationListener = new LocationListener(){
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
			return locationListener;
		}


		
		

}
