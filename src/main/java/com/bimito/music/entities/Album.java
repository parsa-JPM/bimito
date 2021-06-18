package com.bimito.music.entities;

import lombok.Data;

@Data
public class Album {
    private String name;
    private String singer;
    private Genre genre;
    private int tracks;
}
