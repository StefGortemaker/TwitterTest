package com.example.tweeter;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;

public class AuthorizationManager {

    public static OAuth1RequestToken reqToken;
    public static OAuth1AccessToken accessToken;

    public static final String USER_ID = "user_id";

    private static AuthorizationManager manager;
    private static String API_KEY = "UmPu4PuPg8YqGwPFDzJrG9Mli";
    private static String API_SECRET = "Jm760oBNVhqoYLf8Ajf4TOXgWwHaDKLZOdaJHMpKKvkEH0v0SN";
    private static String CALLBACK_URL = "https://tweeter.com";

    public static OAuth1RequestToken reqToken;
    public static OAuth1AccessToken accessToken;

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
