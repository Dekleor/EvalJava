package com.ipiecoles.audiotheque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@CrossOrigin
public class AppController {

    @Autowired
    private ArtistService artistService;
    @Autowired
    private AlbumService albumService;


//Function relatives à Artist
    @GetMapping("/artists/{id}")
    public Artist getArtist(@PathVariable("id") Integer artistId) {
        Optional<Artist> returnedArtist = artistService.get(artistId);
        if (returnedArtist.isPresent()) {
            return returnedArtist.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
    }

    @GetMapping(value = "/artists")
    public Page<Artist> getAllArtists(@RequestParam(value = "name", required = false) Optional<String> name, @RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("sortProperty") String sortProperty, @RequestParam("sortDirection") String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortProperty);

        if(name.isEmpty()) {
            return artistService.findAllArtists(page, size, sort);
        } else {
            return artistService.findArtist(name.get(), page, size, sort);
        }
    }

    @PostMapping(value = "/artists")
    public void addArtist(@RequestBody CreateArtistRequest data) {
        if(artistService.artistExist(data.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity already in database");
        } else {
            artistService.create(data);
        }
    }

    @PutMapping(value = "/artists/{id}")
    public void modifyArtist(@PathVariable("id")Integer artistId, @RequestBody ModifyArtistRequest data) {
        Optional<Artist> artistToModify = artistService.findById(artistId);

        if (artistToModify.isPresent()) {
            Artist modifiedArtist = artistToModify.get();
            modifiedArtist.setName(data.getName());
            artistService.updateArtist(modifiedArtist);
        }
    }

    @DeleteMapping(value = "/artists/{id}")
    public void deleteArtist(@PathVariable("id")Integer artistId) {
        Optional<Artist> artistToDelete = artistService.findById(artistId);

        if (artistToDelete.isPresent()) {
            Artist deleteArtist = artistToDelete.get();
            artistService.deleteArtist(deleteArtist);
        }
    }

    //Fonctions relatives à Album
    @PostMapping(value = "/albums")
    public Optional<Album> addAlbum(@RequestBody CreateAlbumRequest data) {
        Integer artistId = data.getArtist().getId();
        Optional<Artist> artistToCreateAlbum = artistService.findById(artistId);
        if (artistToCreateAlbum.isPresent()) {
            return Optional.of( albumService.addAlbum(data.getTitle(), artistId));
        }
        return Optional.empty();
    }

    @DeleteMapping(value = "/albums/{id}")
    public Optional<Optional<Album>> deleteAlbum(@PathVariable("id")Integer id) {
        Optional<Album> albumToDelete = albumService.findById(id);

        if (albumToDelete.isPresent()) {
            albumService.deleteAlbum(albumToDelete.get());
            return Optional.of(albumToDelete);
        }
        return Optional.empty();
    }
}
