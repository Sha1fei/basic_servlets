package service;

import dao.ImageLoaderDao;
import dao.UserDao;
import dto.CreateImageLoaderDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import servlet.basic.DownloadServlet;
import util.PropertiesUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageLoaderService {
    @Getter
    private static ImageLoaderService instance = new ImageLoaderService();

    public final String basePath = PropertiesUtil.get("image.base.url");

    @SneakyThrows
    public Optional<InputStream> get() {
        ImageLoaderDao imageLoaderDao = new ImageLoaderDao();
        var imageFullPath = Path.of(basePath, imageLoaderDao.findLast() != null ? imageLoaderDao.findLast().getImage() : "");
        return Files.exists(imageFullPath) && !Files.isDirectory(imageFullPath) ? Optional.of(Files.newInputStream(imageFullPath)) : Optional.empty();
    }

    @SneakyThrows
    public void upload(CreateImageLoaderDto imageDto) {
        var imageContent = imageDto.getImage().getInputStream();
        var imageName = imageDto.getImage().getSubmittedFileName();
        var imageFullPath = Path.of(basePath, imageName);
        ImageLoaderDao imageLoaderDao = new ImageLoaderDao();
        imageLoaderDao.save(imageName);
        Files.createDirectories(imageFullPath.getParent());
        Files.write(imageFullPath, imageContent.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

}
