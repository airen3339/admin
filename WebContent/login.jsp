<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
  	<center>
    <h1 style="color:blue">登录页面</h1><br>
    <h1 style="color:red">${requestScope.message}</h1><br>
    <form action="/yong/LoginServlet" method="post">
    	用户名：<input type="text" name="userName"><br>
    	密  码：<input type="password" name="userPass"><br>
    	<input type="submit" value="登陆">
    </form>
    
    <form action="/yong/QueryServlet" method="get" >
    	<input type="submit" value="查询">
    </form>
    
    <form  action="${pageContext.request.contextPath }/UploadServlet"  
        	method="post" enctype="multipart/form-data">  
        	
        	<input type="submit" name="name"> <input type="file" name="file1">  
  
    </form> 
    
    </center>
  </body>
</html>
