package service;

import dao.Dao;
import dao.UserDao;
import entity.User;
import exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public class UserService implements Service<User> {
    private final Dao<User> userDao= new UserDao(User.class);

    @Override
    public User findById(int id) {
        try {
            final var user = userDao.findById(id);
            return user.orElseThrow(() -> new NotFoundException(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);  //TODO: CREATE CUSTOM EXCEPTION
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }
}
