package com.playlistica.http;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class ConnectLastFM {
	HTTPHandler _connection = new HTTPHandler("4ee267973d46f70f027c9d1d652c769e", "http://ws.audioscrobbler.com/2.0/");
	private final long seed = System.nanoTime();
	Random rand = new Random(seed);
	public ConnectLastFM() {
	}
	
	public String getData(URL url) throws Exception {
		return _connection.sendGet(url);
	}
	
	public URL generateURL (String artist, String method) {
		String url = method +""+ artist+"&format=json";
		return _connection.getURL(url);
	}
	
	public JSONObject getArtist(String artist) {
		URL url = _connection.getURL("?method=artist.getinfo&artist="+artist);
		try {
			String data = getData(url);
			if (data.contains("error")) {
				System.out.println("Artist not found. Please enter a valid artist.");
				System.out.println("Message:\n"+data);
			}
			else {
				JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(data); 
				return jsonObject;
			}
		}	catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getSongs(String artist) {
		URL url = _connection.getURL("?method=artist.gettoptracks&artist="+artist);
		try {
				System.out.println(url);
				String songData = getData(url);
				if (songData.contains("error") && songData.contains("16")) {
					System.out.println("No songs found. Please enter a valid artist.");
					System.out.println("Message:\n"+songData);
				} else if (songData.contains("error")) {
					System.out.println("Message:\n"+songData);
				} else {
					//Create JSON Object from songData String output
		
					JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(songData); 
					
					//Get JSONObject from 'toptracks' (otherwise nesting exception) and get array from 'track'.  Next get names in for loops and return
					List<String> list = new ArrayList<String>();
					JSONArray array = jsonObject.getJSONObject("toptracks").optJSONArray("track");
					for(int i = 0 ; i < array.size(); i++){
					    list.add(array.getJSONObject(i).getString("name"));
					}
					Collections.shuffle(list, new Random(seed));
					list = list.subList(0, 2);
					return list;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getSimilarArtists(String artist) {
		URL url = _connection.getURL("?method=artist.getsimilar&artist="+artist);
		try {
			String data = getData(url);
			if (data.contains("error")) {
				System.out.println("Message:\n"+data);
			} else {
				List<String> similarArtists = new ArrayList<String>();
				//Create JSON Object from songData String output
				JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(data); 
				JSONArray artists = jsonObject.optJSONObject("similarartists").optJSONArray("artist");
				
				for (int i=0; i<artists.size();i++) {
					similarArtists.add(artists.getJSONObject(i).getString("name").replace(' ', '+'));
				}
				return similarArtists;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean createPlaylist(String artist) {
		try {
			List<String> artists = getSimilarArtists(artist);
			Map <String, String> songBank = new Hashtable<String, String>();
			ArrayList <String> artistBank = new ArrayList<String>();
			
			//Create a seed from current system time (nano) and use it to shuffle artist list
			Collections.shuffle(artists, new Random(seed));
			
			for (int i=0; i<15; i++) {
				String songs = getSongs(artists.get(i)).get(rand.nextInt(2));
				songBank.put(songs, artists.get(i));
			}
			
			String toPlaylistURL = "?method=playlist.create";
			
			URL url = _connection.getURL(toPlaylistURL);
			String data = getData(url);
			System.out.println(data);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
