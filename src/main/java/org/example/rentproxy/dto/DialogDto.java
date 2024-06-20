package org.example.rentproxy.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DialogDto {
    private Long id;
    private List<MessageDto> messageDtos;
    private LocalDateTime creationDateTime;
}
