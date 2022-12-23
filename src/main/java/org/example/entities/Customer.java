package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Customer {
    @Id
    private Integer code;
    private String name;
    @Column(name = "phone_num", nullable = true, length = -1)
    private String phoneNum;
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(code, customer.code) && Objects.equals(name, customer.name) && Objects.equals(phoneNum, customer.phoneNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, phoneNum);
    }

    public static List<String> columns() {
        return List.of("№","Код","Название","Номер телефона",
                "Город","Улица","Номер здания");
    }

    public List<String> getRawStringList() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add(code.toString());
        list.add(name);
        list.add(phoneNum);
        list.add(address.getCity());
        list.add(address.getStreet());
        list.add(address.getNBuild().toString());
        return list;
    }
}
