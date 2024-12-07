package servlet;

import entity.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import servlet.basic.DownloadServlet;
import util.JSPLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        UserService userService = new UserService();
        List<UserEntity> userEntities= userService.findAll();
        req.setAttribute("userEntities", userEntities);
        req.getRequestDispatcher(JSPLoader.getPath("user")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        var id =  Integer.parseInt(req.getParameter("id"));
        UserService userService = new UserService();
        userService.deleteById(id);
        List<UserEntity> userEntities= userService.findAll();
        req.setAttribute("userEntities", userEntities);
        req.getRequestDispatcher(JSPLoader.getPath("user")).forward(req, resp);
    }
}
