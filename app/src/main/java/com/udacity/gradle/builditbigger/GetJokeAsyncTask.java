package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.example.mferraco.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Calls the backend server to retrieve a joke
 */

class GetJokeAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context mContext;

    private GetJokeResponseListener mResponseListener;

    public GetJokeAsyncTask(GetJokeResponseListener responseListener) {
        mResponseListener = responseListener;
    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        mContext = params[0];

        try {
            return myApiService.joke().execute().getJokeString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mResponseListener.retrievedJoke(result);
    }
}
