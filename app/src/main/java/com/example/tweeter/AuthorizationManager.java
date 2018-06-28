package com.example.tweeter;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.tweeter.model.User;
import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AuthorizationManager {

    public static OAuth1RequestToken reqToken;
    public static OAuth1AccessToken accessToken;

    public static final String USER_ID = "user_id";
    public static final String USER_FOLLOW_REQ = "following";

    private static AuthorizationManager manager;
    private static final String API_KEY = "UmPu4PuPg8YqGwPFDzJrG9Mli";
    private static final String API_SECRET = "Jm760oBNVhqoYLf8Ajf4TOXgWwHaDKLZOdaJHMpKKvkEH0v0SN";
    private static final String CALLBACK_URL = "https://tweeter.com";

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

    public class FollowRequest extends AsyncTask<Void, Void, User> {

        Context context;
        Toast toast;
        User user;
        String url;
        String urlFollow = "https://api.twitter.com/1.1/friendships/create.json?user_id=";
        String urlUnfollow = "https://api.twitter.com/1.1/friendships/destroy.json?user_id=";

        public FollowRequest(User user, boolean follow, Context context){
            this.context = context;
            this.user = user;
            if (!follow){
                url = urlFollow + user.getId_str();
                toast = Toast.makeText(context, "Followed: " + user.getName(), Toast.LENGTH_SHORT);
            } else {
                url = urlUnfollow + user.getId_str();
                toast = Toast.makeText(context, "Unfollowed: " + user.getName(), Toast.LENGTH_SHORT);
            }
        }

        @Override
        protected User doInBackground(Void... voids) {

            try {
                OAuthRequest request = new OAuthRequest(Verb.POST, url);

                authService.signRequest(accessToken, request);

                Response response = authService.execute(request);

                if (response.isSuccessful()){
                    String res = response.getBody();
                    JSONObject jo = new JSONObject(res);
                    user = User.fromJSON(jo);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);

            toast.show();
        }
    }
}
