package ca.cmput301w14t09;

import java.util.ArrayList;
import java.util.Date;

import model.GeoLocation;
import view.SelectLocationActivity;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


public class LocationController {

		private GeoLocation geo = new GeoLocation();
		private SelectLocationActivity selectLocationActivity;
		private double lat;
		private double lng;
		
		public GeoLocation getGeoLocation() {
			return geo;
		}

		public void setGeoLocation() {
			geo.setLatitude(lat);
			geo.setLongitude(lng);
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

		
		public LocationListener NewListener(final TextView tv, final EditText tv2, final EditText tv3){
			
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
						
						lat = location.getLatitude();
						lng = location.getLongitude();
						
						
						Date date = new Date(location.getTime());
						tv.setText("your location is " +lat+"\n" + lng +"\n" + date.toString());
						tv2.setText(""+lng);
						tv3.setText(""+lat);
						System.out.println("got here");
						
					}else{
						tv.setText("no location information");
					}
				}
			};
			return locationListener;
		}


		
		

}
