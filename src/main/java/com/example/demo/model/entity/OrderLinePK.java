package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLinePK implements Serializable {
    @Column(name = "id_order", nullable = false, insertable = false, updatable = false)
    @Basic
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;
    @Column(name = "id_book", nullable = false, insertable = false, updatable = false)
    @Basic
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBook;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderLinePK that = (OrderLinePK) o;

        if (!Objects.equals(idOrder, that.idOrder)) return false;
        if (!Objects.equals(idBook, that.idBook)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOrder != null ? idOrder.hashCode() : 0;
        result = 31 * result + (idBook != null ? idBook.hashCode() : 0);
        return result;
    }
}
