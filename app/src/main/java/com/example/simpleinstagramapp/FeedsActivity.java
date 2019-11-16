package com.example.simpleinstagramapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.simpleinstagramapp.Models.Post;
import com.example.simpleinstagramapp.adapters.PostAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class FeedsActivity extends AppCompatActivity {

    private static final int request_code = 20;

    RecyclerView rView;
    PostAdapter adapter;
    List<Post> posts;
    ImageView imgAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds);

        imgAdd = findViewById(R.id.ivAdd);

        posts = new ArrayList<>();
        adapter = new PostAdapter(posts, this);

        rView = findViewById(R.id.rvPosts);
        rView.setAdapter(adapter);
        rView.setLayoutManager(new LinearLayoutManager(this));

        refreshTimeLine();
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedsActivity.this, PostActivity.class);
                startActivityForResult(intent, request_code);
            }
        });
    }

    private void refreshTimeLine() {
        posts.clear();
        ParseQuery<Post> query = new ParseQuery<Post>(Post.class);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> postObjects, ParseException e) {
                if (e != null) {
                    Log.e("Tag", "Error downloading post objects");
                    return;
                }
                posts.addAll(postObjects);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == request_code && resultCode == RESULT_OK) {
           refreshTimeLine();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
