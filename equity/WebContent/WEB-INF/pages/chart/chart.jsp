<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.lionsardesai.common.Constants" %>
    
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<s:include value="../../inc/head.inc" ></s:include>
<script type="text/javascript" src="../js/charts.js"></script>
<SCRIPT type="text/javascript" src="../js/jquery-1.7.js"></SCRIPT>
<!-- <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>-->
<script type="text/javascript" src="../js/jquery-ui-1.8.16.custom.min.js"></script>
<!--<script type=text/javascript src="http://www.magic-rss.com/magic_js.cgi?z=32159&f=y"></script>-->
<script type="text/javascript">
			$(function(){

				// Tabs
				$('#tabs').tabs();
	
			});
</script>
<title><s:text name="welcome.title" /></title>
<s:url id="graphbase" namespace="/" action="getgraphbase" />
<s:url id="logourl" namespace="/" action="getlogo" />
</head>
 <body>
<s:url id="localeEN" namespace="/" action="locale" >
   <s:param name="request_locale" >en</s:param>
</s:url>
<s:url id="localehin" namespace="/" action="locale" >
   <s:param name="request_locale" >hin</s:param>
</s:url>
<div class="commonheader">
<div class="headerdock" id="top_buttons">
<s:text name="global.login" /> | <s:text name="global.logout" /> | <s:text name="global.portfolio" /> | <s:a href="%{localeEN}" >English</s:a> | <s:a href="%{localehin}" >Hindi</s:a> 
</div>
<s:url id="bannerurl" namespace="/" action="getbanner" />
<div class="headerimages">
<div id="logo" style="font-size:25"><!-- <img src="${logourl}" width="" height="" /> --> LOGO </div>
<div id="banner" style="font-size:25"><!-- <img src="${bannerurl}" width=95% height=100% /> --> Banners </div>
<!-- end common images div -->
</div>
<div id="ticker">tickers</div>
<div id="news">news</div>
<!-- end common header here -->
</div>
<div class="lowerbody">
<div id="main">
<div id="tabs">
			<ul>
				<li><a href="#tabs-1"><s:text name="chart.tab1" /></a></li>
				<li><a href="#tabs-2"><s:text name="chart.tab2" /></a></li>
				<li><a href="#tabs-3"><s:text name="chart.tab3" /></a></li>
				<li><a href="#tabs-4"><s:text name="chart.tab4" /></a></li>
			</ul>
			<s:text name="test.common"></s:text>
			<div id="tabs-1"><s:text name="welcome.text" /><br/>
			
<s:form action="chart.action">
<table>
<tr><s:actionerror /></tr>
<tr><td>
	<s:select list="model.listAll" name="id"></s:select></td><td>
	<s:submit type="button" label="refresh"></s:submit></td></tr>
</table>
</s:form>
id entered : <s:property value="model.id"/>
<canvas id="canvasfirst" width="780" height="550">
<s:text name="unsupported.error" />
</canvas>
</div>
<div id="tabs-2"><s:text name="test.lorem1" /></div>
<div id="tabs-3"><s:text name="test.lorem2" /></div>
<div id="tabs-4"><s:text name="test.lorem3" /></div>
<!-- end tabs -->
</div>
<!-- end main display area -->
</div>
<div id="right_menu" class="ui-corner-all ui-widget-content">
<UL>
	<LI>Lorem ipsum
		<ul>
			<LI>dolor sit amet</LI>
			<li>consectetur adipiscing elit</li>
		</ul>
	</LI>
	<LI>Duis nisl justo
		<ul>
			<li>fermentum ac egestas id</li>
			<li>vestibulum a eros</li>
		</ul>
	</LI>
	<LI>Morbi malesuada
		<ul>
			<li>accumsan auctor</li>
		</ul>
	</LI>
	<LI>Quisque
		<ul>
			<li>ipsum justo</li>
		</ul>
	</LI>
	<LI>In viverra
		<ul>
			<li>dignissim odio</li>
			<li>sed facilisis</li>
			<li>tortor accumsan vitae</li>
		</ul>
	</LI>
</UL>
<script>
drawLine2(<s:property value="closeList"/>,<s:property value="size" />);
</script>
</div>
<!-- close lower body tab -->
</div>
<div class="commonfooter">
<s:text name="test.lorem1"></s:text>
<HR />
<div class="upperfooter" >
<s:text name = "test.lorem2" /></div>
<div class="lowerfooter" >
<s:text name="global.logout"></s:text> | <s:text name="global.get.premium"></s:text> | <s:text name="global.conditions"></s:text> | <s:text name="global.contact.us"></s:text> 
</div>
</div>
</body>
</html>	