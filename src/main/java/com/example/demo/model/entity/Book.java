package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "author", nullable = false, length = 100)
    private String author;

    @JdbcTypeCode(Types.BINARY)
    @Column(name = "image")
    @ToString.Exclude
    private byte[] image;

    @Column(name = "price", nullable = false, precision = 0)
    private Long price;

    @Column(name = "id_owner", nullable = false, length = 255)
    private String idOwner;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_category"))
    @ToString.Exclude
    private Set<Category> categories;
    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    private Set<OrderLine> order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(name, book.name)
                && Objects.equals(author, book.author) && Arrays.equals(image, book.image)
                && Objects.equals(price, book.price) && Objects.equals(idOwner, book.idOwner)
                && Objects.equals(quantity, book.quantity);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, author, price, idOwner, quantity);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

}
