package com.example.demo.model.dto.response;

import com.example.demo.model.dto.BookDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderLineResponse {
//    private Long idBook;
//    private String bookName;
//    private String bookAuthor;
//    private Double bookPrice;
    private BookDto bookByIdBook;
    private Long quantity;
}
