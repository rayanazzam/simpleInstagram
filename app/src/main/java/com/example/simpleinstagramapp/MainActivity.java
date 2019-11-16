package com.example.simpleinstagramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;

    Button login;
    Button signup;

    public static final String TAG = "Main_Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.username = findViewById(R.id.etUsername);
        this.password = findViewById(R.id.etPassword);
        this.login = findViewById(R.id.btnLogIn);
        this.signup = findViewById(R.id.btnSignUp);

        this.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (invalidInput (username.getText().toString()) ||
                        invalidInput(password.getText().toString())){
                    //ToDo handle these case more rigorously and gracefully
                    Log.e (TAG, "invalid input");
                } else {
                    loginUser(username.getText().toString(), password.getText().toString());
                }
            }
        });

        this.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (invalidInput (username.getText().toString()) ||
                        invalidInput(password.getText().toString())){
                    //ToDo handle these case more rigorously and gracefully
                    Log.e (TAG, "invalid input");
                } else {
                    createUser(username.getText().toString(), password.getText().toString());
                }

            }
        });
    }

    private void createUser(String username, String password) {
        ParseUser user = new ParseUser ();
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    //Todo handle exception
                    Log.e (TAG, "Sign up failed");
                    Log.e(TAG, e.getMessage());
                    return;
                }
                launchActivity();
            }
        });
    }

    private void loginUser (String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    launchActivity();
                } else {
                    //Todo handle even more rigorously and gracefully
                }
            }
        });
    }

    private void launchActivity() {
        Toast.makeText(this, "launching activity", Toast.LENGTH_LONG).show();
        Intent intent = new Intent (this, FeedsActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean invalidInput (String input) {
        return input.isEmpty() || input.length() < 3;
    }

}
