<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <style>
        .login{
            display: flex;
            justify-content: center;
        }
        .login_form{
            display: flex;
            width: 300px;
            flex-direction: column;
            background-color: aqua;
            border-radius: 10px;
            margin: 10px;
            padding: 10px;
        }
        .login_form label {
            display: flex;
            justify-content: space-between;
        }
    </style>
</head>
<body>
    <div class="login">
        <form class="login_form" action="${pageContext.request.contextPath}/login" method="get">
            <label for="login">Login:
                <input type="text" name="login" id="login"/>
            </label><br>
            <label for="password">Password:
                <input type="password" name="password" id="password"/>
            </label><br>
            <button type="submit">Send</button>
        </form>
    </div>
</body>
</html>

