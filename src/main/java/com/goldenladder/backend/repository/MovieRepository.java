package com.goldenladder.backend.repository;

import com.goldenladder.backend.model.Actor;
import com.goldenladder.backend.model.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,String> {


    List<Movie> findAllByGenreContains(String genre);

    @Query("SELECT m FROM Movie m ORDER BY m.datePublished")
    List<Movie> findNewestMovies(Pageable pageable);

    @Query("SELECT m FROM Movie m ORDER BY m.avgVote DESC")
    List<Movie> findTopRated(Pageable pageable);


}
