<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <fmt:setLocale value="ru_Ru" />
    <fmt:setBundle basename="translation" />
    <div><fmt:message key="page.hello" />, ${cookie.userId.getValue()}</div>
</div>
