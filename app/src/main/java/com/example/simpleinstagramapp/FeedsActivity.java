package com.example.simpleinstagramapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.simpleinstagramapp.Models.Post;
import com.example.simpleinstagramapp.adapters.PostAdapter;
import com.example.simpleinstagramapp.fragments.ComposeFragment;
import com.example.simpleinstagramapp.fragments.FeedsFragment;
import com.example.simpleinstagramapp.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;

public class FeedsActivity extends AppCompatActivity {


    RecyclerView rView;
    PostAdapter adapter;
    List<Post> posts;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;

                Fragment fragmentHome = new FeedsFragment();
                Fragment fragmentCompose = new ComposeFragment();
                Fragment fragmentProfile = new ProfileFragment();
                switch (menuItem.getItemId()) {
                    case R.id.actionHome:
                        fragment = fragmentHome;
                        break;
                    case R.id.actionAdd:
                        fragment = fragmentCompose;
                        break;
                    case R.id.actionPeople:
                        fragment = fragmentProfile;
                        break;
                    default:
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.actionHome);
    }


}
