package ca.cmput301w14t09;


import java.util.Collection;

import ca.cmput301w14t09.model.Comment;




public class GeoLocation {

	
	private double latitude;
	private double longitude;
	private String name;
	private Collection<Area> area;
	private Collection<Comment> comment;

	
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

	public Collection<Area> getArea() {
		return area;
	}

	
	public void setArea(Collection<Area> area) {
		this.area = area;
	}


	public Collection<Comment> getComment() {
		return comment;
	}

	
	public void setComment(Collection<Comment> comment) {
		this.comment = comment;
	}

	
	public double calculateProximity(GeoLocation targetLocation){
			return 0;
	}

}
