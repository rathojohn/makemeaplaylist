package com.playlistica;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.playlistica.http.ConnectLastFM;

public class Main {
	public static void main (String [] args) {
		ConnectLastFM lastFm = new ConnectLastFM();
		//testGetArtistLastFM(lastFm);
		
		//testTopSongsLastFM(lastFm);
		
		//testRelatedArtistsLastFM(lastFm);
		
		testCreatePlayListLastFM(lastFm);
	}
	
	public static void testGetArtistLastFM(ConnectLastFM lastFm) {
		JSONObject dataFromArtist = lastFm.getArtist("Coldplay");
		System.out.println(dataFromArtist);
	}
	
	public static void testTopSongsLastFM(ConnectLastFM lastFm) {
		List<String> topSongs = lastFm.getSongs("Coldplay");
		for (String s : topSongs) {
			System.out.println(s);
		}
	}
	
	public static void testRelatedArtistsLastFM(ConnectLastFM lastFm) {
		List<String> relatedArtists = lastFm.getSimilarArtists("Coldplay");
		for (String s : relatedArtists) {
			System.out.println(s);
		}
	}
	
	public static void testCreatePlayListLastFM(ConnectLastFM lastFm) {
		lastFm.createPlaylist("Coldplay");
	}
}
