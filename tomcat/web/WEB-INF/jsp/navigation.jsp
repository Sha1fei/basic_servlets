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
    <div>Hello, ${cookie.userId.getValue()}</div>
</div>
