package view;

import java.util.ArrayList;

import ca.cmput301w14t09.model.LocationController;
import model.Area;

public class SelectLocationActivity {

	/**
	 * @uml.property  name="areas"
	 */
	private ArrayList<Area> areas;

	/**
	 * Getter of the property <tt>areas</tt>
	 * @return  Returns the areas.
	 * @uml.property  name="areas"
	 */
	public ArrayList<Area> getAreas() {
		return areas;
	}

	/**
	 * Setter of the property <tt>areas</tt>
	 * @param areas  The areas to set.
	 * @uml.property  name="areas"
	 */
	public void setAreas(ArrayList<Area> areas) {
		this.areas = areas;
	}

	/**
	 * @uml.property  name="locationController"
	 * @uml.associationEnd  aggregation="composite" inverse="selectLocationActivity:controller.LocationController"
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
