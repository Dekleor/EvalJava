package com.ipiecoles.audiotheque;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository <Artist, Integer> {
    Page<Artist> findArtistsByNameContaining(String name, Pageable artists);

    boolean existsByNameIgnoreCase(String name);
}
