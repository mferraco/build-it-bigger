package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

/**
 * Tests the AsyncTask functionality
 */
@RunWith(AndroidJUnit4.class)
public class GetJokeTest {

    @Test
    public void getJokeTest() throws Exception {
        assertEquals("MIKE", "MIKE");
        TestJokeClass testJokeClass = new TestJokeClass();
        GetJokeAsyncTask task = new GetJokeAsyncTask(testJokeClass);
        task.execute(InstrumentationRegistry.getTargetContext());

        // wait for response from AsyncTask
        while (!testJokeClass.response) {

        }

        // test that AsyncTask response is not null
        assertNotNull("Retrieved a null response.", testJokeClass.mJoke);
        assertFalse("Retrieved an empty string response.", testJokeClass.mJoke.equals(""));
    }

    public class TestJokeClass implements GetJokeResponseListener {

        String mJoke;
        boolean response = false;

        @Override
        public void retrievedJoke(String joke) {
            response = true;
            mJoke = joke;
        }
    }

}
