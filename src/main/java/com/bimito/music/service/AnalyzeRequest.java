package com.bimito.music.service;

import com.bimito.music.entities.Album;
import com.bimito.music.entities.RequestData;
import com.bimito.music.entities.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AnalyzeRequest {
    private RequestData requestData;

    public AnalyzeRequest(RequestData requestData) {
        this.requestData = requestData;
    }

    public List<Integer> getAnswers() {
        List<Integer> answers = new LinkedList<>();

        for (String query : requestData.getQueries()) {
            String[] splitQuery = query.split(" ");
            /**
             * bypass queries that has not enough data
             */
            if (splitQuery.length < 3) {
                answers.add(0);
                continue;
            }

            if (splitQuery[0].equals("1"))
                answers.add(userSinger(splitQuery[1], splitQuery[2]));

            if (splitQuery[0].equals("2"))
                answers.add(userGenre(splitQuery[1], splitQuery[2]));

            if (splitQuery[0].equals("3"))
                answers.add(ageSinger(splitQuery[1], splitQuery[2]));

            if (splitQuery[0].equals("4"))
                answers.add(ageGenre(splitQuery[1], splitQuery[2]));

            if (splitQuery[0].equals("5"))
                answers.add(citySinger(splitQuery[1], splitQuery[2]));

            if (splitQuery[0].equals("6"))
                answers.add(cityGenre(splitQuery[1], splitQuery[2]));
        }

        return answers;
    }

    private int userSinger(String username, String singer) {
        Optional<User> optionalUser = findUser(user -> user.getName().equals(username));

        if (optionalUser.isEmpty())
            return 0;

        User user = optionalUser.get();

        return totalTracks(album -> user.getAlbums().contains(album.getName()) && album.getSinger().equals(singer));
    }

    private int userGenre(String username, String genre) {
        Optional<User> optionalUser = findUser(user -> user.getName().equals(username));

        if (optionalUser.isEmpty())
            return 0;

        User user = optionalUser.get();

        return totalTracks(album -> user.getAlbums().contains(album.getName()) && album.getGenre().name().equalsIgnoreCase(genre));
    }

    private int ageSinger(String ageString, String singer) {
        int age = Integer.parseInt(ageString);
        List<User> users = findUsers(user -> user.getAge() == age);

        int tracks = 0;
        for (User user : users) {
            tracks += totalTracks(album -> user.getAlbums().contains(album.getName()) && album.getSinger().equals(singer));
        }

        return tracks;
    }

    private int ageGenre(String ageString, String genre) {
        int age = Integer.parseInt(ageString);
        List<User> users = findUsers(user -> user.getAge() == age);

        int tracks = 0;
        for (User user : users) {
            tracks += totalTracks(album -> user.getAlbums().contains(album.getName()) && album.getGenre().name().equalsIgnoreCase(genre));
        }

        return tracks;
    }

    private int citySinger(String city, String singer) {
        List<User> users = findUsers(user -> user.getCity().equals(city));

        int tracks = 0;
        for (User user : users) {
            tracks += totalTracks(album -> user.getAlbums().contains(album.getName()) && album.getSinger().equals(singer));
        }

        return tracks;
    }

    private int cityGenre(String city, String genre) {
        List<User> users = findUsers(user -> user.getCity().equals(city));

        int tracks = 0;
        for (User user : users) {
            tracks += totalTracks(album -> user.getAlbums().contains(album.getName()) && album.getGenre().name().equalsIgnoreCase(genre));
        }

        return tracks;
    }

    private Optional<User> findUser(Predicate<User> userPredicate) {
        return requestData.getUsers()
                .stream()
                .filter(userPredicate)
                .findFirst();
    }

    private List<User> findUsers(Predicate<User> userPredicate) {
        return requestData.getUsers()
                .stream()
                .filter(userPredicate)
                .collect(Collectors.toUnmodifiableList());
    }

    private int totalTracks(Predicate<Album> albumPredicate) {
        final int[] tracks = {0};
        requestData.getAlbums().stream()
                .filter(albumPredicate)
                .forEach(album -> tracks[0] += album.getTracks());

        return tracks[0];
    }


}
