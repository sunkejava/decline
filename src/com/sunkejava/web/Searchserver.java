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
		String searchContent=URLEncoder.encode(request.getParameter("q"), "utf-8");; // 查询数据
		String jsoncallback=request.getParameter("jsoncallback"); 
		String start=request.getParameter("start"); // 起始页
		System.out.println("jsoncallback:"+jsoncallback);
		System.out.println("start:"+start);
		//String searchContent = request.getParameter("q");
		if (StringUtil.isEmpty(searchContent)) {
			request.setAttribute("error", "请输入要搜索的内容！");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		} else {
			//start = "1";
			//System.out.println(searchContent);
			String urls = null;
			// 创建组建后的Json对象存放的Array数组
			JSONArray thisj = new JSONArray();
			urls = "http://www.yy.com/u/works/s?yyno=" + searchContent + "&p="+start;
			// 需要抛出异常
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
			// 将sb字符串转换为json对象
			JSONObject jsn1 = JSONObject.fromObject(sb2.toString());
			// 取的需要的数据data{}中的数据
			JSONArray result2 = jsn1.getJSONArray("data");
			//添加集合数（页数）
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
