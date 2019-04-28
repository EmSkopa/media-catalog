package com.example.demo.mediaPublish.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Contract;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "user")
public class User {

    @Id
    private final UUID _id;
    private final String userName;

    @Contract
    public User(@JsonProperty("_id") UUID _id,
                @JsonProperty("userName") String userName) {
        this._id = _id;
        this.userName = userName;
    }

    public UUID get_id() {
        return _id;
    }

    public String getUserName() {
        return userName;
    }
}
