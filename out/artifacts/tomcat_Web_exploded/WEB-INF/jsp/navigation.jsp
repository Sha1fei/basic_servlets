<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
  .navigation{
    display: flex;
    justify-content: flex-end;
    padding: 10px;
    background-color: aqua;
  }
  body{
    margin: 0;
  }
</style>
<div class="navigation">
  <c:if test="${true}">
    <a href="/login">Login Page</a>
  </c:if>
  <c:if test="${false}">
    <a href="/admin">Admin Page</a>
  </c:if>

</div>
