package com.example.demo.mediaCatalog.dao;

import com.example.demo.mediaCatalog.model.MediaCatalog;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MediaCatalogRepository extends MongoRepository<MediaCatalog, UUID>{

    @Query("{ 'genre' : { $regex: ?0 } }")
    List<MediaCatalog> findMediaByGenre(String genre, Sort sort);

    @Query("{ 'originatingCountry' : { $regex: ?0 } }")
    List<MediaCatalog> findMediaByOriginatingCountry(String country, Sort sort);

    @Query("{ 'artistName' : { $regex: ?0 } }")
    List<MediaCatalog> findMediaByArtistName(String artistName, Sort sort);

    @Query("{ 'mediaName' : { $regex: ?0 } }")
    List<MediaCatalog> findMediaByMediaName(String mediaName, Sort sort);

    @Query("{ 'publisher' : { $regex: ?0 } }")
    List<MediaCatalog> findMediaByPublisher(String publisher, Sort sort);
}
