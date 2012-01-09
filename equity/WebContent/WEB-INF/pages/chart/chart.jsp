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
<script src="http://www.blastcasta.com/feed-to-json.aspx?feedurl=http://feeds.bbci.co.uk/news/business/rss.xml&param=data1"></script>
<script src="http://www.blastcasta.com/feed-to-json.aspx?feedurl=http://feeds.reuters.com/reuters/USpersonalfinanceNews&param=data2"></script>
<script src="../js/jQuery.rollChildren.js"></script>  
<script type="text/javascript">
			$(function(){

				var main_div = document.getElementById("news");
		        for(i=0;i < data1.rss.channel[0].item.length && i<data2.rss.channel[0].item.length;i++) {
		            main_div.innerHTML += "<div id=" + i + ">" + data1.rss.channel[0].title +" - "+ data1.rss.channel[0].item[i].title + "<a href="+ data1.rss.channel[0].item[i].link + ">[know more]</a></div>";
		            main_div.innerHTML += "<div id=" + i + ">" + data2.rss.channel[0].title +" - "+ data2.rss.channel[0].item[i].title + "<a href="+ data2.rss.channel[0].item[i].link + ">[know more]</a></div>";
		            
		        }
		        
		        var right_div= document.getElementById("right_navi_lower_contents");
		        for(i=0;i<5;i++) {
		        	right_div.innerHTML += "<div id= \"right_navi_lower_newsitem_"
		        	+i+"\">"+data2.rss.channel[0].item[i].description+"</div>";
		        }
		        
				// Tabs
				var $tabs=$('#tabs').tabs();
				$tabs.tabs('select',1);
				
				$('#news').rollchildren({  
	                delay_time : 5000,  
	                loop : true,  
	                pause_on_mouseover : true,  
	                roll_up_old_item : true,  
	                speed: 'fast',
	                height : 0
	            });
				
				$('#right_navi_lower_contents').rollchildren({  
	                delay_time : 10000,  
	                loop : true,  
	                pause_on_mouseover : true,  
	                roll_up_old_item : true,  
	                speed: 'fast',
	                height : 0
	            });
	
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
<div id="news" class="ui-corner-all"></div>
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
			<s:text name="test.lorem1" />
</div>
<div id="tabs-2">
<canvas id="canvasfirst" width="701" height="301">
<s:text name="unsupported.error" />
</canvas>
<s:if test="%{techChart!=null}">
<BR /><BR />
<canvas id="canvassecond" width="700" height="50">
<s:text name="unsupported.error" />
</canvas>
<SCRIPT type="text/javascript">
alternategraph(<s:property value="techData"/>,<s:property value="size" />);
</SCRIPT>
</s:if>
<s:form action="chart.action">
<table>
<tr><s:actionerror /></tr>
<tr><td>
	<s:select list="model.listAll" name="id"></s:select></td><td>
	<s:submit type="button" label="refresh"></s:submit></td></tr><tr><td>
	<s:radio name="techChart" label="Technical Analysis" list="{'roc','mfi', 'rsi', 'macd', 'stoc', 'ad', 'ema' }" /></td><td></td></tr><tr><td>
	<s:radio name="chartType" label="Graph Type" list="{'bar', 'candle', 'line'}" /></td><td></td></tr>
</table>
</s:form>
id entered : <s:property value="model.id"/>
</div>
<div id="tabs-3"><s:text name="test.lorem2" /></div>
<div id="tabs-4"><s:text name="test.lorem3" /></div>
<!-- end tabs -->
</div>
<!-- end main display area -->
</div>
<div id="right_menu" class="ui-corner-all ui-widget-content">
<div id="right_upper">
<div id="right_navi_upper_header" class="ui-widget-header ui-corner-all" style="height:27px; margin:3px 3px 0px 3px; line-height:30px">
Right Navi
</div>
<DIV id="right_navi_upper_contents">
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
<s:if test="%{chartType=='line'}">
<script>
drawLine2(<s:property value="closeList"/>,<s:property value="volumeList"/>,<s:property value="size" />);
</script>
</s:if>
<s:elseif test="%{chartType=='candle'}">
<script>
drawCandle(<s:property value ="highList"/>,<s:property value ="lowList" />,<s:property value ="openList" />,<s:property value="closeList" />,<s:property value = "size" />);
</script>
</s:elseif>
<!-- close right navi upper contents div -->
</DIV>
<!-- close right_upper -->
</div>
<div id="right_lower">
<div id="right_navi_lower_header" class="ui-widget-header ui-corner-all" style="height:27px; margin:3px 3px 0px 3px; line-height:30px">
This Just In
</div>
<DIV id="right_navi_lower_contents" style="margin:3px 3px 0px 3px">
</DIV>
<!-- close right_lower -->
</div>
<!-- close righ navi -->
</div>
<!-- close lower body tab -->
</div>
<div class="commonfooter">
<s:text name="test.lorem1"></s:text>
<HR />
<div class="upperfooter" >
<s:text name = "test.lorem2" /></div>
<div class="lowerfooter" >
<s:text name="global.logout"></s:text> | <s:text name="global.get.premium"></s:text> | <a href="../terms.action"><s:text name="global.conditions"></s:text></a> | <a href="../contactUs.action"><s:text name="global.contact.us"></s:text></a> 
</div>
</div>
</body>
</html>	