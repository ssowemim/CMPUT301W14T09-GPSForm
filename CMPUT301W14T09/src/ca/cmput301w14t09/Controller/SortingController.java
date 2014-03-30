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
import java.util.Map.Entry;
import java.util.TreeMap;

import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.GeoLocation;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;

/**
 * 
 * @author Chunhan
 * Controller handling the logic behind sorting the comments for proximity.
 * Compatible with sorting from default location or changed locations. 
 * 
 *
 */

public class SortingController {

	/**
	 * 
	 * Calls the sort commands of the geo locations and 
	 * returns a list of comments ordered by proximity
	 * @return
	 * 
	 */
	public ArrayList<Comment> sortCommentsByLocation(){
		LocationController lc = new LocationController();
		ArrayList<Comment> newList = null;
		newList = sortComments(lc.getGeoLocation());
		return newList;
	}

	/**
	 * 
	 * Calculates the ranking for each of the maps to the provided
	 * geolocation and places them into a map with key as comment and value
	 * as the distance of the comment
	 * @param geo
	 * @return
	 * 
	 */
	public ArrayList<Comment> sortComments(GeoLocation geo){
		final Map<Comment, Double> myHashMap = new HashMap<Comment, Double>();
		double comRank = 0;
		double lon = geo.getLongitude();
		double lat = geo.getLatitude();
		ArrayList<Comment> topComments = null;
		ArrayList<Comment> sortedComments = new ArrayList<Comment>();

		try {
			topComments = ElasticSearchOperations.pullThreads();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i=0; i< topComments.size(); i++){
			Comment tComment = topComments.get(i);
			GeoLocation geo1 = tComment.getGeoLocation();
			double tlon = geo1.getLongitude();
			double tlat = geo1.getLatitude();
			double a = Math.abs(Math.abs(tlon) - Math.abs(lon));
			double b = Math.abs(Math.abs(tlat)-Math.abs(lat));
			comRank =  a + b;
			System.out.println(Math.abs(tlon));
			System.out.println(Math.abs(lon));
			System.out.println(comRank);
			System.out.println(tComment.getAuthorName());
			myHashMap.put(tComment, comRank);
		}

		//http://www.mkyong.com/java/how-to-sort-a-map-in-java/
		Map<Comment, Double> sortedMap = sortByComparator(myHashMap);
		for (Map.Entry entry : sortedMap.entrySet()){
			Comment comm = (Comment) entry.getKey();
			sortedComments.add(comm);
			System.out.println(comm.getAuthorName());
			System.out.println(comm.getCommentText());
		}

		return sortedComments;
	}

	/**
	 * 
	 * Sorts the unsorted map of comments ranked by distance
	 * @param unsorted
	 * @return
	 * http://www.mkyong.com/java/how-to-sort-a-map-in-java/
	 * 
	 */

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

