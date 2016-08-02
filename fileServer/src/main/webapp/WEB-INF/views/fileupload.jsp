<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>WetCookie FileServer</title>
</head>
<body>
<h1>
	WetCookie FileServer  
</h1>
access Limit : ${pathLimit }<br>
current path : ${path }
<br>
<form action="pathConfig" method="post">
path config : 
	<input type="text" name="path" id="defaultPathValue"><input type="submit">
</form>
<br>
<br>
<br>
<div>

</div>


<form action="uploadFile" method="post" enctype="multipart/form-data">
	<input type="file" name="file">
	<input type="submit" name="upload">
</form>

<br>
<br>

<br>
<ul>
<c:if test="${parentPath!=null }">
<li>
<a href="pathChange?path=${parentPath }">..</a>
</li>
</c:if>
<c:forEach var="file" items="${fileList }">
<li>
<a href="pathChange?path=${path }${file }/">${file }</a>
</li>
</c:forEach>
</ul>

</body>
</html>