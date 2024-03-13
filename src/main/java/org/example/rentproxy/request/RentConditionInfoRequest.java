package org.example.rentproxy.request;

import lombok.Data;
@Data
public class RentConditionInfoRequest {
    private String deposit;
    private String commission;
    private String typeOfPayment;
}
