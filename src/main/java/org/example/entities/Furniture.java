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
@Table(name = "furniture", schema = "public")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Furniture {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private String model;
    private Double cost;
    private String color;
    private Short length;
    private Short width;
    private Short height;
    private Integer weight;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Furniture furniture = (Furniture) o;
        return Objects.equals(id, furniture.id) && Objects.equals(name, furniture.name) && Objects.equals(model, furniture.model) && Objects.equals(cost, furniture.cost) && Objects.equals(color, furniture.color) && Objects.equals(length, furniture.length) && Objects.equals(width, furniture.width) && Objects.equals(height, furniture.height) && Objects.equals(weight, furniture.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, model, cost, color, length, width, height, weight);
    }

    public static List<String> columns() {
        return List.of("№","Наименование","Модель","Стоимость",
                "Цвет","Длина(мм)","Ширина(мм)","Высота(мм)","Вес(г)");
    }

    public List<String> getRawStringList() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add(name);
        list.add(model);
        list.add(cost.toString());
        list.add(color);
        list.add(length.toString());
        list.add(width.toString());
        list.add(height.toString());
        list.add(weight.toString());
        return list;
    }
}
