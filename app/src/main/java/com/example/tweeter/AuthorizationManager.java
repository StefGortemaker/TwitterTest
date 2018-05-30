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
            ServiceBuilder(API_KEY)
                .apiSecret(API_SECRET)
                .callback(CALLBACK_URL)
                .build(TwitterApi.instance());
}
