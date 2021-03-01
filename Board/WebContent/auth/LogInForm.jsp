<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>로그인</title>
</head>

<body>
    <h2>사용자 로그인</h2>
    <form action="login" method="post">
        아이디: <input type="text" name="userID"><br>
        암호: <input type="password" name="userPassword"><br>
        <input type="submit" value="로그인">
    </form>
</body>

</html>