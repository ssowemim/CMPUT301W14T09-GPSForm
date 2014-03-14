package ca.cmput301w14t09.elasticSearch;

/**
 * Handles server configuration and status.  
 * Serves as intermediary between Cache and ElasticSearch.
 * Design structure consideration to allow for the existence of multiple servers in a scaled project.
 * @author mcmorris
 */
public class Server {
	//private ElasticSearchOperations serverOperations;
	
	/**
	 * Can we reach the server from this connection?
	 * @return true if server can be reached.
	 */
	public boolean isServerReachable() {
		// TODO: Check ping against server to see if it is online.
		return true;
	}
	
}
