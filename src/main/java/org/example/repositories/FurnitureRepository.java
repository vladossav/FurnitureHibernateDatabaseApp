package org.example.repositories;

import org.example.HibernateFactory;
import org.example.entities.Furniture;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class FurnitureRepository extends BaseRepo<Furniture> {
    public List<Furniture> furnList;

    @Override
    public List<Furniture> getAll() {
        furnList = super.getAll();
        return super.getAll();
    }

    public FurnitureRepository() {}





    @Override
    protected Class<Furniture> getType() {
        return Furniture.class;
    }

    @Override
    protected String getTableName() {
        return "Furniture";
    }
}
