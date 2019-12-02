<%@ page import="view.auth.AuthHelper" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="isSearchResults" value="${(field != null && query != null) ? true : false}" />
<c:set var="noResultsText" value="${(isSearchResults) ? 'The search produced no results.' : 'No users registered.'}" />

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Authentication Example</title>

    <style>
        table {
            border-collapse: collapse;
        }

        table td {
            border: 1px solid #AAA;
            padding: 5px;
        }
    </style>
</head>

<body>
<h2>Users list</h2>
<c:choose>
    <c:when test="${users != null && users.size() > 0}">
        <table>
            <thead>
            <tr>
                <td>Username</td>
                <td>Password</td>
                <td>Name</td>
                <td>Email</td>
                <td>Address</td>
                <td>BestFriend</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="item">
                <tr>
                    <td><c:out value="${item.getUsername()}"/></td>
                    <td><c:out value="${item.getPassword()}"/></td>
                    <td><c:out value="${item.getName()}"/></td>
                    <td><c:out value="${item.getEmail()}"/></td>
                    <td><c:out value="${item.address.getStreetAddress()}"/></td>
                    <td><c:out value="${item.getBestFriend()}"/></td>
                    <td>
                        <form name="frm" method="get"
                              action="/design_pattern/FrontController">
                            <input type="hidden" name="username" value="${item.username}">
                            <button type="submit" name="command" value="auth.Update">Edit</button>
                        </form>
                    </td>
                    <td>
                        <form name="frm" method="post"
                              action="/design_pattern/FrontController">
                            <input type="hidden" name="username" value="${item.username}">
                            <button type="submit" name="command" value="auth.Delete">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <c:out value="${noResultsText}" />
    </c:otherwise>
</c:choose>


<form name="frm">
    <table>
        <tr>
            <td colspan=2 style="font-size:12pt;" align="center">
                        <h3>Search User</h3></td>
        </tr>
           
        <tr></tr>
        <td>Name</td>
          
        <td>
            <form name="frm" method="get" action="/design_pattern/FrontController">
                <input type="hidden" name="field" value="name">
                <input type="text" name="query" placeholder="Insert query?" value="${query}">
                <button type="submit" name="command" value="auth.UserList">Search</button>
            </form>
        </td>
        </tr>
        <tr>
            <td>Address</td>
              
            <td>
                <form name="frm" method="get" action="/design_pattern/FrontController">
                    <input type="hidden" name="field" value="address">
                    <input type="text" name="query" placeholder="Insert query?" value="${query}">
                    <button type="submit" name="command" value="auth.UserList">Search</button>
                </form>
            </td>
        </tr>
         
        <tr>
            <td>BestFriend</td>
              
            <td>
                <form name="frm" method="get" action="/design_pattern/FrontController">
                    <input type="hidden" name="field" value="bestFriend">
                    <input type="text" name="query" placeholder="Insert query?" value="${query}">
                    <button type="submit" name="command" value="auth.UserList">Search</button>
                </form>
            </td>
        </tr>
         
    </table>
</form>
<p>
    Go <a href="/design_pattern/">back</a>.
</p>
</body>
</html>