package dao;

import entity.CompanyEntity;
import entity.ContactEntity;
import entity.UserEntity;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {
    private  static final UserDao INSTANCE = new UserDao();
    public static UserDao getInstance() {
        return INSTANCE;
    }

    private static final String SAVE_USER = "INSERT INTO company_storage.employee (first_name, last_name, salary, company_id) VALUES (?, ?, ?, ?);";
    private static final String DELETE_USER = "DELETE FROM company_storage.employee WHERE id = ?;";
    private static final String UPDATE_USER = "UPDATE company_storage.employee SET first_name = ?, last_name = ?, salary = ?, company_id = ? WHERE id = ?;";
    private static final String FIND_BY_ID_USER = "SELECT * FROM company_storage.employee emp JOIN company_storage.company comp ON emp.company_id = comp.id JOIN company_storage.employeeid_contactid ec ON emp.id = ec.employeeId JOIN company_storage.contact cont ON cont.id = ec.contactId WHERE id = ?;";
    private static final String FIND_ALL_USER = "SELECT * FROM company_storage.employee emp JOIN company_storage.company comp ON emp.company_id = comp.id JOIN company_storage.employeeid_contactid ec ON emp.id = ec.employeeId JOIN company_storage.contact cont ON cont.id = ec.contactId;";

    @SneakyThrows
    public List<UserEntity> findAll() {
        Class <Driver> driverClass = Driver.class;
        try(var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(FIND_ALL_USER);) {
            var resultSet = preparedStatement.executeQuery();
            List<UserEntity> userEntities = new ArrayList<>();
            while(resultSet.next()){
                var userEntity = new UserEntity(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getLong("salary"),
                        new ContactEntity(resultSet.getLong("id"), resultSet.getString("phone"), resultSet.getString("type")),
                        new CompanyEntity(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getDate("date").toLocalDate())
                );
                userEntities.add(userEntity);
            }
            return userEntities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<UserEntity> findById(Integer id) {
        try(var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(FIND_BY_ID_USER);) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            UserEntity userEntity = null;
            if(resultSet.next()){
                userEntity = new UserEntity(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getLong("salary"),
                        new ContactEntity(resultSet.getLong("id"), resultSet.getString("phone"), resultSet.getString("type")),
                        new CompanyEntity(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getDate("date").toLocalDate())
                );
            }
            return Optional.ofNullable(userEntity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserEntity save(UserEntity entity) {
        try(var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(SAVE_USER);){
            preparedStatement.setObject(1, entity.getFirst_name());
            preparedStatement.setObject(2, entity.getLast_name());
            preparedStatement.setObject(3, entity.getSalary());
            preparedStatement.setObject(4, entity.getCompanyEntity().getId());
            preparedStatement.execute();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong("id"));
            }
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(Integer id) {
        try(var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(DELETE_USER);) {
            preparedStatement.setObject(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(UserEntity entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_USER);) {
            preparedStatement.setObject(1, entity.getFirst_name());
            preparedStatement.setObject(2, entity.getLast_name());
            preparedStatement.setObject(3, entity.getSalary());
            preparedStatement.setObject(4, entity.getCompanyEntity().getId());
            preparedStatement.setObject(5, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
