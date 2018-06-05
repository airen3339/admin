<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcome.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
    <c:choose>
       <c:when test="${not empty sessionScope.userName}">
         <center>
           
           <h1>
                             欢迎${userName}访问我们的网站!!!<br/><br/><br/>
           <a style="color:blue">查看内部资料</a>&nbsp;&nbsp;&nbsp;   
           <a href="<c:url value='exit.jsp'/>">注销</a>
           </h1>
           
         </center>
       </c:when>
       <c:otherwise>
          <c:set value="请您先登陆" var="message" scope="request"/>
          <jsp:forward page="/login.jsp"/>
       </c:otherwise>
     </c:choose>
  </body>
</html>
