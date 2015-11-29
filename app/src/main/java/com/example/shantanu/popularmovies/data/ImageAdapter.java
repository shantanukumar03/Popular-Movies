package com.example.shantanu.popularmovies.data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import com.example.shantanu.popularmovies.R;
import com.example.shantanu.popularmovies.data.gson.Movie;
import com.example.shantanu.popularmovies.util.Utility;

/**
 * An adaptor for the movie view to render each item as a tile consisting
 * of an image and a small overlay
 */
public class ImageAdapter extends ArrayAdapter<Movie> {

    //Store the pixel dimensions we want for each image
    private int mImageWidth;
    private int mImageHeight;

    public ImageAdapter(Context context, int resource) {
        super(context, resource);
        mImageWidth = context.getResources().getDimensionPixelSize(R.dimen.movie_tile_width);
        mImageHeight = context.getResources().getDimensionPixelSize(R.dimen.movie_tile_height);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = getContext();
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        //Recycle the view if possible
        FrameLayout view = (convertView == null) ? (FrameLayout)inflater.inflate(R.layout.movie_tile, null) :
                (FrameLayout) convertView;

        ImageView image = (ImageView) view.findViewById(R.id.movie_image);
        TextView rating = (TextView) view.findViewById(R.id.movie_vote_average);
        TextView year = (TextView) view.findViewById(R.id.movie_year);

        Movie thisMovie = getItem(position);
        //Retrieve and load the poster image for this movie
        Picasso.with(context)
                .load(Utility.buildImageUri(context, thisMovie.posterPath, Utility.ImageType.IMAGE))
                .placeholder(R.drawable.movie_tile_placeholder)
                .error(R.drawable.no_image_placeholder)
                .resize(mImageWidth, mImageHeight)
                .centerCrop()
                .into(image);
        rating.setText(context.getString(R.string.format_rating_overlay, thisMovie.voteAverage));
        year.setText(Utility.getShortDateString(context, thisMovie.releaseDate));
        return view;
    }
}
