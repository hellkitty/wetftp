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
hi ${member.memberId }. <a href="logout">logout</a>
<br>
<br>
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

uploadForm
<form action="uploadFile" method="post" enctype="multipart/form-data">
	<input type="file" name="file">
	<input type="hidden" name="path" value="${path }">
	<input type="submit" name="upload">
</form>

<br>
<br>
${result }
<br>
<br>


<table border="1px">
<thead>
<tr>
<th>check</th>
<th>name</th>
<th>size</th>
<th>permission</th>
<th>owner</th>
<th>lastModify</th>
<th>download</th>
<th>createLink</th>
<th>delete</th>
</tr>
</thead>

<tbody>
<c:if test="${parentPath!=null }">
<tr>
<td>
</td>
<td>
<a href="pathChange?path=${parentPath }">..</a>
</td>
</tr>
</c:if>

<c:forEach var="file" items="${fileList }">
<tr>
<td>
<input type="checkbox">
</td>
<td>
<c:if test="${!file.isFile }">
<a href="pathChange?path=${path }${file.fileName }/">${file.fileName }</a>
</c:if>
<c:if test="${file.isFile }">
${file.fileName }
</c:if>
</td>

<td>
<c:if test="${file.isFile }">
${file.fileSize }
</c:if>

</td>

<td>${file.filePermission }</td>
<td>${file.fileOwner }</td>
<td>${file.lastModify }</td>

<td>
<c:if test="${file.isFile&&file.readPermission  }">
<a href="download?path=${path }&fileName=${file.fileName }">down</a>
</c:if>
</td>
<td>
<c:if test="${file.isFile }">
create
</c:if>
</td>
<td>
<c:if test="${file.isFile&&file.readPermission }">
<a href="deleteFile?path=${path }${file.fileName }/">del</a>
</c:if>

</td>

</tr>
</c:forEach>

</tbody>

<tfoot>
<tr>
<td>
</td>
</tr>
</tfoot>

</table>

</body>
</html>