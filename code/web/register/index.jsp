<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%--@elvariable id="user" type="model.User"--%>

<c:set var="isUpdate" value="${(user != null) ? true : false}" />
<c:set var="title" value="${isUpdate ? 'Update user account' : 'Create a new account'}" />
<c:set var="command" value="${isUpdate ? 'auth.Update' : 'auth.Register'}" />
<c:set var="submitText" value="${isUpdate ? 'Update' : 'Create'}" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${title}"/></title>
</head>
<body>

<h1><c:out value="${title}"/></h1>
<form name="frm" method="post" action="/design_pattern/FrontController">
<p>
<label for="username">Username</label><input type="text" name="username" value="${user.username}" ${isUpdate ? "readonly" : "required"}><br>
<label for="password">Password</label><input type="password" name="password" value="${user.password}"><br>
</p>

<p>
<label for="name">Name</label><input type="text" name="name" value="${user.name}"><br>
<label for="email">Email</label><input type="text" name="email" value="${user.email}"><br>
<label for="address">Address</label><input type="text" name="address" value="${user.address.getStreetAddress()}"><br>
<label for="bestFriend">BestFriend</label><input type="text" name="bestFriend" value="${user.bestFriend}"><br>
</p>

<button type="submit" name="command" value="${command}"><c:out value="${submitText}" /></button>
</form>

<p>
Go <a href="/design_pattern/FrontController?command=auth.UserList">back</a>.
</p>

</body>
</html>
