package controller;

import java.util.ArrayList;
import view.SelectLocationActivity;
import model.GeoLocation;



public class LocationController {

		
		/**
		 */
		public ArrayList<String> getLocationNames(){
			return null;
		}

		/**
		 * @uml.property  name="selectLocationActivity"
		 * @uml.associationEnd  inverse="locationController:view.SelectLocationActivity"
		 */
		private SelectLocationActivity selectLocationActivity;

		/**
		 * Getter of the property <tt>selectLocationActivity</tt>
		 * @return  Returns the selectLocationActivity.
		 * @uml.property  name="selectLocationActivity"
		 */
		public SelectLocationActivity getSelectLocationActivity() {
			return selectLocationActivity;
		}

		/**
		 * Setter of the property <tt>selectLocationActivity</tt>
		 * @param selectLocationActivity  The selectLocationActivity to set.
		 * @uml.property  name="selectLocationActivity"
		 */
		public void setSelectLocationActivity(
				SelectLocationActivity selectLocationActivity) {
					this.selectLocationActivity = selectLocationActivity;
				}

		/**
		 * @uml.property  name="geoLocation"
		 * @uml.associationEnd  inverse="locationController:model.GeoLocation"
		 */
		private GeoLocation geoLocation;

		/**
		 * Getter of the property <tt>geoLocation</tt>
		 * @return  Returns the geoLocation.
		 * @uml.property  name="geoLocation"
		 */
		public GeoLocation getGeoLocation() {
			return geoLocation;
		}

		/**
		 * Setter of the property <tt>geoLocation</tt>
		 * @param geoLocation  The geoLocation to set.
		 * @uml.property  name="geoLocation"
		 */
		public void setGeoLocation(GeoLocation geoLocation) {
			this.geoLocation = geoLocation;
		}

}
