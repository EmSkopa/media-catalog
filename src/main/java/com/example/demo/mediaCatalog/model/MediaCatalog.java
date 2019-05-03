package com.example.demo.mediaCatalog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Contract;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.mongodb.core.mapping.Document;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "mediaCatalog")
public class MediaCatalog {

    @Id
    private final UUID id;
    @CreatedDate
    private final LocalDateTime timestamp;
    @CreatedBy
    private final String createdUserID;
    @LastModifiedBy
    private final String updatedUserID;

    private final String mediaName;
    private final String artistName;
    private final String publisher;
    private final String originatingCountry;
    private final String genre;
    private final LocalDate publishingDate;


    @Contract(pure = true)
    public MediaCatalog(@JsonProperty("id") UUID id,
                        @JsonProperty("timestamp") LocalDateTime timestamp,
                        @JsonProperty("createdUserID") String createdUserID,
                        @JsonProperty("updatedUserID") String updatedUserID,
                        @JsonProperty("mediaName") String mediaName,
                        @JsonProperty("artistName") String artistName,
                        @JsonProperty("publisher") String publisher,
                        @JsonProperty("originatingCountry") String originatingCountry,
                        @JsonProperty("genre") String genre,
                        @JsonProperty("publishingDate") LocalDate publishingDate) {
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

    public LocalDateTime getTimestamp() {
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

    public LocalDate getPublishingDate() {
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
