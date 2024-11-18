package com.example.demo.mediaPublish.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Contract;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "user")
public class User {

    @Id
    private final UUID id;
    private final String userName;

    @Contract
    public User(@JsonProperty("id") UUID id,
                @JsonProperty("userName") String userName) {
        this.id = id;
        this.userName = userName;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}
