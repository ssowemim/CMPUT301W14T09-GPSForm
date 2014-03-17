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

import java.util.Collection;

//code from https://github.com/Mrbilec/PicPoster/blob/master/src/ca/ualberta/cs/picposter/Hits.java

public class Hits<T> {

	 int total;
	    double max_score;
	    Collection<ElasticSearchResponse<T>> hits;
	    public Collection<ElasticSearchResponse<T>> getHits() {
	        return hits;
	    }
	    public String toString() {
	        return (super.toString()+","+total+","+max_score+","+hits);
	    }
}
