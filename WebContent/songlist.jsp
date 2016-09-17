<%@ page language="java" import="net.sf.json.JSONArray,com.sunkejava.web.WangyiMusicServer" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String searchContext = request.getParameter("q");
if(searchContext == "" || searchContext == null){
response.sendRedirect("404.jsp");
}else{
WangyiMusicServer a = new WangyiMusicServer();
String uid = request.getParameter("q");
JSONArray q=a.getSongList(uid);
out.print("var NeiCeList = "+q);
}
%>