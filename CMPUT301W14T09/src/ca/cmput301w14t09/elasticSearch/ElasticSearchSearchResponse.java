package ca.cmput301w14t09.elasticSearch;

import java.util.ArrayList;
import java.util.Collection;

//code from https://github.com/Mrbilec/PicPoster/blob/master/src/ca/ualberta/cs/picposter/ElasticSearchSearchResponse.java
	
public class ElasticSearchSearchResponse<T> {
	
	 int took;
	    boolean timed_out;
	    transient Object _shards;
	    Hits<T> hits;
	    boolean exists;
	    public Collection<ElasticSearchResponse<T>> getHits() {
	        return hits.getHits();
	    }
	    public Collection<T> getSources() {
	        Collection<T> out = new ArrayList<T>();
	        for (ElasticSearchResponse<T> essrt : getHits()) {
	            out.add( essrt.getSource() );
	        }
	        return out;
	    }
	    public String toString() {
	        return (super.toString() + ":" + took + "," + _shards + "," + exists + "," + hits);
	    }

}
