package com.example.demo.mediaCatalog.dao;

import com.example.demo.mediaCatalog.model.MediaCatalog;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MediaCatalogDao{

    int insertMedia(UUID id, MediaCatalog newMedia);

    default int insertMedia(MediaCatalog _newMedia) {
        UUID _id = UUID.randomUUID();
        return insertMedia(_id, _newMedia);
    }

    List<MediaCatalog> selectAllMedia();

    Optional<MediaCatalog> selectMediaById(UUID id);

    int deleteMediaById(UUID id);
    int updateMediaById(UUID id, MediaCatalog mediaCatalog);

    List<MediaCatalog> findByGenre(String text);

    List<MediaCatalog> findByCountry(String text);

    List<MediaCatalog> findByArtistName(String text);

    List<MediaCatalog> findByMediaTitle(String text);

    List<MediaCatalog> findByPublisher(String text);

    List<MediaCatalog> findSearch(String genre,
                                  String country,
                                  String artist,
                                  String media,
                                  String publisher,
                                  String sort,
                                  Integer dir);
}
