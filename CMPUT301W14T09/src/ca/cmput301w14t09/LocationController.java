package ca.cmput301w14t09;

import java.util.ArrayList;
import java.util.Date;

import model.GeoLocation;
import view.SelectLocationActivity;
import android.location.LocationListener;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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

		
		public void locationchanged(android.location.Location location, EditText tv2, EditText tv3){
			
			// TODO Auto-generated method stub
			
			if(location != null){
				
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
				
			}else{
				//tv.setText("no location information");
			}
			
		}
			
		
		public void updatelocation(EditText tv2, EditText tv3){
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
			
			System.out.println("location updated");
			System.out.println("long: "+lng);
			System.out.println("lat: "+lat);
			
		}
		
}
