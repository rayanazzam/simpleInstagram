package com.example.simpleinstagramapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.simpleinstagramapp.Models.Post;
import com.example.simpleinstagramapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    List<Post> posts;
    Context context;
    private static final String TAG = "Post_Adapter";

    public PostAdapter (List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.post_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind (posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView postImage;
        TextView postCaption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.postImage = itemView.findViewById(R.id.ivPostImage);
            this.postCaption = itemView.findViewById(R.id.tvCaption);
        }

        public void bind (Post post) {
            this.postCaption.setText(post.getDescription());
            if (post.getImage() != null) {
                Glide.with(context).load(post.getImage().getUrl()).into(postImage);
                //Picasso.get().load(post.getImage().getUrl()).fit().into(postImage);
                Log.e(TAG, "working" + post.getImage().getUrl());
                /*post.getImage().getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] data, ParseException e) {
                        if (e == null) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                            postImage.setImageBitmap(bmp);
                        } else {
                            Log.e(TAG,e.getMessage());
                            Log.e(TAG, "Problem setting image");
                        }
                    }
                });*/
            }

        }
    }
}
