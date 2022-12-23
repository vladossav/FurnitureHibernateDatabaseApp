package org.example.repositories;

import org.example.Columns;
import org.example.HibernateFactory;
import org.example.entities.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerRepository extends BaseRepo<Customer> {
    public List<Customer> customerList;

    public CustomerRepository() {
        customerList = super.getAll();
    }

    @Override
    public List<Customer> getAll() {
        customerList = super.getAll();
        return customerList;
    }

    public List<Customer> searchByAddressString(String str, Columns col) {
        String hql = String.format("select c From %s c join c.address a where a.%s like :name ", getTableName(), col.getColumnName());
        Session session = HibernateFactory.getSessionFactory().openSession();
        Query<Customer> query = session.createQuery(hql, getType());
        query.setParameter("name", "%" + str + "%");
        List<Customer> list = query.list();
        session.close();
        return list;
    }

    @Override
    protected Class<Customer> getType() {
        return Customer.class;
    }

    @Override
    protected String getTableName() {
        return "Customer";
    }
}
