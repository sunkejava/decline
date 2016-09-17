package com.sunkejava.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



/**
 * YY����������̨��51AVI����
 * ��ҪJson Jar��
 * @author Administrator
 *
 */
/*
*/
public class DataJsonsp {
	public static void main(String[] args) throws Exception {
		/*for(int i = 0;i<YYnubs.length;i++){
			System.out.println(YYnubs[i]);
		}
		*/

	}
	
	
	
	public static JSONArray GetDate(String DataType,String[] ID) throws Exception{
		String urls = null;
		//�����齨���Json�����ŵ�Array����
		JSONArray thisj = new JSONArray();
		JSONArray pthisj = new JSONArray();
		if(DataType == "YY"){
			System.out.println(ID.length);
			for(int p = 0;p<ID.length;p++){
				//System.out.println(ID[p]);
				urls = "http://www.yy.com/u/works/s?yyno="+ID[p]+"&p=1";
			//��Ҫ�׳��쳣
				try{
				URL url2 = new URL(urls);
				BufferedReader bufr2 = new BufferedReader(new InputStreamReader(new BufferedInputStream(url2.openStream()),"utf-8"));
				String line2;
				StringBuffer sb2 = new StringBuffer();
				while((line2 = bufr2.readLine())!= null){
					sb2.append(line2);
				}
				bufr2.close();
				//��sb�ַ���ת��Ϊjson����
				JSONObject jsn1 = JSONObject.fromObject(sb2.toString());
				JSONArray presult = jsn1.getJSONArray("data");
				JSONObject newjsonp = new JSONObject();
				newjsonp.put("song_album_id",Integer.toString(p+1));
				newjsonp.put("song_album",presult.getJSONObject(0).get("ownername"));
				newjsonp.put("song_album1","����YY����");
				int Sumpage = (int) jsn1.get("pages");
				for(int j=0;j<Sumpage;j++){
					String purls = "http://www.yy.com/u/works/s?yyno="+ID[p]+"&p="+(j+1);
					//��Ҫ�׳��쳣
					try{	
					URL purl2 = new URL(purls);
						BufferedReader pbufr2 = new BufferedReader(new InputStreamReader(new BufferedInputStream(purl2.openStream()),"utf-8"));
						String pline2;
						StringBuffer psb2 = new StringBuffer();
						while((pline2 = pbufr2.readLine())!= null){
							psb2.append(pline2);
						}
						pbufr2.close();
						//��sb�ַ���ת��Ϊjson����
						JSONObject pjsn1 = JSONObject.fromObject(psb2.toString());
						JSONArray presult2 = pjsn1.getJSONArray("data");
						
				for(int i = 0;i<presult2.size();i++ ){
					JSONObject concent = (JSONObject) presult2.get(i);
					//��ȡ��Ҫ�ĵ����ݲ�����µ�json����2
					JSONObject newjson = new JSONObject();
					newjson.put("song_id", Integer.toString(i+1));
					//��ȡ�������Ʋ������µ�Json������
					newjson.put("song_title",concent.get("songname"));
					//��ȡ�������ֲ������µ�Json������
					newjson.put("singer", concent.get("ownername"));
					newjson.put("album", concent.get("ownername"));
					//��ȡ�������ŵ�ַ�������µ�Json������
					newjson.put("pic", concent.get("snapshoturl"));
					newjson.put("resurl",concent.get("resurl"));
					//���齨���json�������ݼ��뵽newarr������
					pthisj.add(newjson);
						}
					} catch (Exception e) {
						thisj.add("��ȷ��ID["+ID[p]+"]�Ƿ���ȷ");
						//System.out.println("��ȷ��ID�Ƿ���ȷ");
						}
				}
				
				newjsonp.put("song_list",pthisj);
				pthisj.clear();
				thisj.add(newjsonp);
				} catch(Exception e){
					thisj.add("��ȷ��ID["+ID[p]+"]�Ƿ���ȷ");
					//System.out.println("��ȷ��ID�Ƿ���ȷ");
					}
				}
		}else if(DataType == "YINYUETAI"){
			 urls = "http://www.yinyuetai.com/api/info/get-video-urls?videoId="+ID;
			//��Ҫ�׳��쳣
				URL url2 = new URL(urls);
				BufferedReader bufr2 = new BufferedReader(new InputStreamReader(new BufferedInputStream(url2.openStream()),"utf-8"));
				String line2;
				StringBuffer sb2 = new StringBuffer();
				while((line2 = bufr2.readLine())!= null){
					sb2.append(line2);
				}
				bufr2.close();
//				System.out.println(sb2.toString());
				//��sb�ַ���ת��Ϊjson����
				JSONObject jsonobject2 = JSONObject.fromObject("{data:["+sb2.toString()+"]}");
				//ȡ����Ҫ������data{}�е�����
				JSONArray result2 = jsonobject2.getJSONArray("data");
				for(int i = 0;i<result2.size();i++ ){
					JSONObject concent = (JSONObject) result2.get(i);
					//��ȡ��Ҫ�ĵ����ݲ�����µ�json����2
					JSONObject newjson = new JSONObject();
					//��ȡ�������Ʋ������µ�Json������
					newjson.put("hdVideoUrl",concent.get("hdVideoUrl"));
					//��ȡ�������ֲ������µ�Json������
					newjson.put("hcVideoUrl", concent.get("hcVideoUrl"));
					//��ȡ�������ŵ�ַ�������µ�Json������
					newjson.put("heVideoUrl",concent.get("heVideoUrl"));
					//���齨���json�������ݼ��뵽newarr������
					thisj.add(newjson);
				}
		}else if(DataType == "51AVI"){
			urls = "http://www.51av.us/modules/premium/player/ckplayer/config.php?id="+ID;
			//��ȡString���͵�XML
			InputStream is=downloadXML(urls);
			String outfile=convertStreamToString(is);
			//ȡ����Ҫ���ı�
			String VideUrl = StringUtil.betweenSting(outfile, "<file>", "</file>");
			String VideoSize = StringUtil.betweenSting(outfile, "<size>", "</size>");
			String VideoSeconds = StringUtil.betweenSting(outfile, "<seconds>", "</seconds>");
			String VideoText = "file:"+VideUrl+" size:"+VideoSize+" seconds:"+VideoSeconds;
/*			System.out.println("��Ӱ��ַ��"+VideUrl);
			System.out.println("��Ӱ��С��"+VideoSize);
			System.out.println("��Ӱʱ����"+VideoSeconds);*/
			thisj.add(VideoText);
	} 
		return thisj;

}
	public static InputStream downloadXML(final String urlStr){
		InputStream inStream = null;
					   URL url = null;
					try {
						url = new URL(urlStr);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
					   HttpURLConnection conn = null;
					try {
						conn = (HttpURLConnection) url.openConnection();
						conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)"); 
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
					   conn.setConnectTimeout(5 * 1000);  
					  try {
						conn.setRequestMethod("GET");
						conn.connect();
					} catch (Exception e) {
						// TODO Auto-generated catch block
					}  
					  
					   try {
						   inStream= conn.getInputStream();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
	return inStream;  
	}
	public static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {   
		 
		   BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));   

				StringBuilder sb = new StringBuilder();   



				String line = null;   

				try {   

					while ((line = reader.readLine()) != null) {   

						sb.append(line + "/n");   

					}   

				} catch (IOException e) {   

					e.printStackTrace();   

				} finally {   

					try {   

						is.close();   

					} catch (IOException e) {   

						e.printStackTrace();   

					}   
 
				}   
 
 
 
				return sb.toString();   
 
			}
}
