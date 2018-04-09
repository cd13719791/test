<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="com.moyou.moyouRms.util.*" %>
<%@ page language="java" import="com.moyou.moyouRms.util.mac.*" %>
<%
	request.setAttribute("title", "用户登录");
	String ip = StringUtil.getIpAddr(request);
	String mac = GetMacAddress.getMacAddress(ip);
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
</head>
<body>
<h2>test</h2>        
<h2><%=ip %></h2>        
<h2>[=========]<%=mac %></h2>        
</body>