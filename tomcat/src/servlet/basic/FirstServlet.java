package servlet.basic;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/first") //добавлени обработки url servlet
public class FirstServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { //обработка метода GET

        var headersName = req.getHeaderNames(); //вывод headers
        while (headersName.hasMoreElements()) {
            String headerName = headersName.nextElement();
            System.out.println(headerName + ":" + req.getHeader(headerName));
        }
        res.setHeader("token", "1234"); // добавление header в ответ

        res.setContentType("text/html;charset=utf-8");
        try(var writer = res.getWriter()) {
            writer.write("<h1>Hello from First Servlet</h1>");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { //обработка метода POST
        var params = req.getParameterMap();
        params.forEach((key, param) -> System.out.println(key + ": " + Arrays.stream(param).toList())); //вывод параметров запроса
        try(var reader = req.getReader(); var lines = reader.lines()) { // вывод body
            lines.forEach(line -> System.out.println(line));
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
