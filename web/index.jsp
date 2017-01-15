<%-- 
    Document   : index
    Created on : Oct 3, 2016, 5:16:31 PM
    Author     : Sergey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
response.setStatus(301);
response.setHeader( "Location", "local");
response.setHeader( "Connection", "close" );
%>
