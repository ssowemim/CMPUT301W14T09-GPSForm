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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import android.util.Log;
import ca.cmput301w14t09.model.Comment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author Conner
 * ElasticSearchOperations contains all methods that talk to the internet that either 
 * upload information onto the elasticSearch website or pull information for the same
 * site
 * code from https://github.com/Mrbilec/PicPoster/blob/master/src/ca/ualberta/cs/picposter/ElasticSearchOperations.java
 *
 */
public class ElasticSearchOperations {

	private static String serverName = "ElasticSearch";
	private static String postAddress = "http://cmput301.softwareprocess.es:8080/cmput301w14t09/test08/";
	private static String searchAddress = "http://cmput301.softwareprocess.es:8080/cmput301w14t09/test08/_search?pretty=1";

	
	static Comment comment;
	
	/**
	 * postThread posts a top comment to Elasti-Search.
	 * Tested and verified.
	 * @param ElasticSearchOperations
	 */
	public static void postThread(final Comment commentThread){
		Thread thread = new Thread() {

			@Override
			public void run() {
				HttpClient client = new DefaultHttpClient();
				Gson gson = new Gson();
				HttpPost request = new HttpPost(postAddress);

				try { 
					String jsonString = gson.toJson(commentThread);
					System.out.println("JSON String:" +jsonString);
					
					request.setEntity(new StringEntity(jsonString));

					HttpResponse response = client.execute(request);
					Log.w(serverName, response.getStatusLine().toString());
					
					response.getStatusLine().toString();
					HttpEntity entity = response.getEntity();

					BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
					String output = reader.readLine();
					while (output != null) {
						Log.w(serverName, output);
						output = reader.readLine();
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}

	/**
	 * pullThreads returns the list of top comments.  
	 * Tested and verified.
	 * @return list of names of Threads.
	 * @throws InterruptedException
	 */
	public static ArrayList<Comment> pullThreads() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);
		final ArrayList<Comment> commentList = new ArrayList<Comment> ();
		Thread thread = new Thread() {

			@Override
			public void run() {
				HttpClient client = new DefaultHttpClient();
				Gson gson = new Gson();

				try {
					HttpPost searchRequest = new HttpPost(searchAddress);
					String query = "{\"query\" : {\"query_string\" : {\"default_field\" : \"topComment\",\"query\" : \"true\"}}}";

					StringEntity stringentity = new StringEntity(query);
					searchRequest.setEntity(stringentity);

					HttpResponse response = client.execute(searchRequest);
					String json = getEntityContent(response);
					
					Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Comment>>(){}.getType();
					ElasticSearchSearchResponse<Comment> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);

					for (ElasticSearchResponse<Comment> r : esResponse.getHits()) {
						Comment topComment = r.getSource();
						commentList.add(topComment);
					}
					
					// Sort by latest dated element.
					Collections.sort(commentList);
					Collections.reverse(commentList);
					
					latch.countDown();
					 //searchRequest.releaseConnection();	

				} catch(Exception e){
					e.printStackTrace();
				}

			}
		};
		thread.start();
		latch.await();
		
		return commentList;
	}
	
	/**
	 * pullOneThread takes in a thread ID and then
	 * returns all comments that contain that thread ID
	 * @param threadId
	 * @return
	 * @throws InterruptedException
	 */
	public static ArrayList<Comment> pullOneThread(final String threadId) throws InterruptedException {
            final CountDownLatch latch = new CountDownLatch(1);
            final ArrayList<Comment> commentList = new ArrayList<Comment> ();
            Thread thread = new Thread() {

                    @Override
                    public void run() {
                            HttpClient client = new DefaultHttpClient();
                            Gson gson = new Gson();

                            System.out.println("I am searching for: " + threadId);
                            
                            try {
                                    HttpPost searchRequest = new HttpPost(searchAddress);
                                    String query = "{\"query\" : {\"query_string\" : {\"default_field\" : \"author\",\"query\" : \"fi\"}}}";

                                    StringEntity stringentity = new StringEntity(query);
                                    searchRequest.setEntity(stringentity);

                                    HttpResponse response = client.execute(searchRequest);
                                    String json = getEntityContent(response);
                                    
                                    Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Comment>>(){}.getType();
                                    ElasticSearchSearchResponse<Comment> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);

                                    for (ElasticSearchResponse<Comment> r : esResponse.getHits()) {
                                            Comment topComment = r.getSource();
                                            commentList.add(topComment);
                                    }
                                    latch.countDown();
                                     //searchRequest.releaseConnection();   

                            } catch(Exception e){
                                    e.printStackTrace();
                            }

                    }
            };
            thread.start();
            latch.await();
            
            return commentList;
    }

	/**
	 * getEntityContent prints result statements to logcat
	 * @param response
	 * @return
	 * @throws IOException
	 */
	static String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));
		String output;
		System.err.println("Output from Server -> ");
		String json = "";
		while ((output = br.readLine()) != null) {
			System.err.println(output);
			json += output;
		}
		System.err.println("JSON:"+json);
		return json;
	}
	
	/**
	 * loadComment is a test method that we currently aren't using.
	 * returns results of elasticSearch query
	 * @param commentText
	 * @return
	 * @throws InterruptedException
	 */
	public static Comment loadComment(final String commentText) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);
		
		Thread thread = new Thread() {

			@Override
			public void run(){

				HttpClient client = new DefaultHttpClient();
				Gson gson = new Gson();

								
				try{
					HttpPost searchRequest = new HttpPost(searchAddress);
					String query = "{\"query\" : {\"query_string\" : {\"default_field\" : \"commentText\",\"query\" : \"" + commentText +"\"}}}";

					StringEntity stringentity = new StringEntity(query);

					searchRequest.setEntity(stringentity);

					HttpResponse response = client.execute(searchRequest);
					
					String json = getEntityContent(response);
					
					
					Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Comment>>(){}.getType();
					ElasticSearchSearchResponse<Comment> esResponse = gson.fromJson(json, elasticSearchSearchResponseType);

					for (ElasticSearchResponse<Comment> r : esResponse.getHits()) {
						comment = r.getSource();												
					}
					latch.countDown();
					 //searchRequest.releaseConnection();	

				} catch(Exception e){
					e.printStackTrace();
				}

			}
		};
		thread.start();
		latch.await();
		
		return comment;
	}

}
