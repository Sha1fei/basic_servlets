package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.basic.DownloadServlet;
import util.JSPLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String UNIQUE_ID = "userId";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var login = req.getParameter("login");
        var password = req.getParameter("password");
        if(login != null && password != null && !login.isBlank() && !password.isBlank()){
            var customCookie = new Cookie(UNIQUE_ID, login);
            customCookie.setMaxAge(15*60);
            resp.addCookie(customCookie);
            req.setCharacterEncoding("utf-8");
            resp.sendRedirect("/");
        } else {
            req.getRequestDispatcher(JSPLoader.getPath("login")).forward(req, resp);
        }
    }
}
