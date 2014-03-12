package ca.cmput301w14t09.model.user;

/**
 * Handles server configuration and status.  
 * Serves as intermediary between Cache and ElasticSearch.
 * Design structure consideration to allow for the existence of multiple servers in a scaled project.
 * @author mcmorris
 */
public class Server {
	
	// Most configuration settings are not changeable in the context of our class.
	private String serverName = "ElasticSearch";
	private String postAddress = "http://cmput301.softwareprocess.es:8080/cmput301w14t09/test02/";
	private String searchAddress = "http://cmput301.softwareprocess.es:8080/cmput301w14t09/test02/_search?pretty=1";
	
	/**
	 * Can we reach the server from this connection?
	 * @return true if server can be reached.
	 */
	public boolean isServerReachable() {
		// TODO: Check ping against server to see if it is online.
		return true;
	}
	
}
