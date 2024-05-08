package org.example.rentproxy.repository.mongo;

import org.example.rentproxy.repository.mongo.documents.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
}
