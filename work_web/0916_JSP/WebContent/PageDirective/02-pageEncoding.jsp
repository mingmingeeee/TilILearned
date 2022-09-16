<!-- 
인코딩 적용 순서
1. pageEncoding -> 혼동일으키기 떄문엥...가급적 사용 X
2. contentType -> charset="UTF-8"로 
3. 모두 해당되지 않을 경우는 ISO-8859-1 캐릭터  셋 사용 
 -->
<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page pageEncoding="utf-8" %> <!-- 우선 순위 -->
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<title>인코딩 적용 순서</title>
<body>
<p>동해물과 ...</p>
<p>abcde ... </p>
</body>
</html>