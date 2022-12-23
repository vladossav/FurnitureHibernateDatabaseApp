package org.example.repositories;

import org.example.entities.Contract;

import java.util.List;

public class ContractRepository extends BaseRepo<Contract> {
    public List<Contract> contractList;

    public ContractRepository() {
        contractList = super.getAll();
    }

    @Override
    public List<Contract> getAll() {
        contractList = super.getAll();
        return contractList;
    }

    @Override
    protected Class<Contract> getType() {
        return Contract.class;
    }

    @Override
    protected String getTableName() {
        return "Contract";
    }
}
