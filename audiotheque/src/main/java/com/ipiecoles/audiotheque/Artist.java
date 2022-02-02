package com.ipiecoles.audiotheque;

import javax.persistence.*;
import java.util.List;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "artistId")
    private List<Album> albums;

    public Artist() {

    }

    public  Artist(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Artist(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
