<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>界面标题</title>
</head>
<body>
 
<a href="helloworld">hello world</a>

<a href="berich">berich</a>
<form action="http://localhost:8080/tuiwen?functionId=loginWx" method="post">
<p>title</p><input name = 'title'>
<p>tags</p>
<p>content</p><input name = 'content'>
<input type="submit" value="提交">
</form>

<a href="byebye">byebye</a>
<form action="https://www.zyykqkq.top/MySpringMVC/byebye?" method="post"> 
<!--  <form action="http://localhost:8080/byebye?" method="post">--> 
<p>idName</p><input name = 'idName' value = 'TEST#3'>
<p>mainHero</p><input name = 'mainHero' value = '测试'>
<p>finalWord</p><input name = 'finalWord' value = '再见'>
<p>time</p><input name = 'time' value = '2023-01-01'>
<p>isTest</p><input name = 'isTest' value = 'prod'>
<input type="submit" value="提交">
</form>

<a href="byebye">Home</a>
<form action="https://www.zyykqkq.top/MySpringMVC/home?" method="post">  
<!--<form action="http://localhost:8080/home?" method="post">   --> 
<p>isTest</p><input name = 'isTest' value = 'prod'>
<input type="submit" value="提交">
</form>

</body>

</html>