package com.yusuf.gallerymanagement.artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query("SELECT a FROM Artist a LEFT JOIN FETCH a.paintingList")
    List<Artist> findAllWithPaintings();
}