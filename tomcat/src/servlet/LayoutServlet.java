package servlet;

import entity.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import util.JSPLoader;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/", name = "LayoutServlet", initParams = {
        @WebInitParam(name="param", value = "param")
})
public class LayoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        UserService userService = new UserService();
        List<UserEntity> userEntities= userService.findAll();
        req.setAttribute("userEntities", userEntities);
        req.getRequestDispatcher(JSPLoader.getPath("layout")).forward(req, resp);
    }
}
