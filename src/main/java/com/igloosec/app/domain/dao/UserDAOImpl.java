package com.igloosec.app.domain.dao;

import com.igloosec.app.domain.tables.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by enrico on 9/5/14.
 */
@Repository
public class UserDAOImpl extends MainDAO<User, Integer> implements UserDAO {

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        super(User.class, sessionFactory);
    }
}
