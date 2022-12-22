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
@Getter
@Setter
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "city", nullable = false, length = -1)
    private String city;
    @Basic
    @Column(name = "street", nullable = false, length = -1)
    private String street;
    @Basic
    @Column(name = "n_build", nullable = false)
    private Short nBuild;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Short getnBuild() {
        return nBuild;
    }

    public void setnBuild(Short nBuild) {
        this.nBuild = nBuild;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(nBuild, address.nBuild);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, nBuild);
    }
}
