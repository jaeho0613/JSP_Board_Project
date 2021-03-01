<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>회원 등록</title>
</head>

<body>
    <h1>회원 등록</h1>
    <form action="signUp" method="post">
        이름: <input type="text" name="userName"><br>
        아이디: <input type="text" name="userID"><br>
        암호: <input type="password" name="userPassword"><br>
        <input type="submit" value="추가">
        <input type="reset" value="취소">
    </form>

</body>

</html>