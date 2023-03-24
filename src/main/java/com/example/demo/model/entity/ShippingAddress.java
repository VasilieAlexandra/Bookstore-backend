package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "shipping_address", schema = "public", catalog = "shopDb")
public class ShippingAddress {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_user", nullable = false, length = 255)
    private String idUser;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "state", nullable = false, length = 50)
    private String state;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "address", nullable = false, length = 100)
    private String address;
    @OneToMany(mappedBy = "shippingAddress")
    @ToString.Exclude
    private Collection<Order> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingAddress that = (ShippingAddress) o;
        return Objects.equals(id, that.id)
                && Objects.equals(idUser, that.idUser) && Objects.equals(country, that.country)
                && Objects.equals(state, that.state) && Objects.equals(city, that.city)
                && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUser, country, state, city, address);
    }

}
