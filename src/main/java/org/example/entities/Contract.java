package org.example.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Contract {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "number", nullable = false)
    private Integer number;
    @Basic
    @Column(name = "customer_code", nullable = true)
    private Integer customerCode;
    @Basic
    @Column(name = "reg_date", nullable = true, length = -1)
    private String regDate;
    @Basic
    @Column(name = "done_date", nullable = true, length = -1)
    private String doneDate;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Integer customerCode) {
        this.customerCode = customerCode;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(String doneDate) {
        this.doneDate = doneDate;
    }

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
}
