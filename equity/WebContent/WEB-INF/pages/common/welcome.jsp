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
<s:include value="../../inc/head.inc" ></s:include>
 <script type="text/javascript">
 <s:include value="../../js/canvas.js" ></s:include>
 </script>
<title><s:text name="welcome.title" /></title>
<s:url id="graphbase" namespace="/" action="getgraphbase" />
<s:url id="logourl" namespace="/" action="getlogo" />

 </head>
 <body onLoad="init(${graphbase})">
<s:url id="localeEN" namespace="/" action="locale" >
   <s:param name="request_locale" >en</s:param>
</s:url>
<s:url id="localehin" namespace="/" action="locale" >
   <s:param name="request_locale" >hin</s:param>
</s:url>
<div id="top_buttons">
<s:a href="%{localeEN}" >English</s:a> | <s:a href="%{localehin}" >Hindi</s:a> 
</div>
<s:url id="bannerurl" namespace="/" action="getbanner" />

<div id="logo"><img src="${logourl}" width="" height="" /></div>
<div id="banner"><img src="${bannerurl}" width="" height="" /></div>
<div id="tabs">tabs</div>
<div id="ticker">tickers</div>
<div id="news">news</div>
<div id="main"><s:text name="welcome.text" /><br/>
<canvas id="canvasfirst" width="780" height="550">
<s:text name="unsupported.error" />
</canvas>
</div>
<div id="right_menu"> to be written
</div>
</body>
 </html>

</jsp:root>