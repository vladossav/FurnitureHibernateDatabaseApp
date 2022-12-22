package org.example.repositories;

import org.example.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public abstract class BaseRepo<T> {
    public List<T> getAll() {
        String hql = String.format("From %s", getTableName());
        Session session = HibernateFactory.getSessionFactory().openSession();
        Query<T> query = session.createQuery(hql, getType());
        List<T> list = query.list();
        session.close();
        return list;
    }

    public Boolean checkIdNotExists(int id) {
        String hql = String.format("select 1 from %s f where f.id = :key", getTableName());
        Session session = HibernateFactory.getSessionFactory().openSession();
        Query<Boolean> query = session.createQuery(hql, Boolean.class);
        query.setParameter("key", id);
        Boolean result = query.uniqueResult() == null;
        session.close();
        return result;
    }

    public void save(T entity) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(entity);
        tx1.commit();
        session.close();
    }

    public void edit(T entity) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(entity);
        tx1.commit();
        session.close();
    }

    public void delete(T entity) {
        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(entity);
        tx1.commit();
        session.close();
    }

    public List<T> searchByString(String str, Columns column) {
        String hql = String.format("From %s where %s like :name ", getTableName(), column.getColumnName());
        Session session = HibernateFactory.getSessionFactory().openSession();
        Query<T> query = session.createQuery(hql, getType());
        query.setParameter("name", "%" + str + "%");
        List<T> list = query.list();
        session.close();
        return list;
    }

    public List<T> searchByRange100(String str, Columns column) {
        String hql = String.format("From %s where %s between :val-100.0 and :val+100.0 ",
                getTableName(), column.getColumnName());

        Session session = HibernateFactory.getSessionFactory().openSession();
        Query<T> query = session.createQuery(hql, getType());
        query.setParameter("val", str);
        List<T> list = query.list();
        session.close();
        return list;
    }



    public enum Columns {
        FURNITURE_NAME("name"), FURNITURE_MODEL("model"), FURNITURE_COLOR("color"),
        FURNITURE_COST("cost"), FURNITURE_LEN("length"), FURNITURE_WIDTH("width"), FURNITURE_HEIGHT("height"),
        FURNITURE_WEIGHT("weight"),
        ADDRESS_CITY("city"), ADDRESS_STREET("street"),
        CUSTOMER_NAME("name");

        Columns(String col) {
            this.column = col;
        }
        private final String column;

        public String getColumnName() {
            return column;
        }
    }
    protected abstract Class<T> getType();
    protected abstract String getTableName();
}
