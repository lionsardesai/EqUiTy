<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
    
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<s:include value="../../inc/head.inc" ></s:include>
<script type="text/javascript" src="../js/canvas.js"></script>
<SCRIPT type="text/javascript" src="../js/jquery-1.7.js"></SCRIPT>
<script type="text/javascript" src="../js/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript">
			$(function(){
	
			});
</script>
<title><s:text name="welcome.title" /></title>
<s:url id="welcomeBanner" namespace="/" action="getWelcomeBanner" />
</head>
 <body>
<div class="welcome">
<a href="../chart/chart.action"><img src="${welcomeBanner}" height="" width=""/></a>
</div>
</body>
</html>	