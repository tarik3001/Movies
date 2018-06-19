package com.example.thodzic.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>{

    private List<Movie> mMovieData;
    private Context context;

    public MovieAdapter(Context context, List<Movie> movieData) {
        this.context = context;
        this.mMovieData = movieData;

    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, int position) {
        holder.mTitleTextView.setText(mMovieData.get(position).getTitle());
        holder.mReleaseDateTextView.setText(mMovieData.get(position).getDate());
        Picasso.with(context).load(mMovieData.get(position).getMoviePoster()).into(holder.mImageView);

    }

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_item, parent, false);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (null == mMovieData)
            return 0;
        return mMovieData.size();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTitleTextView;
        public final TextView mReleaseDateTextView;
        public final ImageView mImageView;

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);

            mTitleTextView = itemView.findViewById(R.id.title_text_view);
            mReleaseDateTextView = itemView.findViewById(R.id.release_date_text_view);
            mImageView = itemView.findViewById(R.id.image_data);
        }
    }

}
