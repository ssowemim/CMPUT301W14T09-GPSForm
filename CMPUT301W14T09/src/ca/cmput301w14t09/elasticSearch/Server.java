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

/**
 * 
 * @author mcmorris
 * Server handles server configuration and status.  
 * Serves as intermediary between Cache and ElasticSearch.
 * Design structure consideration to allow for the existence of multiple servers in a scaled project.
 *
 */
public class Server {
	//private ElasticSearchOperations serverOperations;
	
	/**
	 * isServerReachable can reach the server from this connection
	 * @return true if server can be reached.
	 */
	public boolean isServerReachable() {
		// TODO: Check ping against server to see if it is online.
		return true;
	}
	
}
