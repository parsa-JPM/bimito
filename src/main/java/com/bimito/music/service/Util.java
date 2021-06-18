package com.bimito.music.service;

import com.bimito.music.entities.RequestData;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class Util {

    public static RequestData convertYAMLToEntity() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);

        return objectMapper.readValue(new File("src/main/resources/test.yaml"), RequestData.class);
    }
}
