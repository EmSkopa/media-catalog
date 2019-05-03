package com.example.demo.mediaCatalog.dao;

import com.example.demo.mediaCatalog.model.MediaCatalog;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository("embeddedDb")
public class MediaDataAccessService implements MediaCatalogDao  {

    private MediaCatalogRepository mediaCatalogRepository;

    private static List<MediaCatalog> DB = new ArrayList<>();
    public MediaDataAccessService(MediaCatalogRepository mediaCatalogRepository) {
        this.mediaCatalogRepository = mediaCatalogRepository;
    }

    @Override
    public int insertMedia(UUID id, LocalDateTime timestamp, String cUserID, String uUserID, MediaCatalog newMedia){
        if(DB.isEmpty())
            DB = this.mediaCatalogRepository.findAll();

        DB.add(new MediaCatalog(id, timestamp, cUserID, uUserID,
                                    newMedia.getMediaName(),
                                    newMedia.getArtistName(),
                                    newMedia.getPublisher(),
                                    newMedia.getOriginatingCountry(),
                                    newMedia.getGenre(),
                                    newMedia.getPublishingDate()));

        this.mediaCatalogRepository.saveAll(DB);

        return 1;
    }

    @Override
    public List<MediaCatalog> selectAllMedia() {
        if(DB.isEmpty())
            DB = this.mediaCatalogRepository.findAll();
        return DB;
    }

