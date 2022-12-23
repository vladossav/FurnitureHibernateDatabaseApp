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
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Contract {
    @Id
    private Integer number;
    @Column(name = "customer_code", nullable = true)
    private Integer customerCode;
    @Column(name = "reg_date", nullable = true, length = -1)
    private String regDate;
    @Column(name = "done_date", nullable = true, length = -1)
    private String doneDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return Objects.equals(number, contract.number) && Objects.equals(customerCode, contract.customerCode) && Objects.equals(regDate, contract.regDate) && Objects.equals(doneDate, contract.doneDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, customerCode, regDate, doneDate);
    }

    public static List<String> columns() {
        return List.of("№","Номер договора","Код заказчика","Дата регистрации","Дата выполнения");
    }

    public List<String> getRawStringList() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add(number.toString());
        list.add(customerCode.toString());
        list.add(regDate);
        list.add(doneDate);
        return list;
    }
}
