package ca.cmput301w14t09;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;


//class that is responsible for building the areas needed for geolocations.


public class BuildAreas {
	
	List<Area> listAreas = new ArrayList<Area>();
	
public List<Area> AreaBuild(Context appcontext){
	
	//create quad area	
	Area ld = new Area();
	
	GeoLocation geo = new GeoLocation();
	geo.setLongitude(53.52568);
	geo.setLatitude(-113.52562);
	ld.addGeolocation(geo);
	listAreas.add(ld);
	
	
	
 
	
	return listAreas;
	
		}
	

}
