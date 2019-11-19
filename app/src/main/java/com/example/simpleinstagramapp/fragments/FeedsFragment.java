package com.example.simpleinstagramapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simpleinstagramapp.Models.Post;
import com.example.simpleinstagramapp.R;
import com.example.simpleinstagramapp.adapters.PostAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


public class FeedsFragment extends Fragment {

    private static final int request_code = 20;

    RecyclerView rView;
    PostAdapter adapter;
    List<Post> posts;
    SwipeRefreshLayout swipeRefreshLayout;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.feeds_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        posts = new ArrayList<>();
        adapter = new PostAdapter(posts, getContext());

        swipeRefreshLayout = view.findViewById(R.id.swipeContainer);

        rView = view.findViewById(R.id.rvPosts);
        rView.setAdapter(adapter);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshTimeLine();
            }
        });

        refreshTimeLine();
    }

    protected void refreshTimeLine() {
        posts.clear();
        ParseQuery<Post> query = new ParseQuery<Post>(Post.class);
        query.setLimit(20);
        query.addDescendingOrder(Post.CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> postObjects, ParseException e) {
                if (e != null) {
                    Log.e("FeedFragment", "Error downloading post objects");
                    return;
                }
                Log.i ("FeedFragment", "successfully downloaded objects");
                posts.addAll(postObjects);
                adapter.notifyDataSetChanged();
            }
        });

        swipeRefreshLayout.setRefreshing(false);
    }
}
