package com.example.demo.model.entity;

import lombok.*;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "date", nullable = false)
    private Date date;
    @Basic
    @Column(name = "id_address", nullable = false, insertable = false, updatable = false)
    private Long idAddress;
    @Basic
    @Column(name = "id_user", nullable = false, length = 255)
    private String idUser;
    @ManyToOne
    @JoinColumn(name = "id_address", referencedColumnName = "id", nullable = false)
    private ShippingAddress shippingAddressByIdAddress;
    @OneToMany(mappedBy = "orderByIdOrder")
    @ToString.Exclude
    private Set<OrderLine> orderLinesById;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(date, order.date) && Objects.equals(idAddress, order.idAddress) && Objects.equals(idUser, order.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, idAddress, idUser);
    }


}
