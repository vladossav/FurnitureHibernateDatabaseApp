package org.example.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Sale {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "contract_num", nullable = true)
    private Integer contractNum;
    @Basic
    @Column(name = "furniture_id", nullable = true)
    private Integer furnitureId;
    @Basic
    @Column(name = "amount", nullable = true)
    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContractNum() {
        return contractNum;
    }

    public void setContractNum(Integer contractNum) {
        this.contractNum = contractNum;
    }

    public Integer getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(Integer furnitureId) {
        this.furnitureId = furnitureId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id) && Objects.equals(contractNum, sale.contractNum) && Objects.equals(furnitureId, sale.furnitureId) && Objects.equals(amount, sale.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contractNum, furnitureId, amount);
    }
}
