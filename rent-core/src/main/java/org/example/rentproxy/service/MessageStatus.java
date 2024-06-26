package org.example.rentproxy.service;

import lombok.Getter;

@Getter
public enum MessageStatus {
    READ("Прочитано"),
    UNREAD("Непрочитано");

    private final String fieldName;

    MessageStatus(String fieldName) {
        this.fieldName = fieldName;
    }
}
