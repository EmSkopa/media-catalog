package com.example.demo.dao;

import com.example.demo.model.MediaCatalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MediaCatalogRepository extends MongoRepository<MediaCatalog, UUID> {
}
