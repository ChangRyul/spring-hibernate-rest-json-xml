package com.lesula.app.domain.dao;

import com.lesula.app.domain.tables.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by enrico on 9/5/14.
 */
@Repository
public class UserDAO extends MainDAO<User, Integer> implements IUserDAO{

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        super(User.class, sessionFactory);
    }
}
