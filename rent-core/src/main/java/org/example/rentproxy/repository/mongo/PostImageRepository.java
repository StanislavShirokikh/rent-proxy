package org.example.rentproxy.repository.mongo;

import org.example.rentproxy.repository.mongo.documents.PostImage;
import org.example.rentproxy.repository.mongo.documents.PostImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImageRepository extends MongoRepository<PostImage, String> {
    PostImage findByPostId(Long postId);
}
