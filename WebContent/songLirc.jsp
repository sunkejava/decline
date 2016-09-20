<%@ page language="java" import="com.sunkejava.web.WangyilrcServer"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String searchContext = request.getParameter("q");
	if (searchContext == "" || searchContext == null) {
		response.sendRedirect("404.jsp");
	} else {
		WangyilrcServer a = new WangyilrcServer();
		String songid = request.getParameter("q");
		String q = a.getMusiclrc(songid);
		out.print("var cont = '" + q + "';");
	}
%>