package com.example.demo.mediaCatalog.model.es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Document(indexName = "mediaCatalog",type = "mediaCatalog")
public class EsMediaCatalog implements Serializable {

    private static final long serialVersionUID = -3442422877407279457L;

    @Id
    private final UUID id;

    @Field
    private final UUID mediaId;
    private final String mediaName;
    private final String artistName;
    private final String publisher;
    private final String originatingCountry;
    private final String genre;
    private final LocalDate publishingDate;


    public EsMediaCatalog(UUID id, UUID mediaId, String mediaName, String artistName, String publisher, String originatingCountry, String genre, LocalDate publishingDate) {
        this.id = id;
        this.mediaId = mediaId;
        this.mediaName = mediaName;
        this.artistName = artistName;
        this.publisher = publisher;
        this.originatingCountry = originatingCountry;
        this.genre = genre;
        this.publishingDate = publishingDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UUID getId() {
        return id;
    }

    public UUID getMediaId() {
        return mediaId;
    }

    public String getMediaName() {
        return mediaName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDate getPublishingDate() {
        return publishingDate;
    }
}
