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
@Getter
@Setter
public class Sale {
    @Id
    private Integer id;
    @Column(name = "contract_num", nullable = true)
    private Integer contractNum;
    //@Column(name = "furniture_id", nullable = true)
    //private Integer furnitureId;
    @ManyToOne
    @JoinColumn(name = "furniture_id", referencedColumnName = "id")
    private Furniture furniture;
    private Integer amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id) && Objects.equals(contractNum, sale.contractNum) &&  Objects.equals(amount, sale.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contractNum, amount);
    }

    public static List<String> columns() {
        return List.of("№","Номер договора","Название","Модель", "Цвет","Количество");
    }

    public List<String> getRawStringList() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add(contractNum.toString());
        list.add(furniture.getName());
        list.add(furniture.getModel());
        list.add(furniture.getColor());
        list.add(amount.toString());
        return list;
    }
}
