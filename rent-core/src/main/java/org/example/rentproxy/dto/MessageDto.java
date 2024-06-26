package org.example.rentproxy.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class MessageDto {
    private Long id;
    private Long dialogId;
    private Long statusId;
    private String text;
    private LocalDateTime creationDateTime;
}
