package com.spark.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.spark.AsyncTaskListener;
import com.spark.FacebookPost;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

public class RetrievePostsTask extends AsyncTask<Integer, Integer, ArrayList<FacebookPost>> {
	private static final String API_KEY = "CAACEdEose0cBADMNhLxSUx8cBxae18R9K4GeCpFSyOf7SAAhgmflzdXm6OLHUxIF6Ttt5pBkas29Rhbgqx0SvwekXfsGnYYSZAz8pVmedDzAsHOl2RjZC8ZCDRXQZB1CVK7jaVZBpHhYZBxgqqsAJZCSt7Dh1xbvNKTRQ77C5M35uDpAYmfyjE8ee8mfncMOfBkZAOWfZCOLK5wZDZD";

	
	private static final String URL = "https://graph.facebook.com/GilbertFireDept/posts?access_token=";
	private AsyncTaskListener<ArrayList<FacebookPost>> listener;
	
	public RetrievePostsTask(AsyncTaskListener<ArrayList<FacebookPost>> l) {
		listener = l;
	}

	protected ArrayList<FacebookPost> doInBackground(Integer... unecessary) {
		try {
			JSONObject json = readJsonFromUrl(URL + API_KEY);
			ArrayList<FacebookPost> posts = new ArrayList<FacebookPost>();
			
			JSONArray array = json.getJSONArray("data");
			String message = null;
			String URL = null;
			String imageURL = null;
			String date = null;

			for (int i = 0; i < array.length(); i++) {
				message = array.getJSONObject(i).optString("message", null);
				
				if (message != null) {
					URL = array.getJSONObject(i).optString("link", null);
					imageURL = array.getJSONObject(i).optString("picture", null);
					date = array.getJSONObject(i).optString("created_time", null);
					
					posts.add(new FacebookPost(message, URL, imageURL, date));
				}
			}
			
			return posts;
		} catch (IOException e) {
			Log.e("RetrievePostsTask", e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			Log.e("RetrievePostsTask", e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	protected void onPostExecute(ArrayList<FacebookPost> result) {
		listener.onTaskComplete(result);
	}

	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public JSONObject readJsonFromUrl(String url) throws IOException,
			JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
}
