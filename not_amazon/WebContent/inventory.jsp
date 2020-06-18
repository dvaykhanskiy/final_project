<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Da Market</title>
  </head>
  <body>
    <div>
      <h1>Inventory Management</h1>
      <a href="${pageContext.request.contextPath}/">View All</a>
<a href="${pageContext.request.contextPath}/add">Sell an Item</a> 
    </div>
    <div>
      <table border="1">
  <caption>All Items in Store</caption>
  
  <tr>
    <td>Name</td>
    <td>Price</td>
    <td>Stock</td>
    <td>Available</td>
  </tr>
  <c:forEach var="book" items="${books}">
    <tr>
      <td><c:out value="${book.name}" /></td>
      <td><c:out value="${book.price}" /></td>
      <td><c:out value="${book.stock}" /></td>
      <td><c:out value="${book.available}" /></td>
      <td>
        <a href="${pageContext.request.contextPath}/update?action=rent&id=
	  <c:out value="${book.id}" />">RENT
	</a>
        <a href="${pageContext.request.contextPath}/update?action=return&id=
	  <c:out value="${book.id}" />">RETURN
	</a>
        <a href="${pageContext.request.contextPath}/edit?id=
	  <c:out value="${book.id}" />">EDIT
	</a>
      </td>
    </tr>
  </c:forEach>
</table>
    </div>
  </body>
</html>