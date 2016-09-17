<%@ page language="java" import="com.sunkejava.web.WangyiMusicurlServer" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String searchContext = request.getParameter("q");
if(searchContext == "" || searchContext == null){
response.sendRedirect("404.jsp");
}else{
WangyiMusicurlServer a = new WangyiMusicurlServer();
String songid = request.getParameter("q");
String q=a.GetMusicurl(songid);
response.sendRedirect(q);
}
%>