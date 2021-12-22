package dao;

import entity.BaseEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends BaseEntity> {

    Optional<T> findById(int id) throws SQLException;

    List<T> findAll();

    T create(T t);

    void delete(T t);

    T update(T t);
}
