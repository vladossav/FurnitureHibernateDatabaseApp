package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateFactory {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {return sessionFactory;}
}
