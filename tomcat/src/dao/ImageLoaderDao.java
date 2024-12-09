package dao;

import entity.CompanyEntity;
import entity.ContactEntity;
import entity.ImageLoaderEntity;
import entity.UserEntity;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageLoaderDao {
    private  static final ImageLoaderDao INSTANCE = new ImageLoaderDao();
    public static ImageLoaderDao getInstance() {
        return INSTANCE;
    }

    private static final String SAVE_IMAGE = "INSERT INTO company_storage.employee_images (image) VALUES (?);";
    private static final String FIND_LAST = "SELECT * FROM company_storage.employee_images WHERE id=(SELECT max(id) FROM company_storage.employee_images);";

    @SneakyThrows
    public ImageLoaderEntity findLast() {
        Class <Driver> driverClass = Driver.class;
        try(var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(FIND_LAST);) {
            var resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new ImageLoaderEntity(
                    resultSet.getLong("id"),
                    resultSet.getString("image")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @SneakyThrows
    public void save(String imagePath) {
        Class <Driver> driverClass = Driver.class;
        try(var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(SAVE_IMAGE);){
            preparedStatement.setObject(1, imagePath);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
