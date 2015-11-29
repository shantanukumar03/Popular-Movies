package com.example.shantanu.popularmovies.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shantanu.popularmovies.R;
import com.example.shantanu.popularmovies.fragment.MovieDetailFragment;

public class MovieDetailActivity extends AppCompatActivity {

    private final String FRAGMENT_TAG = "FTAG_MOVIE_DETAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (savedInstanceState == null) {
            //Add the movie detail fragment to the view
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_movie_detail, new MovieDetailFragment(), FRAGMENT_TAG)
                    .commit();
        }
    }
}
