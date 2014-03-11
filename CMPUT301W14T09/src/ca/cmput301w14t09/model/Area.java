package ca.cmput301w14t09.model;

import java.util.ArrayList;
import java.util.Collection;




public class Area {

	/**
	 * @uml.property  name="containedGeoLocations"
	 */
	private ArrayList<GeoLocation> containedGeoLocations;

	/**
	 * Getter of the property <tt>containedGeoLocations</tt>
	 * @return  Returns the containedGeoLocations.
	 * @uml.property  name="containedGeoLocations"
	 */
	public ArrayList<GeoLocation> getContainedGeoLocations() {
		return containedGeoLocations;
	}

	/**
	 * Setter of the property <tt>containedGeoLocations</tt>
	 * @param containedGeoLocations  The containedGeoLocations to set.
	 * @uml.property  name="containedGeoLocations"
	 */
	public void setContainedGeoLocations(
			ArrayList<GeoLocation> containedGeoLocations) {
				this.containedGeoLocations = containedGeoLocations;
			}

	/**
	 * @uml.property  name="areaName"
	 */
	private String areaName;

	/**
	 * Getter of the property <tt>areaName</tt>
	 * @return  Returns the areaName.
	 * @uml.property  name="areaName"
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * Setter of the property <tt>areaName</tt>
	 * @param areaName  The areaName to set.
	 * @uml.property  name="areaName"
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * @uml.property   name="geoLocation"
	 * @uml.associationEnd   multiplicity="(0 -1)" aggregation="composite" inverse="area:model.GeoLocation"
	 */
	private Collection<GeoLocation> geoLocation;

	/** 
	 * Getter of the property <tt>geoLocation</tt>
	 * @return  Returns the geoLocation.
	 * @uml.property  name="geoLocation"
	 */
	public Collection<GeoLocation> getGeoLocation() {
		return geoLocation;
	}

	/** 
	 * Setter of the property <tt>geoLocation</tt>
	 * @param geoLocation  The geoLocation to set.
	 * @uml.property  name="geoLocation"
	 */
	public void setGeoLocation(Collection<GeoLocation> geoLocation) {
		this.geoLocation = geoLocation;
	}

		
		/**
		 */
		public boolean containsGeoLocation(GeoLocation location){
			return false;	
		}

}
