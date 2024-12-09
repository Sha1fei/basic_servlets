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
      .paragraph {
        background-color: blueviolet;
      }
      .hidden{

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
        <th>Salary</th>
        <c:if test="${cookie.userId.getValue().equals('admin')}">
          <th>manage</th>
        </c:if>
      </tr>
      <tr>
      <c:forEach var="user" items="${requestScope.userEntities}">
          <td><a href="/user?id=${user.getId()}">${user.getFirst_name()} ${user.getLast_name()}</a></td>
          <td>${user.getContactEntity().getPhone()} ${user.getContactEntity().getType()}</td>
          <td>${user.getCompanyEntity().getName()}</td>
          <td>${user.getSalary()}</td>
          <c:if test="${cookie.userId.getValue().equals('admin')}">
            <td>
              <form class="login_form" action="${pageContext.request.contextPath}/user" method="post">
                <input type="hidden" name="id" id="id" value="${user.getId()}"/>
                <input type="submit" value="удалить" >
              </form>
            </td>
          </c:if>
        </tr>
      </c:forEach>
    </table>
    <%@ include file="imageLoader.jsp"%>
    <div>Доп объекты JSP:</div>
    <div>
      <span class="paragraph">applicationScope:</span> ${applicationScope}
    </span>
    <div>
      <span class="paragraph">sessionScope:</span> ${sessionScope}
    </div>
    <div>
      <span class="paragraph">requestScope:</span> ${requestScope}
    </div>
    <div>
      <span class="paragraph">pageScope:</span> ${pageScope}
    </div>
    <div>
      <span class="paragraph">param:</span> ${param}
    </div>
    <div>
      <span class="paragraph">header:</span> ${header}
    </div>
    <div>
      <span class="paragraph">cookie:</span> ${cookie}
    </div>
    <div>
        <span class="paragraph">initParam:</span> ${initParam}
    </div>


  </body>
</html>
