package ca.cmput301w14t09.elasticSearch;


// code from https://github.com/Mrbilec/PicPoster/blob/master/src/ca/ualberta/cs/picposter/ElasticSearchOperations.java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
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

public class ElasticSearchOperations {

	private static String serverName = "ElasticSearch";
	private static String postAddress = "http://cmput301.softwareprocess.es:8080/cmput301w14t09/test02/";
	private static String searchAddress = "http://cmput301.softwareprocess.es:8080/cmput301w14t09/test02/_search?pretty=1";

	static Comment comment;
	
	public static void postTopComment(final Comment comment){
		Thread thread = new Thread() {

			@Override
			public void run(){

				HttpClient client = new DefaultHttpClient();
				Gson gson = new Gson();
				HttpPost request = new HttpPost(postAddress);

				try{ 
					String jsonString = gson.toJson(comment);
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
				catch (Exception e){

					e.printStackTrace();
				}
			}
		};
		thread.start();
	}

	public static String[] pullTopComments() throws InterruptedException{
		final CountDownLatch latch = new CountDownLatch(1);
		final ArrayList<String> commentList = new ArrayList<String> ();
		Thread thread = new Thread() {

			@Override
			public void run(){

				HttpClient client = new DefaultHttpClient();
				Gson gson = new Gson();

				try{
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
						commentList.add(topComment.getCommentText());
						
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
		
		return commentList.toArray(new String[commentList.size()]);
	}

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
	
	public static Comment loadComment(final String commentText) throws InterruptedException{
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
