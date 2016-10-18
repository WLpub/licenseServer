<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
<title>c:set 标签实例</title>
</head>
<body>
<%
  java.util.ArrayList<String> list = new java.util.ArrayList<String>();
  list.add("First");
  list.add("Second");
  list.add("Third");
  list.add("Fourth");
  list.add("Fifth");
 
  java.util.ArrayList<java.util.ArrayList<String>> list2 = new java.util.ArrayList<java.util.ArrayList<String>>();
  list2.add(list);
  list2.add(list);
  list2.add(list);
  list2.add(list);
  list2.add(list);
  pageContext.setAttribute("list", list2, PageContext.PAGE_SCOPE);
%>
<c:forEach var="i" items="${list}" varStatus="status">
	第<c:out value="${status.index}"/>行：  
	<c:forEach var="j" items="${i}">
   		Element Value:<c:out value="${j}"/>&nbsp;&nbsp;&nbsp;
   </c:forEach><p></p>
</c:forEach>
<!-- 
 
-->
 <form action="login" method="POST" onreset="alert('reset now!')">
	&nbsp;&nbsp;&nbsp;&nbsp;username:<input type="text" name="name" value=""/><br/>
	&nbsp;&nbsp;&nbsp;&nbsp;password:<input type="password" name="password" value=""/><br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;sex:<input type="radio" name="sex" value="male"/>Male&nbsp;<input type="radio" name="sex" value="female"/>Female<br/>
	&nbsp;&nbsp;&nbsp;hobby:<input type="radio" name="hobby[0]" value="sport"/>Sport<input type="radio" name="hobby[1]" value="music">Music<br/>
	introduction:<textarea name="introduction"></textarea><br/>
	&nbsp;&nbsp;&nbsp;photo:<input type="file" name="photo"/><br/>
	<input type="button" value="submit" onclick="alert('submit btn clicked')" name="subBtn"/><input type="button" value="cancle" onclick="alert('cancle btn clicked')" name="cancleBtn"/>
</form>
</body>
</html>