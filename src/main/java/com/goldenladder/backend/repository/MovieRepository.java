package com.goldenladder.backend.repository;

import com.goldenladder.backend.model.Actor;
import com.goldenladder.backend.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,String> {


    @Query("SELECT m FROM Movie m WHERE m.genre = :genre ORDER BY m.voteCount DESC")
    List<Movie> findAllByGenreSorted(String genre,Pageable pageable);

    @Query("SELECT m FROM Movie m ORDER BY m.datePublished DESC")
    List<Movie> findNewestMovies(Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE m.country IN ('USA','UK','USA,UK') AND m.voteCount>10 ORDER BY m.avgVote DESC")
    List<Movie> findTopRated(Pageable pageable);

    @Query("SELECT m FROM Movie m ORDER BY m.voteCount DESC")
    List<Movie> findTopPopularity(Pageable pageable);

}
