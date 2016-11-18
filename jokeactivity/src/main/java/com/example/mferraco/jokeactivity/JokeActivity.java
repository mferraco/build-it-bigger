package com.example.mferraco.jokeactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Bundle args = getIntent().getExtras();
        String joke = args.getString(JOKE);

        TextView jokeTextView = (TextView) findViewById(R.id.joke_text);
        jokeTextView.setText(joke);
    }
}
