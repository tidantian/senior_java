<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User List</title>
</head>
<body>
	<%
		if (request.getAttribute("loginfailed") != null) {
	%>
	<font color="red">Login failed. Please Try Again.</font>
	<br>
	<%
		}
	%>
	<form name="login" action="check" method="post">
		<input type="text" name="username"> <input type="password"
			name="password"> <input type="submit" name="submit">
	</form>
</body>
</html>