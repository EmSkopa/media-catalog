package com.example.demo.service;

import com.example.demo.dao.MediaCatalogDao;
import com.example.demo.model.MediaCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MediaCatalogService {

    private final MediaCatalogDao mediaCatalogDao;

    @Autowired
    public MediaCatalogService(@Qualifier("embeddedDb") MediaCatalogDao mediaCatalogDao) {
        this.mediaCatalogDao = mediaCatalogDao;
    }

    public int addMedia(MediaCatalog mediaCatalog){
        return mediaCatalogDao.insertMedia(mediaCatalog);
    }

    public List<MediaCatalog> getAllMedia() {
        return mediaCatalogDao.selectAllMedia();
    }

    public Optional<MediaCatalog> getMediaById(UUID id) {
        return mediaCatalogDao.selectMediaById(id);
    }

    public int deleteMediaById(UUID id) {
        return mediaCatalogDao.deleteMediaById(id);
    }

    public int updateMediaById (UUID id, MediaCatalog mediaCatalog) {
        return mediaCatalogDao.updateMediaById(id, mediaCatalog);
    }

    public List<MediaCatalog> getMediaByGenre(String text) {
        return mediaCatalogDao.findByGenre(text);
    }

    public List<MediaCatalog> getMediaByCountry(String text){
        return mediaCatalogDao.findByCountry(text);
    }

    public List<MediaCatalog> getMediaByArtistName(String text){
        return mediaCatalogDao.findByArtistName(text);
    }

    public List<MediaCatalog> getMediaByMediaTitle(String text){
        return mediaCatalogDao.findByMediaTitle(text);
    }

    public List<MediaCatalog> getMediaByPublisher(String text){
        return mediaCatalogDao.findByPublisher(text);
    }

    public List<MediaCatalog> getSearch(String genre,
                                        String country,
                                        String artist,
                                        String media,
                                        String publisher){
        return mediaCatalogDao.findSearch(genre,country,artist,media,publisher);
    }

}
