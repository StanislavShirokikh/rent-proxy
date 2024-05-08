package org.example.rentproxy.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class ImageDto {
    private String id;
    private MultipartFile image;
}
