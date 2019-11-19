package com.example.simpleinstagramapp.Models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String DESCRIPTION_KEY = "description";
    public static final String IMAGE_KEY = "image";
    public static final String USER_KEY = "user";
    public static final String CREATED_AT = "createdAt";
    public Post() {
        super ();
    }

    public void setDescription (String description) {
        put(DESCRIPTION_KEY, description);
    }

    public void setImage (ParseFile image) {
        put (IMAGE_KEY, image);
    }

    public void setUser (ParseUser user) {
        put (USER_KEY, user);
    }

    public String getDescription () {
        return getString(DESCRIPTION_KEY);
    }

    public ParseFile getImage () {
        return getParseFile(IMAGE_KEY);
    }

    public ParseUser getUser () {
        return getParseUser(USER_KEY);
    }

}
