package org.example.rentproxy.filter;

import lombok.Getter;

@Getter
public enum PostOrder {
    DESCENDING_PRICE("price"),
    ASCENDING_PRICE("price"),
    DESCENDING_DATE("date"),
    ASCENDING_DATE("date");

    private final String fieldName;
    PostOrder(String fieldName) {
        this.fieldName = fieldName;
    }
}
