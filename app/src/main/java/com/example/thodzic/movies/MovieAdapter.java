package com.example.thodzic.movies;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

//24.  We need to create the custom data adapter.
//27.  extend the class with the RecyclerView adapter.
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>{

    //25.  Define two fields which are referenced from the main activity.
    private List<Movie> mMovieData;
    private Context context;

    //26. Create the constructor for the class
    public MovieAdapter(Context context, List<Movie> movieData) {
        this.context = context;
        this.mMovieData = movieData;

    }

    //34.  onBindViewHolder is method where we can manipulate the views.
    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, final int position) {
        holder.mTitleTextView.setText(mMovieData.get(position).getTitle());
        holder.mReleaseDateTextView.setText(mMovieData.get(position).getDate());
        //35.  Use Picasso library to quickly convert image url to image.
        Picasso.with(context).load(mMovieData.get(position).getMoviePoster()).into(holder.mImageView);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("movie_title", mMovieData.get(position).getTitle());
                intent.putExtra("poster", mMovieData.get(position).getMoviePoster());
                intent.putExtra("release_date", mMovieData.get(position).getDate());
                intent.putExtra("vote_average", mMovieData.get(position).getVoteAverage());
                intent.putExtra("plot", mMovieData.get(position).getPlot());
                context.startActivity(intent);
            }
        });
    }

    //31.  The onCreateViewHolder class is used in order to reference the cardview layout inflater to
    //inflate the layout.
    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //32.  Create a new view and use the layout inflater to
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_item, parent, false);
        //33.  We need to return a new instance of the viewholder with the referenced view.
        return new MovieAdapterViewHolder(view);
    }

    //30.  One of the methods we must override for the custom adapter class.  Need to return the size
    //of the list that we provide.
    @Override
    public int getItemCount() {
        if (null == mMovieData)
            return 0;
        return mMovieData.size();
    }

    //28.  Create the MovieAdapterViewHolder class from where we can update the cardview with data.
    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder {

        //29.  Our cardview has two text views and an image view.
        public final TextView mTitleTextView;
        public final TextView mReleaseDateTextView;
        public final ImageView mImageView;
        public final CardView mCardView;

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);

            mTitleTextView = itemView.findViewById(R.id.title_text_view);
            mReleaseDateTextView = itemView.findViewById(R.id.release_date_text_view);
            mImageView = itemView.findViewById(R.id.image_data);
            mCardView = itemView.findViewById(R.id.card_view);
        }
    }

}
