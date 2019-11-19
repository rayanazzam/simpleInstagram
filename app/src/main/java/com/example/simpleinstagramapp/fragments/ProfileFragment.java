package com.example.simpleinstagramapp.fragments;

import android.util.Log;

import com.example.simpleinstagramapp.Models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends FeedsFragment {
    @Override
    protected void refreshTimeLine() {
        posts.clear();
        ParseQuery<Post> query = new ParseQuery<Post>(Post.class);
        query.setLimit(20);
        query.addDescendingOrder(Post.CREATED_AT);
        query.whereEqualTo("user", ParseUser.getCurrentUser());
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
