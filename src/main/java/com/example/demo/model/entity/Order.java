package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "orders", schema = "public", catalog = "shopDb")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "id_address", nullable = false)
    private Long idAddress;
    @Column(name = "id_user", nullable = false, length = 255)
    private String idUser;
    @ManyToOne
    @JoinColumn(name = "id_address", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ShippingAddress shippingAddress;
    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private Set<OrderLine> orderLines;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(date, order.date)
                && Objects.equals(idAddress, order.idAddress) && Objects.equals(idUser, order.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, idAddress, idUser);
    }


}
