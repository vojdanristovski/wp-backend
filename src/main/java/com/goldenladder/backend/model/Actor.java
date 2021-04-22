package com.goldenladder.backend.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="actors")
public class Actor {
    @Id
    @Column(name = "imdb_name_id")
    private String actorId;

    @Column(name="name")
    private String name;

    @Column(name = "bio")
    private String bio;

    @Column(name="date_of_birth")
    private String birthDate;

    @Column(name = "date_of_death")
    private String deathDate;

    public Actor() {
    }

    public Actor(String name, String bio, String birthDate, String deathDate) {
        this.name = name;
        this.bio = bio;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }
}
