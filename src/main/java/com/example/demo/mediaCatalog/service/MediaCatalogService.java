package com.example.demo.mediaCatalog.service;

import com.example.demo.mediaCatalog.dao.MediaCatalogDao;
import com.example.demo.mediaCatalog.dao.MediaCatalogRepository;
import com.example.demo.mediaCatalog.model.MediaCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MediaCatalogService {

    private final MediaCatalogDao mediaCatalogDao;
    private final MediaCatalogRepository mediaCatalogRepository;

    @Autowired
    public MediaCatalogService(@Qualifier("embeddedDb") MediaCatalogDao mediaCatalogDao, MediaCatalogRepository mediaCatalogRepository) {
        this.mediaCatalogDao = mediaCatalogDao;
        this.mediaCatalogRepository = mediaCatalogRepository;
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

    public List<MediaCatalog> getMediaByGenre(String text, Sort sort) {
        return mediaCatalogRepository.findMediaByGenre(text, sort);
    }

    public List<MediaCatalog> getMediaByCountry(String text, Sort sort){
        return mediaCatalogRepository.findMediaByOriginatingCountry(text, sort);
    }

    public List<MediaCatalog> getMediaByArtistName(String text, Sort sort){
        return mediaCatalogRepository.findMediaByArtistName(text, sort);
    }

    public List<MediaCatalog> getMediaByMediaTitle(String text, Sort sort){
        return mediaCatalogRepository.findMediaByMediaName(text, sort);
    }

    public List<MediaCatalog> getMediaByPublisher(String text, Sort sort){
        return mediaCatalogRepository.findMediaByPublisher(text, sort);
    }

    public List<MediaCatalog> getSearch(String genre,
                                        String country,
                                        String artist,
                                        String media,
                                        String publisher,
                                        String sort,
                                        Integer dir){
        return mediaCatalogDao.findSearch(genre,country,artist,media,publisher,sort,dir);
    }

}
