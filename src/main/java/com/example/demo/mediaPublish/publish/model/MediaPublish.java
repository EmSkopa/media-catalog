package com.example.demo.mediaPublish.publish.model;

import com.example.demo.mediaCatalog.model.MediaCatalog;
import com.example.demo.mediaPublish.user.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Contract;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document(collection = "publish")
public class MediaPublish {

    @Id
    private final UUID id;
    private final List<UUID> userIDList;
    private final List<User> userList;
    private final List<UUID> mediaIDList;
    private final List<MediaCatalog> mediaCatalogList;
    private final LocalDate expiryPeriod;

    @Contract(pure = true)
    public MediaPublish(@JsonProperty("id") UUID id,
                        @JsonProperty("userIDList") List<UUID> userIDList,
                        @JsonProperty("userList") List<User> userList,
                        @JsonProperty("mediaIDList") List<UUID> mediaIDList,
                        @JsonProperty("mediaList") List<MediaCatalog> mediaCatalogList,
                        @JsonProperty("expiryPeriod") LocalDate expiryPeriod) {
        this.id = id;
        this.userIDList = userIDList;
        this.userList = userList;
        this.mediaIDList = mediaIDList;
        this.mediaCatalogList = mediaCatalogList;
        this.expiryPeriod = expiryPeriod;
    }

    public UUID getId() {
        return id;
    }

    public List<UUID> getUserIDList() {
        return userIDList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<UUID> getMediaIDList() {
        return mediaIDList;
    }

    public List<MediaCatalog> getMediaCatalogList() {
        return mediaCatalogList;
    }

    public LocalDate getExpiryPeriod() {
        return expiryPeriod;
    }
}
