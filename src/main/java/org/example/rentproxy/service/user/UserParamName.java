package org.example.rentproxy.service.user;

import lombok.Getter;

@Getter
public enum UserParamName {
    DEFAULT_CURRENCY("defaultCurrency"),
    AUTO_CONVERSION("autoConversion");

    private final String name;

    UserParamName(String name) {
        this.name = name;
    }
}
