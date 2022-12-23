package org.example.repositories;

import org.example.entities.Furniture;
import java.util.List;

public class FurnitureRepository extends BaseRepo<Furniture> {
    public List<Furniture> furnList;

    public FurnitureRepository() {
        furnList = super.getAll();
    }

    @Override
    public List<Furniture> getAll() {
        furnList = super.getAll();
        return furnList;
    }

    @Override
    protected Class<Furniture> getType() {
        return Furniture.class;
    }

    @Override
    protected String getTableName() {
        return "Furniture";
    }
}
