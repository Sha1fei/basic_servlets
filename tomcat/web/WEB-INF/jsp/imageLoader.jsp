<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form class="login_form" action="${pageContext.request.contextPath}/imageLoader" method="post" enctype="multipart/form-data">
    <input type="file" name="image" id="imageId"/>
    <input type="submit" value="Загрузить какртинку на сервер" >
</form>
<img src="${pageContext.request.contextPath}/imageLoader/last" alt="Картинка не загружена">
