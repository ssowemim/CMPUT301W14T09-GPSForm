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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.util.Log;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.UserProfileModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author Conner & ssowemim
 * ElasticSearchOperations contains all methods that talk to the internet that either 
 * upload information onto the elasticSearch website or pull information for the same
 * site
 * code from https://github.com/Mrbilec/PicPoster/blob/master/src/ca/ualberta/cs/picposter/ElasticSearchOperations.java
 *
 */
public class ElasticSearchOperations extends Server{

	private static String serverName = "ElasticSearch";
	private static String postAddress = "http://cmput301.softwareprocess.es:8080/cmput301w14t09/real09/";
	private static String searchAddress = "http://cmput301.softwareprocess.es:8080/cmput301w14t09/real09/_search?pretty=1&size=100";
	private static String updateAddress = "http://cmput301.softwareprocess.es:8080/cmput301w14t09/real09/";
	private static String profileAddress = "http://cmput301.softwareprocess.es:8080/cmput301w14t09/testUser009/";
	private static Gson GSON = null;
	static Comment comment;

	private static ElasticSearchOperations instance = null;
	private ElasticSearchOperations() {
		super();
	}

	/**
	 * returns the instance of the ElasticSearchOperations
	 * @return
	 */
	public static ElasticSearchOperations getInstance() {
		if (instance == null)
			instance = new ElasticSearchOperations();
		return instance;
	}

	/**
	 * postThread posts a top comment to Elastic-Search.
	 * Tested and verified.
	 * @param ElasticSearchOperations
	 * @throws InterruptedException 
	 */
	public static void postThread(final Comment commentThread) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);

		if (GSON == null)
			constructGson();

		Thread thread = new Thread() {

			@Override
			public void run() {
				HttpClient client = new DefaultHttpClient();
				HttpPost request = new HttpPost(postAddress + commentThread.getUuid() + "/");

				try { 
					request.setEntity(new StringEntity(GSON.toJson(commentThread)));

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
				latch.countDown();
			}
		};
		thread.start();
		latch.await();
	}

	/**
	 * This method creates is what makes the bitmap into a json serialzable format
	 */
	private static void constructGson(){
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Bitmap.class, new JsonBitmapConverter());
		GSON = builder.create();
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

		if (GSON == null)
			constructGson();

		Thread thread = new Thread() {
			
			@Override
			public void run() {
				HttpClient client = new DefaultHttpClient();

				try {
					HttpPost searchRequest = new HttpPost(searchAddress);
					String query = "{\"query\" : {\"query_string\" : {\"default_field\" : \"topComment\",\"query\" : \"true\"}}}";

					StringEntity stringentity = new StringEntity(query);
					searchRequest.setEntity(stringentity);

					HttpResponse response = client.execute(searchRequest);
					String json = getEntityContent(response);

					Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Comment>>(){}.getType();
					ElasticSearchSearchResponse<Comment> esResponse = GSON.fromJson(json, elasticSearchSearchResponseType);

					for (ElasticSearchResponse<Comment> r : esResponse.getHits()) {
						Comment topComment = r.getSource();
						commentList.add(topComment);
					}

					// Sort by latest dated element.
					Collections.sort(commentList);
					Collections.reverse(commentList);
					latch.countDown();
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

		if (GSON == null)
			constructGson();

		Thread thread = new Thread() {

			@Override
			public void run() {
				HttpClient client = new DefaultHttpClient();

				try {
					HttpPost searchRequest = new HttpPost(searchAddress);
					String query = "{\"query\" : {\"query_string\" : {\"default_field\" : \"threadId\", \"query\" : \"" + threadId + "\"}}}";
					StringEntity stringentity = new StringEntity(query);
					searchRequest.setEntity(stringentity);

					HttpResponse response = client.execute(searchRequest);
					String json = getEntityContent(response);

					Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Comment>>(){}.getType();
					ElasticSearchSearchResponse<Comment> esResponse = GSON.fromJson(json, elasticSearchSearchResponseType);

					for (ElasticSearchResponse<Comment> r : esResponse.getHits()) {
						Comment topComment = r.getSource();
						commentList.add(topComment);
					}
					
					// Sort by latest dated element.
					Collections.sort(commentList);
					latch.countDown();
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
	 * updateComment updates the text field of a comment
	 * @throws InterruptedException 
	 */
	public static void updateComment(final Comment comment, final String str) throws ClientProtocolException, IOException, InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);

		Thread thread = new Thread() {
			@SuppressWarnings("hiding")
			@Override
			public void run() {
				HttpPost updateRequest = new HttpPost(updateAddress + comment.getUuid() + "/_update/");
				String query = "{\"script\" : \"ctx._source." + str + "}";
				StringEntity stringentity;
				try
				{
					stringentity = new StringEntity(query);
					updateRequest.setHeader("Accept","application/json");
					updateRequest.setEntity(stringentity);

					latch.countDown();

				}  catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
		latch.await();
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
		String json = "";
		while ((output = br.readLine()) != null) {
			json += output;
		}
		return json;
	}

	/**
	 * Pushes the UserProfileModel onto the server
	 * @param uPModel
	 * @throws InterruptedException
	 */
	public static void pushUserProfile(final UserProfileModel uPModel) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);

		if (GSON == null)
			constructGson();

		Thread thread = new Thread() {

			@Override
			public void run() {
				HttpClient client = new DefaultHttpClient();
				HttpPost request = new HttpPost(profileAddress+uPModel.getUniqueID()+"/");

				try { 

					request.setEntity(new StringEntity(GSON.toJson(uPModel)));

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
				latch.countDown();
			}
		};
		thread.start();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Pulls the userProfile from the server, uses their uniqueID as a key source of extracting
	 * Information.
	 * @param uniqueID
	 * @return
	 * @throws InterruptedException
	 */
	public static ArrayList<UserProfileModel> pullUserProfile(final String uniqueID) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);
		final ArrayList<UserProfileModel> userProfileList = new ArrayList<UserProfileModel> ();

		if (GSON == null)
			constructGson();

		Thread thread = new Thread() {

			@Override
			public void run() {
				HttpClient client = new DefaultHttpClient();
				try {
					HttpPost searchRequest = new HttpPost(profileAddress + "_search?pretty=1");
					String query = "{\"query\" : {\"query_string\" : {\"default_field\" : \"uniqueID\",\"query\" : \""+uniqueID+"\"}}}";

					StringEntity stringentity = new StringEntity(query);
					searchRequest.setEntity(stringentity);

					HttpResponse response = client.execute(searchRequest);
					String json = getEntityContent(response);

					Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<UserProfileModel>>(){}.getType();
					ElasticSearchSearchResponse<UserProfileModel> esResponse = GSON.fromJson(json, elasticSearchSearchResponseType);

					for (ElasticSearchResponse<UserProfileModel> r : esResponse.getHits()) {
						UserProfileModel userProfileModel = r.getSource();
						userProfileList.add(userProfileModel);
					}
					latch.countDown();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
		latch.await();
		return userProfileList;
	}

}
