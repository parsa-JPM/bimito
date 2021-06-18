package com.bimito.music.entities;

import java.util.List;

import lombok.Data;

@Data
public class User {
    private String name;
    private int age;
    private String city;
    private List<String> albums;
}
