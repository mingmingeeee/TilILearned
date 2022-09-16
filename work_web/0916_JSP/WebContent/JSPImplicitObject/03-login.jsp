<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<form method="post" action="./03-response.jsp">
		<label>아이디: <input type="text" name="userId"></label><br>
		<label>비밀번호: <input type="password" name="pwd"></label><br>
		<input type="submit" value="로그인">
	</form>
</body>
</html>