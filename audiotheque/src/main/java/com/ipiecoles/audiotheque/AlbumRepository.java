package com.ipiecoles.audiotheque;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository <Album, Integer> {
}