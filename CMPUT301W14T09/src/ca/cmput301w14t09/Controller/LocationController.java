package ca.cmput301w14t09.Controller;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.EditText;
import android.widget.Toast;
import ca.cmput301w14t09.model.GeoLocation;



public class LocationController {

		private GeoLocation geo = new GeoLocation();
		
		private double lat;
		private double lng;
		LocationManager lm = null;
		int count = 0;
		
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
		
		
		public void setLocationManager(Context context){
			
			// Obtain LocationManager service 
			@SuppressWarnings("static-access")
			LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
			
			
		}
		
		public void requestLocationUpdates(LocationListener locationListener){
			
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
			
		}
		



		
		public void locationchanged(android.location.Location location, EditText tv2, EditText tv3){
			
			
			if(location != null){
				
				if(count<2){
				
				lat = location.getLatitude();
				lng = location.getLongitude();
				
				
				Date date = new Date(location.getTime());
				//tv.setText("your location is " +lat+"\n" + lng +"\n" + date.toString());
				tv2.setText(""+lng);
				tv3.setText(""+lat);
				System.out.println("location changed");
				System.out.println("long: "+lng);
				System.out.println("lat: "+lat);
				//set geolocation to current location
				setGeoLocation();
				count = count + 1;
				}
			}
			
		}
			
		
		public void updatelocation(Context context, EditText tv2, EditText tv3){
			//System.out.println("UPdate complete");
			
			
			//lng
			String text = tv2.getText().toString();
			
			//lat
			String text1 = tv3.getText().toString();
			
			//lng
			double double1 = Double.parseDouble(text);
			lng = double1;
			
			//lat
			double double2 = Double.parseDouble(text1);
			lat = double2;
			
			//sets geolocation to new given one
			setGeoLocation();
			
			String update = "Your location has been updated";
			// When clicked, show a toast with the TextView text Game, Help, Home
            Toast.makeText(context, update, Toast.LENGTH_SHORT).show();  
			
            System.out.println("count"+count);
			System.out.println("location updated");
			System.out.println("long: "+lng);
			System.out.println("lat: "+lat);
			}
			
			
		}
		
		
		
		
		
		

