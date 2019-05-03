package com.example.demo.mediaCatalog.dao;

import com.example.demo.mediaCatalog.model.MediaCatalog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MediaCatalogDao{

    int insertMedia(UUID id, LocalDateTime timestamp, String cUserID, String uUserID, MediaCatalog newMedia);

    default int insertMedia(MediaCatalog _newMedia) {
        UUID _id = UUID.randomUUID();
        LocalDateTime _timestamp = LocalDateTime.now();
        String _cUserID = _id.toString();
        String _uUserID = _id.toString();
        return insertMedia(_id, _timestamp, _cUserID, _uUserID, _newMedia);
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
