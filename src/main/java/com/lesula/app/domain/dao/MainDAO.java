package com.lesula.app.domain.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by enrico on 9/5/14.
 */
@Repository
public abstract class MainDAO<E, ID extends Serializable>
        implements IMainDAO<E, ID> {

    private final Class<E> entityClass;
    private SessionFactory sessionFactory;

    public MainDAO(Class<E> entityClass, SessionFactory sessionFactory) {

        this.entityClass = entityClass;
        this.sessionFactory = sessionFactory;
    }

    public Class<E> getEntityClass() {
        return entityClass;
    }

    @SuppressWarnings({"unchecked"})
    public E findById(ID id) {
        Object obj = null;
        obj = sessionFactory.getCurrentSession().get(getEntityClass(), id);
        return (E) obj;
    }

    public E loadById(ID id) {
        Object obj = null;
        obj = sessionFactory.getCurrentSession().load(getEntityClass(), id);
        return (E) obj;
    }

    public E findByIdOrCreateNew(ID id) {
        Object obj = findById(id);
        if (obj == null) {
            try {
                obj = entityClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return (E) obj;
    }

    public List<E> findAll() {
        return findByCriteria();
    }

    public String getTableName() {
        return getEntityClass().getSimpleName();
    }

    @SuppressWarnings("unchecked")
    public List<E> findByExample(E exampleInstance, String... excludeProperty) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
        Example example = Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }

    public void save(E entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    public void update(E entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    public void saveOrUpdate(E entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    public void saveOrUpdateAndFlush(E entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        sessionFactory.getCurrentSession().flush();
    }

    public void delete(E entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public void flush() {
        sessionFactory.getCurrentSession().flush();
    }

    public void clear() {
        sessionFactory.getCurrentSession().clear();
    }

    @SuppressWarnings("unchecked")
    protected List<E> findByCriteria(Criterion... criterion) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }

}
