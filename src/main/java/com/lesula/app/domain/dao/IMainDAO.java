package com.lesula.app.domain.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by enrico on 9/5/14.
 */
public interface IMainDAO<E, ID extends Serializable>{

    E findById(ID id);

    List<E> findAll();

    List<E> findByExample(E exampleInstance, String... excludeProperty);

    void save(E entity);

    void saveOrUpdate(E entity);

    void update(E entity);

    void delete(E entity);

    void flush();

    void clear();
}
