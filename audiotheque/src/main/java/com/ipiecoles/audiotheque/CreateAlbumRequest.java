package com.ipiecoles.audiotheque;

public class CreateAlbumRequest {
    String title;
    CreateAlbumRequestArtist artist;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CreateAlbumRequestArtist getArtist() {
        return artist;
    }

    public void setArtist(CreateAlbumRequestArtist artist) {
        this.artist = artist;
    }

}
