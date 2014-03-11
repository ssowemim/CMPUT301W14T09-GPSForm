package ca.cmput301w14t09.elasticSearch;

//code from https://github.com/Mrbilec/PicPoster/blob/master/src/ca/ualberta/cs/picposter/ElasticSearchResponse.java

public class ElasticSearchResponse<T> {
	
	 String _index;
	    String _type;
	    String _id;
	    int _version;
	    boolean exists;
	    T _source;
	    double max_score;
	    public T getSource() {
	        return _source;
	    }

}
