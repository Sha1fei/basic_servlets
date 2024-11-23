package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet("/download") //добавлени обработки url servlet
public class DownloadServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { //обработка метода GET со скачиванием файла

        res.setHeader("Content-Disposition", "attachment;filename=test.json");
        res.setContentType("application/json");
        res.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var printWriter = res.getOutputStream();
             var stream = DownloadServlet.class.getClassLoader().getResourceAsStream("test.json")) {
            printWriter.write(stream.readAllBytes());
        }

    }
}
