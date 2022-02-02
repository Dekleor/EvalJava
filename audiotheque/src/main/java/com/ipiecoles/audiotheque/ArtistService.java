package com.ipiecoles.audiotheque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ArtistService {

    @Autowired
    private ArtistRepository repoArtist;

    public Optional<Artist> get(Integer id) {
        return repoArtist.findById(id);
    }

    public Page<Artist> findAllArtists(int page, int size, Sort sort) {
        Pageable artists = PageRequest.of(page, size, sort);
        return repoArtist.findAll(artists);
    }

    public Page<Artist> findArtist(String name, Integer page, Integer size, Sort sort) {
        Pageable artists = PageRequest.of(page, size, sort);
        return repoArtist.findArtistsByNameContaining(name, artists);
    }

    public void create(CreateArtistRequest data) {
        Artist newArtist = new Artist(data.getName());
        repoArtist.save(newArtist);
    }

    public boolean artistExist(String name) {
        return repoArtist.existsByNameIgnoreCase(name);
    }

    public Optional<Artist> findById(Integer artistId) {
        return repoArtist.findById(artistId);
    }

    public void updateArtist(Artist modifiedArtist) {
        repoArtist.save(modifiedArtist);
    }

    public void deleteArtist(Artist deleteArtist) {
        repoArtist.delete(deleteArtist);
    }
}
