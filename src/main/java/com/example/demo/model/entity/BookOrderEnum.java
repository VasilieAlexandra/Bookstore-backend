package com.example.demo.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public enum BookOrderEnum {
    MIN_MAX_PRICE("MinMaxPrice"),
    MAX_MIN_PRICE("MaxMinPrice"),
    ALPHABETICALLY_ASC("AlphabeticallyAsc"),
    ALPHABETICALLY_DESC("AlphabeticallyDesc"),
    NO_ORDER("NoOrder")
    ;

    private String value;

    BookOrderEnum(String value) {
        this.value = value;
    }
}
