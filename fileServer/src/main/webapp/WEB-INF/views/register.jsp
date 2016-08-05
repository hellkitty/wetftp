<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<form action="memberRegister" method="Post">
<table border="1px">
<tr>
<td>
id
</td>
<td>
<input type="text" name="memberId">
</td>
</tr>

<tr>
<td>
password
</td>
<td>
<input type="password" name="memberPw">
</td>
</tr>

<tr>

<td>
re-password
</td>
<td>
<input type="password" id="memberPwCheck">
</td>
</tr>

<tr>
<td colspan="2">
<button type="submit">register</button>
</td>
</tr>
</table>
</form>
</body>
</html>