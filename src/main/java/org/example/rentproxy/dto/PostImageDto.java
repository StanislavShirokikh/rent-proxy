package org.example.rentproxy.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostImageDto {
    private String id;
    private Long postId;
    private List<ImageDto> images;
}
