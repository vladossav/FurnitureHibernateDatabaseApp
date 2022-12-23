package org.example.repositories;

import org.example.entities.Address;
import java.util.List;

public class AddressRepository extends BaseRepo<Address> {
    public List<Address> addressList;

    public AddressRepository() {
        addressList = super.getAll();
    }

    @Override
    public List<Address> getAll() {
        addressList = super.getAll();
        return addressList;
    }

    @Override
    protected Class<Address> getType() {
        return Address.class;
    }

    @Override
    protected String getTableName() {
        return "Address";
    }
}
