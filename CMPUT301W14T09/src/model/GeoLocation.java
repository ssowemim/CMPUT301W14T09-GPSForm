package model;


import java.util.Collection;

import ca.cmput301w14t09.LocationController;




public class GeoLocation {

	/**
	 * @uml.property  name="latitude"
	 */
	private double latitude;

	/**
	 * Getter of the property <tt>latitude</tt>
	 * @return  Returns the latitude.
	 * @uml.property  name="latitude"
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Setter of the property <tt>latitude</tt>
	 * @param latitude  The latitude to set.
	 * @uml.property  name="latitude"
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @uml.property  name="longitude"
	 */
	private double longitude;

	/**
	 * Getter of the property <tt>longitude</tt>
	 * @return  Returns the longitude.
	 * @uml.property  name="longitude"
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Setter of the property <tt>longitude</tt>
	 * @param longitude  The longitude to set.
	 * @uml.property  name="longitude"
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @uml.property  name="name"
	 */
	private String name;

	/**
	 * Getter of the property <tt>name</tt>
	 * @return  Returns the name.
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter of the property <tt>name</tt>
	 * @param name  The name to set.
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @uml.property   name="area"
	 * @uml.associationEnd   multiplicity="(0 -1)" inverse="geoLocation:model.Area"
	 */
	private Collection<Area> area;

	/** 
	 * Getter of the property <tt>area</tt>
	 * @return  Returns the area.
	 * @uml.property  name="area"
	 */
	public Collection<Area> getArea() {
		return area;
	}

	/** 
	 * Setter of the property <tt>area</tt>
	 * @param area  The area to set.
	 * @uml.property  name="area"
	 */
	public void setArea(Collection<Area> area) {
		this.area = area;
	}

	/**
	 * @uml.property   name="comment"
	 * @uml.associationEnd   multiplicity="(0 -1)" inverse="geoLocation1:model.Comment"
	 */
	private Collection<Comment> comment;

	/** 
	 * Getter of the property <tt>comment</tt>
	 * @return  Returns the comment.
	 * @uml.property  name="comment"
	 */
	public Collection<Comment> getComment() {
		return comment;
	}

	/** 
	 * Setter of the property <tt>comment</tt>
	 * @param comment  The comment to set.
	 * @uml.property  name="comment"
	 */
	public void setComment(Collection<Comment> comment) {
		this.comment = comment;
	}

		
		/**
		 */
		public double calculateProximity(GeoLocation targetLocation){
			return 0;
		}

		/**
		 * @uml.property  name="locationController"
		 * @uml.associationEnd  aggregation="composite" inverse="geoLocation:controller.LocationController"
		 */
		private LocationController locationController;

		/**
		 * Getter of the property <tt>locationController</tt>
		 * @return  Returns the locationController.
		 * @uml.property  name="locationController"
		 */
		public LocationController getLocationController() {
			return locationController;
		}

		/**
		 * Setter of the property <tt>locationController</tt>
		 * @param locationController  The locationController to set.
		 * @uml.property  name="locationController"
		 */
		public void setLocationController(LocationController locationController) {
			this.locationController = locationController;
		}

}
