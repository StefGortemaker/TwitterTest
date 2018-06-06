package com.example.tweeter;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth10aService;

public class AuthorizationManager {
    private static AuthorizationManager manager;

    public static AuthorizationManager getInstance() {
        if (manager == null) {
            manager = new AuthorizationManager();
        }

        return manager;
    }

    OAuth10aService authService = new
            ServiceBuilder("UmPu4PuPg8YqGwPFDzJrG9Mli")
                .apiSecret("Jm760oBNVhqoYLf8Ajf4TOXgWwHaDKLZOdaJHMpKKvkEH0v0SN")
                .callback("https://www.google.com")
                .build(TwitterApi.instance());
}
