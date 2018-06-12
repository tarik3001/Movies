package com.example.thodzic.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>{

    private String [] mMovieData;
    private Context mContext;

    public MovieAdapter() {

    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mImageView;

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_data);
        }
    }

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_item, parent, false);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, int position) {
        String movieImage = mMovieData[position];
        Picasso.with(mContext).load(movieImage).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        if (null == mMovieData)
            return 0;
        return mMovieData.length;
    }

    public void setMovieData(String [] movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }

}
