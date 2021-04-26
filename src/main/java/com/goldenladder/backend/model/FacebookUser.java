package com.goldenladder.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`facebookUser`")
@Entity
@Data
@NoArgsConstructor
public class FacebookUser {

    // User table - username
    @Id
    String id;

    String username;

    public FacebookUser(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
