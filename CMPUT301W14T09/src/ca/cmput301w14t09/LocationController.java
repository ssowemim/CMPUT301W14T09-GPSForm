package ca.cmput301w14t09;

import java.util.ArrayList;
import view.SelectLocationActivity;
import model.GeoLocation;



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

}
