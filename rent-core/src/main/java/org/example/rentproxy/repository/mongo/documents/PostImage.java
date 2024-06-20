package org.example.rentproxy.repository.mongo.documents;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
@Data
public class PostImage {
    @MongoId
    private String id;
    private Long postId;
    private List<Image> images;
}
