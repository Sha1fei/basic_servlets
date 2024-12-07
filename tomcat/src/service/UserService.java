package service;

import dao.UserDao;
import entity.CompanyEntity;
import entity.ContactEntity;
import entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    public List<UserEntity> findAll() {
        UserDao userDao = new UserDao();
        return userDao.findAll().stream()
            .reduce(new ArrayList<UserEntity>(),
                (acc, entity) -> {
                    if(acc.stream().filter(userFullEntity -> userFullEntity.getId() == entity.getId()).findFirst().isPresent()){
                        var index = acc.indexOf(entity);
                        var removedEntity = acc.remove(index);
                        acc.add(new UserEntity(
                            entity.getId(),
                            entity.getFirst_name(),
                            entity.getLast_name(),
                            entity.getSalary(),
                            new ContactEntity(
                                entity.getContactEntity().getId(),
                                entity.getContactEntity().getPhone() + ", " + removedEntity.getContactEntity().getPhone(),
                                entity.getContactEntity().getType()+ ", " + removedEntity.getContactEntity().getType()
                            ),
                            new CompanyEntity(
                                entity.getCompanyEntity().getId(),
                                entity.getCompanyEntity().getName(),
                                entity.getCompanyEntity().getDate())
                        )
                        );
                    } else {
                        acc.add(entity);
                    }
                    return acc;
                }, (acc, entity) ->  acc);
    }

    public Optional<UserEntity> findById(Integer id) {
        UserDao userDao = new UserDao();
        return userDao.findById(id);
    }

    public Boolean deleteById(Integer id) {
        UserDao userDao = new UserDao();
        return userDao.delete(id);
    }
}
