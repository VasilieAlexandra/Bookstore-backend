package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "order_line", schema = "public", catalog = "shopDb")
@IdClass(OrderLinePK.class)
public class OrderLine {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic
    @Column(name = "id_order", nullable = false, insertable = false, updatable = false)
    private Long idOrder;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic
    @Column(name = "id_book", nullable = false, insertable = false, updatable = false)
    private Long idBook;
    @Basic
    @Column(name = "quantity", nullable = false)
    private Long quantity;
    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id", nullable = false)
    private Order orderByIdOrder;
    @ManyToOne
    @JoinColumn(name = "id_book", referencedColumnName = "id", nullable = false)
    private Book bookByIdBook;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return idOrder.equals(orderLine.idOrder) && idBook.equals(orderLine.idBook) && quantity.equals(orderLine.quantity) && orderByIdOrder.equals(orderLine.orderByIdOrder) && bookByIdBook.equals(orderLine.bookByIdBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idBook, quantity, orderByIdOrder, bookByIdBook);
    }
}
