package com.bimito.music;

import com.bimito.music.entities.RequestData;
import com.bimito.music.service.AnalyzeRequest;
import com.bimito.music.service.Util;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        RequestData requestData = Util.convertYAMLToEntity();
        System.out.println(new AnalyzeRequest(requestData).getAnswers());
    }
}
