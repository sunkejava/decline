<%@ page language="java"
	import="net.sf.json.JSONArray,com.sunkejava.util.DataJsonsp"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String searchContext = request.getParameter("q");
	if(searchContext == "" || searchContext == null){
		response.sendRedirect("404.jsp");
	}else{
	DataJsonsp p = new DataJsonsp();
	String[] pa = new String[100];
	pa = searchContext.split(",");
	JSONArray result2 = p.GetDate("YY", pa); 
	out.print("var NeiCeList = " + result2.toString());
	}
%>