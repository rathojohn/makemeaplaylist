package com.playlistica.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HTTPHandler {
	
	/**
	 * Returns the URL object for the String value given.
	 * @param baseUrl
	 * @return
	 */
	
	private HttpClient _httpclient;
	private final String USER_AGENT = "Mozilla/5.0";
	private String API_KEY;
	private String BASE_URL;

	
	public HTTPHandler (String API_KEY, String BASE_URL) {
		_httpclient = new DefaultHttpClient();
		this.API_KEY = API_KEY;
		this.BASE_URL = BASE_URL;
	}
	
	public URL getURL (String input) {
		try {
			URL url = new URL(BASE_URL+""+input+"&api_key="+API_KEY+
					"&format=json");
			return url;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getTokenFromURL (URL url) {
		// Not Yet implemented
		return null;
	}
	
	public void sendPost(URL url,List<NameValuePair> urlParameters) {
		String sUrl = url.toString();

		HttpPost post = new HttpPost(sUrl);
 
		// add header
		post.setHeader("User-Agent", USER_AGENT);

 
		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse response = _httpclient.execute(post);
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + post.getEntity());
			System.out.println("Response Code : " + 
	                                    response.getStatusLine().getStatusCode());
	 
			BufferedReader rd = new BufferedReader(
	                        new InputStreamReader(response.getEntity().getContent()));
	 
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
	 
			System.out.println(result.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public String sendGetJSON(URL url) throws Exception {
		String sUrl = url.toString();
		HttpGet request = new HttpGet(sUrl);
 
		// add request header
		request.addHeader("User-Agent", USER_AGENT);
		
 
		HttpResponse response = _httpclient.execute(request);
 
		int responseCode = response.getStatusLine().getStatusCode();
		if (!(responseCode == 200)) {
			System.out.println ("HTTP Error Status Code: "+responseCode+"!");
		}
 
		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		return result.toString();
	}
	
	public String sendGet(URL url) throws Exception {
		String sUrl = url.toString();
		HttpGet request = new HttpGet(sUrl);
 
		// add request header
		request.addHeader("User-Agent", USER_AGENT);
 
		HttpResponse response = _httpclient.execute(request);
 
		int responseCode = response.getStatusLine().getStatusCode();
		if (!(responseCode == 200)) {
			System.out.println ("HTTP Error Status Code: "+responseCode+"!");
		}
 
		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		return result.toString();
	}
}

