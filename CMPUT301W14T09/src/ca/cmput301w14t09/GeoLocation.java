package ca.cmput301w14t09;


import java.util.Collection;

import ca.cmput301w14t09.model.Comment;




public class GeoLocation {

	
	private double latitude;
	private double longitude;
	private String name;
	

	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}


	public double calculateProximity(GeoLocation targetLocation){
			return 0;
	}

}
