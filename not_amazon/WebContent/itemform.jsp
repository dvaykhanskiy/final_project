<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>AIT Library</title>
  </head>
  <body>
    <div>
      <h1>Inventory Management</h1>
      
      <div>
        <a href="${pageContext.request.contextPath}/">VIEW ALL</a>
        <a href="${pageContext.request.contextPath}/add">ADD A BOOK</a> 
      </div>
    </div>
    <div>
      <c:if test="${item != null}">
        <h2>Edit Book</h2>
        <form action="update" method="post">
          <input type="hidden" name="id" value="<c:out value="${item.id}" />" />
          
          <label>
            Title
            <input type="text" name="name" value="<c:out value="${item.name}" />" />
          </label>
          <label>
            Author
            <input type="text" name="price" value="<c:out value="${item.price}" />" />
          </label>
          <label>
            # of Copies
            <input type="text" name="stock" value="<c:out value="${item.stock}" />" />
          </label>
          <input type="submit" value="Save" name="submit" />
          <input type="submit" value="Delete" name="submit" />
        </form>
      </c:if>
      <c:if test="${item == null}">
        <h2>Add Book</h2>
        <form action="insert" method="post">
          <input type="hidden" name="id" />
          
          <label>
            Title
            <input type="text" name="name" />
          </label>
          <label>
            Author
            <input type="text" name="price" />
          </label>
          <label>
            # of Copies
            <input type="text" name="stock" />
          </label>
          <input type="submit" value="Add" name="submit" />
        </form>
      </c:if>
    </div>
  </body>
</html>