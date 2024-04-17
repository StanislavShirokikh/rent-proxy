package org.example.rentproxy.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomsTypeResponse {
    private Long id;
    private String name;
}
