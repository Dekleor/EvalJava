package com.ipiecoles.audiotheque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AlbumService {

    @Autowired
    private AlbumRepository repoAlbum;

    public Album addAlbum(String title, Integer artistId) {
        Album newAlbum = new Album(title, artistId);
        return repoAlbum.save(newAlbum);
    }

    public void deleteAlbum(Album deleteAlbum) {
        repoAlbum.delete(deleteAlbum);
    }

    public Optional<Album> findById(Integer id) {
        return repoAlbum.findById(id);
    }
}
