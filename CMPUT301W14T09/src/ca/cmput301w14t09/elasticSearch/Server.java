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

import android.content.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 *  
 * @author mcmorris
 * Server handles server configuration and status.  
 * Serves as intermediary between Cache and ElasticSearch.
 * Design structure consideration to allow for the existence of multiple servers in a scaled project.
 * 
 * code taken from
 * http://stackoverflow.com/questions/4238921/android-detect-whether-there-is-an-internet-connection-available
 */

public class Server {

	/**
	 * isServerReachable can reach the server from this connection
	 * @return true if server can be reached.
	 */
    protected static Server instance = null;
    protected Server() {
        
    }
    
    public static Server getInstance() {
        if (instance == null)
            instance = new Server();
        return instance;
    }
	
	public boolean isServerReachable(Context con) {
		return haveNetworkConnection(con);
	}
	
	private boolean haveNetworkConnection(Context con) {
	    boolean haveConnectedWifi = false;
	    boolean haveConnectedMobile = false;

	    ConnectivityManager cm = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	    for (NetworkInfo ni : netInfo) {
	        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	            if (ni.isConnected())
	                haveConnectedWifi = true;
	        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	            if (ni.isConnected())
	                haveConnectedMobile = true;
	    }
	    return haveConnectedWifi || haveConnectedMobile;
	}

}
