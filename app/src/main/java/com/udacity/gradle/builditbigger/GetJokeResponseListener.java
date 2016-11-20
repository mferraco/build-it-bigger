package com.udacity.gradle.builditbigger;

/**
 * An interface for handling the joke response from the async task.
 */

public interface GetJokeResponseListener {

    void retrievedJoke(String joke);
}
