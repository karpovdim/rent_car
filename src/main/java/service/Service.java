package service;

import entity.BaseEntity;

import java.util.List;

public interface Service<T extends BaseEntity>{
    T findById(int id);

    List<T> findAll();

    T create(T t);

    T update(T t);

    void delete(T t);
}
