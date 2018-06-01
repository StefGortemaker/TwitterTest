package com.example.tweeter;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth10aService;

public class AuthorizationManager {

    private static AuthorizationManager manager;
    private static String API_KEY = "UmPu4PuPg8YqGwPFDzJrG9Mli";
    private static String API_SECRET = "Jm760oBNVhqoYLf8Ajf4TOXgWwHaDKLZOdaJHMpKKvkEH0v0SN";
    private static String CALLBACK_URL = "https://tweeter.com";

    public static AuthorizationManager getInstance() {
        if (manager== null) {
            manager = new AuthorizationManager();
        }
        return manager;
    }

    public static OAuth10aService authService = new
            ServiceBuilder(API_KEY).
            apiSecret(API_SECRET).
            callback(CALLBACK_URL).
            build(TwitterApi.instance());
}
