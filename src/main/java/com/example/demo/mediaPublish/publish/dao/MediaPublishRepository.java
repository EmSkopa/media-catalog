package com.example.demo.mediaPublish.publish.dao;

import com.example.demo.mediaPublish.publish.model.MediaPublish;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MediaPublishRepository extends MongoRepository<MediaPublish, UUID> {
}
