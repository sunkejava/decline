package com.sunkejava.web;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunkejava.util.DataJson;
import com.sunkejava.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Searchserver extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String searchContent=URLEncoder.encode(request.getParameter("q"), "utf-8");; // ��ѯ����
		String jsoncallback=request.getParameter("jsoncallback"); 
		String start=request.getParameter("start"); // ��ʼҳ
		System.out.println("jsoncallback:"+jsoncallback);
		System.out.println("start:"+start);
		//String searchContent = request.getParameter("q");
		if (StringUtil.isEmpty(searchContent)) {
			request.setAttribute("error", "������Ҫ���������ݣ�");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		} else {
			//start = "1";
			//System.out.println(searchContent);
			String urls = null;
			// �����齨���Json�����ŵ�Array����
			JSONArray thisj = new JSONArray();
			urls = "http://www.yy.com/u/works/s?yyno=" + searchContent + "&p="+start;
			// ��Ҫ�׳��쳣
			URL url2 = new URL(urls);
			BufferedReader bufr2 = new BufferedReader(
					new InputStreamReader(new BufferedInputStream(url2.openStream()), "utf-8"));
			String line2;
			StringBuffer sb2 = new StringBuffer();
			while ((line2 = bufr2.readLine()) != null) {
				sb2.append(line2);
			}
			bufr2.close();
			String maxpage = StringUtil.betweenSting(StringUtil.betweenSting(sb2.toString(), "pages", "data"), ":",
					",");
			request.setAttribute("maxpage", maxpage);
			// ��sb�ַ���ת��Ϊjson����
			JSONObject jsn1 = JSONObject.fromObject(sb2.toString());
			// ȡ����Ҫ������data{}�е�����
			JSONArray result2 = jsn1.getJSONArray("data");
			//��Ӽ�������ҳ����
			for(int l=0; l < result2.size();l++){
				JSONObject info = result2.getJSONObject(l);
				info.put("maxpage",maxpage);
			}
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println(jsoncallback+"("+result2.toString()+")");
			out.flush();
			out.close();
		}
	}
	

}
