package com.sunkejava.web;

import com.sunkejava.util.HttpRequest;
import com.sunkejava.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WangyilrcServer {
	public static void main(String[] args) {
		String songid = "28784280";
		getMusiclrc(songid);
	}

	public static String getMusiclrc(String songid) {
		String result = "";
		StringUtil strp = new StringUtil();
		String songLrc=HttpRequest.sendGet("http://music.163.com/api/song/lyric?", "os=osx&id="+songid+"&lv=-1&kv=-1&tv=-1");
		songLrc = songLrc.replace("\\n", "");
		JSONObject ojson = JSONObject.fromObject(songLrc);
		JSONObject osp = (JSONObject)ojson.get("lrc");
		result = osp.get("lyric").toString();
		//System.out.println(result);
		return result;
	}

}
