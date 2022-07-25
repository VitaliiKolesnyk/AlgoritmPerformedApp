package com.algoritm.app.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDao {
    private SessionFactory sessionFactory;

    protected BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