    @Override
    public Optional<MediaCatalog> selectMediaById(UUID id) {
        if(DB.isEmpty())
            DB = this.mediaCatalogRepository.findAll();

        return DB.stream()
                .filter(mediaCatalog -> mediaCatalog.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteMediaById(UUID id) {
        if(DB.isEmpty())
            DB = this.mediaCatalogRepository.findAll();

        Optional<MediaCatalog> mediaForDelete = selectMediaById(id);
        if(mediaForDelete.isEmpty()){
            return 0;
        }
        DB.remove(mediaForDelete.get());
        this.mediaCatalogRepository.deleteById(id);
        return 1;
    }

    @Override
    public int updateMediaById(UUID id, MediaCatalog updateMediaCatalog) {
        if(DB.isEmpty())
            DB = this.mediaCatalogRepository.findAll();

        return selectMediaById(id)
                .map(_mediaCatalog -> {
                    int indexToUpdate = DB.indexOf(_mediaCatalog);
                    if(indexToUpdate >= 0){
                        DB.set(indexToUpdate, new MediaCatalog(id,
                                updateMediaCatalog.getTimestamp(),
                                updateMediaCatalog.getCreatedUserID(),
                                updateMediaCatalog.getUpdatedUserID(),
                                updateMediaCatalog.getMediaName(),
                                updateMediaCatalog.getArtistName(),
                                updateMediaCatalog.getPublisher(),
                                updateMediaCatalog.getOriginatingCountry(),
                                updateMediaCatalog.getGenre(),
                                updateMediaCatalog.getPublishingDate()));
                    }

                    this.mediaCatalogRepository.save(new MediaCatalog(id,
                            updateMediaCatalog.getTimestamp(),
                            updateMediaCatalog.getCreatedUserID(),
                            updateMediaCatalog.getUpdatedUserID(),
                            updateMediaCatalog.getMediaName(),
                            updateMediaCatalog.getArtistName(),
                            updateMediaCatalog.getPublisher(),
                            updateMediaCatalog.getOriginatingCountry(),
                            updateMediaCatalog.getGenre(),
                            updateMediaCatalog.getPublishingDate()));
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public List<MediaCatalog> findByGenre(String text) {
        if(DB.isEmpty())
            DB = this.mediaCatalogRepository.findAll();

        List<MediaCatalog> findmatches = new ArrayList<>();

        findmatches = DB.stream()
            .filter(_mediaCatalog -> _mediaCatalog.getGenre().equals(text))
            .collect(Collectors.toList());

        return findmatches;
    }

    @Override
    public List<MediaCatalog> findByCountry(String text) {
        if(DB.isEmpty())
            DB = this.mediaCatalogRepository.findAll();

        List<MediaCatalog> findmatches = new ArrayList<>();

        findmatches = DB.stream()
                .filter(_mediaCatalog -> _mediaCatalog.getOriginatingCountry().equals(text))
                .collect(Collectors.toList());

        return findmatches;
    }

    @Override
    public List<MediaCatalog> findByArtistName(String text) {
        if(DB.isEmpty())
            DB = this.mediaCatalogRepository.findAll();

        List<MediaCatalog> findmatches = new ArrayList<>();

        findmatches = DB.stream()
                .filter(_mediaCatalog -> _mediaCatalog.getArtistName().equals(text))
                .collect(Collectors.toList());

        return findmatches;
    }

    @Override
    public List<MediaCatalog> findByMediaTitle(String text) {
        if(DB.isEmpty())
            DB = this.mediaCatalogRepository.findAll();

        List<MediaCatalog> findmatches = new ArrayList<>();

        findmatches = DB.stream()
                .filter(_mediaCatalog -> _mediaCatalog.getMediaName().equals(text))
                .collect(Collectors.toList());

        return findmatches;
    }

    @Override
    public List<MediaCatalog> findByPublisher(String text) {
        if(DB.isEmpty())
            DB = this.mediaCatalogRepository.findAll();

        List<MediaCatalog> findmatches = new ArrayList<>();

        findmatches = DB.stream()
                .filter(_mediaCatalog -> _mediaCatalog.getPublisher().equals(text))
                .collect(Collectors.toList());

        return findmatches;
    }

    @Override
    public List<MediaCatalog> findSearch(String genre, String country, String artist, String media,
                                         String publisher, String sort, Integer dir) {
        if(DB.isEmpty())
            DB = this.mediaCatalogRepository.findAll();

        List<MediaCatalog> findmatches = DB;

        if(genre != null && !genre.isEmpty())
            findmatches = findmatches.stream()
                .filter(_mediaCatalog -> _mediaCatalog.getGenre().contains(genre))
                .collect(Collectors.toList());
        if(country != null && !country.isEmpty())
            findmatches = findmatches.stream()
                    .filter(_mediaCatalog -> _mediaCatalog.getOriginatingCountry().contains(country))
                    .collect(Collectors.toList());

        if(artist != null && !artist.isEmpty())
            findmatches = findmatches.stream()
                    .filter(_mediaCatalog -> _mediaCatalog.getArtistName().contains(artist))
                    .collect(Collectors.toList());

        if(media != null && !media.isEmpty())
            findmatches = findmatches.stream()
                    .filter(_mediaCatalog -> _mediaCatalog.getMediaName().contains(media))
                    .collect(Collectors.toList());

        if(publisher != null && !publisher.isEmpty())
            findmatches = findmatches.stream()
                    .filter(_mediaCatalog -> _mediaCatalog.getPublisher().contains(publisher))
                    .collect(Collectors.toList());

        if(sort == "id"){
            if(dir == -1)
                findmatches.sort(Comparator.comparing(MediaCatalog::getId).reversed());
            else
                findmatches.sort(Comparator.comparing(MediaCatalog::getId));
        }
        else if(sort == "media"){
            if(dir == -1)
                findmatches.sort(Comparator.comparing(MediaCatalog::getMediaName).reversed());
            else
                findmatches.sort(Comparator.comparing(MediaCatalog::getMediaName));
        }
        else if(sort == "artist"){
            if(dir == -1)
                findmatches.sort(Comparator.comparing(MediaCatalog::getArtistName).reversed());
            else
                findmatches.sort(Comparator.comparing(MediaCatalog::getArtistName));
        }
        else if(sort == "country"){
            if(dir == -1)
                findmatches.sort(Comparator.comparing(MediaCatalog::getOriginatingCountry).reversed());
            else
                findmatches.sort(Comparator.comparing(MediaCatalog::getOriginatingCountry));
        }
        else if(sort == "publisher"){
            if(dir == -1)
                findmatches.sort(Comparator.comparing(MediaCatalog::getPublisher).reversed());
            else
                findmatches.sort(Comparator.comparing(MediaCatalog::getPublisher));
        }

        return findmatches;
    }
}
