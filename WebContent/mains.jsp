<%@ page language="java" import="net.sf.json.JSONArray,com.sunkejava.util.DataJsonp"
contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String searchContext = request.getParameter("q");
if(searchContext == "" || searchContext == null){
	response.sendRedirect("404.jsp");
}else{
	DataJsonp p = new DataJsonp();	
	JSONArray result2 = p.GetDate("YY",searchContext);
		out.print("var NeiCeList = " + result2.toString());
	}
%>