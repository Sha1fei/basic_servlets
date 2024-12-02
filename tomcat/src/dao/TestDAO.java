package dao;

import java.sql.Driver;

public class TestDAO {
    public static void main(String[] args) {
        UserDao userDao = UserDao.getInstance();
        var m = userDao.findAll();
        System.out.println(m);
    }
}
