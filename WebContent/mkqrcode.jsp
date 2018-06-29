<%@ page language="java" import="java.util.*,java.net.URL" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>qrcode 生成</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta property="qc:admins" content="4746776325477164510063757" >
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
   <textarea id="topictextarea" rows="5" cols="134" name="codemsg" form="qrcode_generate" ></textarea>

    <form id="qrcode_generate" action="<%=basePath%>servlet/qrcode/make" method="get" target="qrcodeimg">
	  <input type="submit" value="生成二维码" />
	</form>
	
	
	<br />
	<iframe src="<%=basePath%>servlet/qrcode/make" name="qrcodeimg" width="400" height="400"></iframe>
   
	

  </body>
  
  
  
</html>
