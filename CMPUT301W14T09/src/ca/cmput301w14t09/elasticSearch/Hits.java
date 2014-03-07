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
