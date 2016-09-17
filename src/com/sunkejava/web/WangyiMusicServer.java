package com.sunkejava.web;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sunkejava.util.HttpRequest;
import com.sunkejava.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WangyiMusicServer {
	public static void main(String[] args) {
		/**
		 * get请求获取163music用户歌单
		 */
//		String uid = "317483295";
//		getSongList(uid);
		String searcs= "美女";
		getSearchsSonglist(searcs);
	}
	
	/**
	 * 
	 * 根据ID获取music信息
	 * lrc:http://music.163.com/api/song/lyric?os=osx&id=22761631&lv=-1&kv=-1&tv=-1
	 * @param uid
	 * @return
	 */
	public static JSONArray getSongList(String uid){
		String p = "";
		JSONArray thisj = new JSONArray();
		JSONArray pthisj = new JSONArray();
		StringUtil strp = new StringUtil();
		String songSheet=HttpRequest.sendGet("http://music.163.com/api/user/playlist/?", "offset=0&limit=100&uid="+uid);
		JSONObject pjsn1 = JSONObject.fromObject(songSheet);
		JSONArray ps2 = pjsn1.getJSONArray("playlist");
		JSONObject newjson = new JSONObject();
        JSONObject newjsonp = new JSONObject();
        for(int j=0;j<ps2.size();j++){
        	JSONObject concents = (JSONObject) ps2.get(j);
    		newjsonp.put("song_album_id",concents.get("id").toString());
    		newjsonp.put("song_album",concents.get("name"));
    		newjsonp.put("song_album1","来自网易云音乐");
    		String songDetails = HttpRequest.sendGet("http://music.163.com/api/playlist/detail", 
    				"id="+concents.get("id"));
    		String pq = strp.betweenSting(songDetails, "result\":", ",\"code");
    		JSONObject pjsn2 = JSONObject.fromObject(pq);
    		JSONArray presult2 = pjsn2.getJSONArray("tracks");
    		for(int i =0;i<presult2.size();i++){
    			JSONObject concent = (JSONObject) presult2.get(i);
    			JSONArray aj = concent.getJSONArray("artists");
    			JSONObject concent2 = aj.getJSONObject(0);
    			String as =strp.betweenSting(concent.toString(),"album\":",",\"starred");
    			JSONObject pa2 = JSONObject.fromObject(as);
				newjson.put("song_id",concent.get("id").toString());
				newjson.put("song_title", concent.get("name"));
				newjson.put("singer", concent2.get("name"));
				newjson.put("album", pa2.get("name"));
				newjson.put("pic", pa2.get("blurPicUrl"));
				newjson.put("resurl", concent.get("mp3Url"));
				pthisj.add(newjson);
    		}
    		newjsonp.put("song_list",pthisj);
			pthisj.clear();
			thisj.add(newjsonp);
        }
        return thisj;	
        

        
	}
	
	public static JSONArray getSearchsSonglist(String searchContext){
		JSONArray songList_Result = new JSONArray();
		String postUrl = "http://music.163.com/api/search/get";
		String forData;
		try {
			forData = "s="+URLEncoder.encode(searchContext,"utf-8")+"&type=1&offset=0&total=true&limit=60";
			System.out.println(forData);
			System.out.println(HttpRequest.sendPost(postUrl, forData));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return songList_Result;
	}
}


