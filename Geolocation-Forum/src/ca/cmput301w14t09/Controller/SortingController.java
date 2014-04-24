/**
License GPLv3: GNU GPL Version 3
<http://gnu.org/licenses/gpl.html>.
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package ca.cmput301w14t09.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.GeoLocation;

/**
 * 
 * @author chunhan, ssowemim
 * Controller handling the logic behind sorting the comments for proximity.
 * Compatible with sorting from default location or changed locations. 
 * Also sorts comments based of if there is a picture attached to that comment
 *
 */

public class SortingController {
	
	/**
	 * 
	 * Sort Pictures in top comments view
	 * @author chunhan, ssowemim
	 * @return
	 * 
	 */
	public ArrayList<Comment> sortPicTopComments(ArrayList<Comment> commentList){

		Collections.sort(commentList, new Comparator<Comment>() {
			public int compare(Comment o1, Comment o2) {
				if (o1.getHasPicture() == null || o2.getHasPicture() == null)
					return 0;
				return o1.getHasPicture().compareTo(o2.getHasPicture());
			}
		});
		Collections.reverse(commentList);
		return commentList;
	}


	/**
	 * 
	 * @author chunhan
	 * Calculates the ranking for each of the maps to the provided
	 * geolocation and places them into a map with key as top comments and value
	 * as the distance of the comment
	 * @param lc
	 * @param geo
	 * @param topComments
	 * @return
	 */
	public ArrayList<Comment> sortTopComments(LocationController lc, GeoLocation geo, ArrayList<Comment> topComments){
		
		if(geo == null){
			geo = lc.getGeodefault();
		}
		
		final Map<Comment, Double> myHashMap = new HashMap<Comment, Double>();
		double comRank = 0;
		double lon = geo.getLongitude();
		double lat = geo.getLatitude();

		ArrayList<Comment> sortedComments = new ArrayList<Comment>();
		for (int i=0; i< topComments.size(); i++){
			Comment tComment = topComments.get(i);
			GeoLocation geo1 = tComment.getGeoLocation();
			double tlon = geo1.getLongitude();
			double tlat = geo1.getLatitude();
			double a = Math.abs(Math.abs(tlon) - Math.abs(lon));
			double b = Math.abs(Math.abs(tlat)-Math.abs(lat));
			comRank =  a + b;
			myHashMap.put(tComment, comRank);
		}

		//http://www.mkyong.com/java/how-to-sort-a-map-in-java/
		@SuppressWarnings("unchecked")
		Map<Comment, Double> sortedMap = sortByComparator(myHashMap);
		for (@SuppressWarnings("rawtypes") Map.Entry entry : sortedMap.entrySet()){
			Comment comm = (Comment) entry.getKey();
			sortedComments.add(comm);
		}
		return sortedComments;
	}

	/**
	 * 
	 * @author chunhan
	 * Sorts the unsorted map of comments ranked by distance
	 * @param unsorted
	 * @return
	 * http://www.mkyong.com/java/how-to-sort-a-map-in-java/
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <E> Map sortByComparator(Map unsorted){

		List list = new LinkedList(unsorted.entrySet());
		Collections.sort(list, new Comparator(){
			public int compare(Object o1, Object o2){
				return (Integer) ((((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry)(o2)).getValue()) ));
			}
		});

		Map sorted = new LinkedHashMap();
		for (Iterator<E> it = list.iterator(); it.hasNext();){
			Map.Entry entry = (Map.Entry) it.next();
			sorted.put(entry.getKey(), entry.getValue());
		}
		return sorted;
	}

}

