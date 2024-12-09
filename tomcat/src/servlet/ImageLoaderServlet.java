package servlet;

import dto.CreateImageLoaderDto;
import entity.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ImageLoaderService;
import service.UserService;
import util.JSPLoader;

import java.io.File;
import java.io.IOException;
import java.util.List;
@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/imageLoader/*")
public class ImageLoaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var requestURI = req.getRequestURI();
        var imagePath = requestURI.replace("/imageLoader", "");
        ImageLoaderService imageLoaderService = ImageLoaderService.getInstance();
        imageLoaderService.get().ifPresentOrElse(image -> {
            resp.setContentType("application/octet-stream");
            try(image; var outpurStream = resp.getOutputStream()){
                int currentByte;
                while((currentByte = image.read()) != -1) {
                    outpurStream.write(currentByte);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, () ->  resp.setStatus(404));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        var image =  req.getPart( "image" );
        ImageLoaderService imageLoaderService = ImageLoaderService.getInstance();
        var imageDto = CreateImageLoaderDto.builder().image(image).build();
        imageLoaderService.upload(imageDto);
        UserService userService = UserService.getInstance();
        List<UserEntity> userEntities= userService.findAll();
        req.setAttribute("userEntities", userEntities);
        req.getRequestDispatcher(JSPLoader.getPath("layout")).forward(req, resp);
    }
}
