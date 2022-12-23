package org.example.repositories;

import org.example.Columns;
import org.example.HibernateFactory;
import org.example.entities.Customer;
import org.example.entities.Sale;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SaleRepository extends BaseRepo<Sale> {
    public List<Sale> saleList;

    public SaleRepository() {
        saleList = super.getAll();
    }

    public List<Sale> searchByFurnitureString(String str, Columns col) {
        String hql = String.format("From %s s join s.furniture f where f.%s like :name ",
                getTableName(), col.getColumnName());
        Session session = HibernateFactory.getSessionFactory().openSession();
        Query<Sale> query = session.createQuery(hql, getType());
        query.setParameter("name", "%" + str + "%");
        List<Sale> list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<Sale> getAll() {
        saleList = super.getAll();
        return saleList;
    }

    @Override
    protected Class<Sale> getType() {
        return Sale.class;
    }

    @Override
    protected String getTableName() {
        return "Sale";
    }
}
