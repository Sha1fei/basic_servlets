<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
    <title>Title</title>
    <style>
      table {
        border: 1px solid grey;
      }
      th {
        border: 1px solid grey;
      }
      td {
        border: 1px solid grey;
      }
    </style>
  </head>
  <body>
    <%@ include file="navigation.jsp"%>
    <table>
      <tr>
        <th>Employee</th>
        <th>Contact</th>
        <th>Company</th>
      </tr>
      <c:forEach var="user" items="${requestScope.userEntities}">
        <tr>
          <td><a href="/user?id=${user.getId()}">${user.getFirst_name()}</a></td>
          <td>${user.getContactEntity().getPhone()}</td>
          <td>${user.getCompanyEntity().getName()}</td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
