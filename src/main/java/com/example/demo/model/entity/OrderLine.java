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
    @Id
    @Column(name = "id_order", nullable = false)
    private Long idOrder;

    @Id
    @Column(name = "id_book", nullable = false)
    private Long idBook;
    @Column(name = "quantity", nullable = false)
    private Long quantity;
    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "id_book", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Book book;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return idOrder.equals(orderLine.idOrder) && idBook.equals(orderLine.idBook)
                && quantity.equals(orderLine.quantity) && order.equals(orderLine.order)
                && book.equals(orderLine.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idBook, quantity, order, book);
    }
}
