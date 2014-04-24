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

package ca.cmput301w14t09.elasticSearch;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Conner & ssowemim
 * code from https://github.com/Mrbilec/PicPoster/blob/master/src/ca/ualberta/cs/picposter/ElasticSearchSearchResponse.java
 * @param <T>
 */

public class ElasticSearchSearchResponse<T> {

	int took;
	boolean timed_out;
	transient Object _shards;
	Hits<T> hits;
	boolean exists;
	
	/**
	 * Returns the hits
	 * @return
	 */
	public Collection<ElasticSearchResponse<T>> getHits() {
		return hits.getHits();
	}
	
	/**
	 * Returns the sources based on the amount of hits it gets
	 * @return
	 */
	public Collection<T> getSources() {
		Collection<T> out = new ArrayList<T>();
		for (ElasticSearchResponse<T> essrt : getHits()) {
			out.add( essrt.getSource() );
		}
		return out;
	}
	
	/**
	 * Returns the information of the hits in a string format
	 */
	public String toString() {
		return (super.toString() + ":" + took + "," + _shards + "," + exists + "," + hits);
	}
}
