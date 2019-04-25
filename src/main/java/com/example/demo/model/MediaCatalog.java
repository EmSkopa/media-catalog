package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Contract;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.UUID;

@Document(collection = "mediaCatalog")
public class MediaCatalog {

    @Id
    private final UUID id;
    private final Timestamp timestamp;
    private final String createdUserID;
    private final String updatedUserID;

    private final String mediaName;
    private final String artistName;
    private final String publisher;
    private final String originatingCountry;
    private final String genre;
    private final DateFormat publishingDate;


    @Contract(pure = true)
    public MediaCatalog(@JsonProperty("id") UUID id,
                        @JsonProperty("timestamp") Timestamp timestamp,
                        @JsonProperty("createdUserID") String createdUserID,
                        @JsonProperty("updatedUserID") String updatedUserID,
                        @JsonProperty("mediaName") String mediaName,
                        @JsonProperty("artistName") String artistName,
                        @JsonProperty("publisher") String publisher,
                        @JsonProperty("originatingCountry") String originatingCountry,
                        @JsonProperty("genre") String genre,
                        @JsonProperty("publishingDate") DateFormat publishingDate) {
        this.id = id;
        this.timestamp = timestamp;
        this.createdUserID = createdUserID;
        this.updatedUserID = updatedUserID;
        this.mediaName = mediaName;
        this.artistName = artistName;
        this.publisher = publisher;
        this.originatingCountry = originatingCountry;
        this.genre = genre;
        this.publishingDate = publishingDate;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getCreatedUserID() {
        return createdUserID;
    }

    public String getUpdatedUserID() {
        return updatedUserID;
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

    public DateFormat getPublishingDate() {
        return publishingDate;
    }

    public UUID getId() {
        return id;
    }

    public String getMediaName() {
        return mediaName;
    }

    public String getGenre() {
        return genre;
    }
}
