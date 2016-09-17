package com.sunkejava.web;

import com.sunkejava.util.HttpRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WangyiMusicurlServer {
	public static void main(String[] args) {
		GetMusicurl("29849140");
	}
	
	public static String GetMusicurl(String song_id){
		String song_url = "";
		String resurlt = HttpRequest.sendGet("http://music.163.com/api/song/detail/?", "id="+song_id+"&ids=["+song_id+",26346072]");
		JSONObject pjsn1 = JSONObject.fromObject(resurlt);
		JSONArray ps2 = pjsn1.getJSONArray("songs");
		JSONObject concents = (JSONObject) ps2.get(0);
		song_url=concents.get("mp3Url").toString();
		return song_url;
	}
	
}
