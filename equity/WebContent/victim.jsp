<jsp:root 
 xmlns:jsp="http://java.sun.com/JSP/Page"
 xmlns:c="http://java.sun.com/jsp/jstl/core"
 xmlns="http://www.w3.org/1999/xhtml"
 xmlns:s="/struts-tags"
 version="2.0">
 
 <jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
 <jsp:directive.page import="java.util.ArrayList" />
 <jsp:directive.page import="java.util.List" />
 <jsp:output omit-xml-declaration="true" />
 <jsp:output doctype-root-element="HTML" 
 doctype-system="about:legacy-compat" />

 <html lang="en">
 <head>
<s:include value="../../inc/head.inc"></s:include>
<title><s:text name="welcome.title" /></title>
 </head>
 <body>
<s:url id="localeEN" namespace="/" action="locale" >
   <s:param name="request_locale" >en</s:param>
</s:url>
<s:url id="localehin" namespace="/" action="locale" >
   <s:param name="request_locale" >hin</s:param>
</s:url>
<div id="top_buttons">
<s:a href="%{localeEN}" >English</s:a> | <s:a href="%{localehin}" >Hindi</s:a> 
</div>
<div id="logo">logo</div>
<div id="banner">banner</div>
<div id="tabs">tabs</div>
<div id="news">news</div>
<div id="main"><s:text name="welcome.text" /><br/>
<canvas id="canvasfirst" width="780" height="550">
<s:text name="unsupported.error" />
</canvas>
<!-- 
<script type="text/javascript">
var canvas = document.getElementById("canvasfirst");
var context = canvas.getContext("2d");
context.fillStyle = "rgb(0,0,255)";
//context.fillRect(30,30,50,50);
context.strokeStyle = "rgb(255,0,0)";
context.strokeRect(0,0,780,550);
context.lineWidth = 1;
context.beginPath();
context.moveTo(30,30);
context.lineTo(70,70);
context.stroke();
context.endPath();
</script>
-->
</div>
<div id="right_menu">right menu</div>
</body>
 </html>

</jsp:root>