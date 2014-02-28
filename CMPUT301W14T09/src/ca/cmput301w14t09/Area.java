package ca.cmput301w14t09;

import java.util.ArrayList;
import java.util.Collection;


public class Area {
	
	private String areaName;

	private ArrayList<GeoLocation> containedGeoLocations;

	public ArrayList<GeoLocation> getContainedGeoLocations() {
		return containedGeoLocations;
	}
	
	private Collection<GeoLocation> geoLocation;


	public void setContainedGeoLocations(
			ArrayList<GeoLocation> containedGeoLocations) {
				this.containedGeoLocations = containedGeoLocations;
			}

	public String getAreaName() {
		return areaName;
	}

	
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Collection<GeoLocation> getGeoLocation() {
		return geoLocation;
	}

	
	public void setGeoLocation(Collection<GeoLocation> geoLocation) {
		this.geoLocation = geoLocation;
	}

		public boolean containsGeoLocation(GeoLocation location){
			return false;	
		}

}
