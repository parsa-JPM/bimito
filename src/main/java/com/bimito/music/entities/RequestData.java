package com.bimito.music.entities;

import lombok.Data;

import java.util.List;

@Data
public class RequestData {
    private List<User> users;
    private List<Album> albums;
    private List<String> queries;
}
