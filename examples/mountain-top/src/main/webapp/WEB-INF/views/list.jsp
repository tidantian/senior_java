<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User List</title>
</head>
<body>
	Welcome: ${user.username}
	<br>
	<form name="exit" action="show" method="post">
		<input name="exit_submit" type="submit" value="退出">
	</form>
</body>
</html>