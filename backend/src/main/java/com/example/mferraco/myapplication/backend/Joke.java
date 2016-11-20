package com.example.mferraco.myapplication.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class Joke {

    private String jokeString;

    public String getJokeString() {
        return jokeString;
    }

    public void setJokeString(String data) {
        jokeString = data;
    }
}