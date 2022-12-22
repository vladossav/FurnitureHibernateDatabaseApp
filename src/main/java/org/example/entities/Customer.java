package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "code", nullable = false)
    private Integer code;
    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;
    @Basic
    @Column(name = "address_id", nullable = true)
    private Integer addressId;
    @Basic
    @Column(name = "phone_num", nullable = true, length = -1)
    private String phoneNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(code, customer.code) && Objects.equals(name, customer.name) && Objects.equals(addressId, customer.addressId) && Objects.equals(phoneNum, customer.phoneNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, addressId, phoneNum);
    }
}
