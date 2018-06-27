package com.example.tweeter;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.tweeter.model.Dataprovider;
import com.example.tweeter.model.Tweet;
import com.example.tweeter.model.User;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.tweeter.AuthorizationManager.authService;

public class DetailActivity extends AppCompatActivity {

    private String url = "https://api.twitter.com/1.1/users/show.json?user_id=";
    private String timelineURL = "https://api.twitter.com/1.1/statuses/user_timeline.json?user_id=";

    ImageView imageView, backgroundImage;
    TextView tvName, tvScreenName, tvDescription, tvLocation;
    ListView tweetList;
    Button btnFollowers, btnFollowing;

    ListAdapter adapter;

    Toolbar toolbar;

    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.profileImage);
        backgroundImage = findViewById(R.id.backgroundImage);
        tvName = findViewById(R.id.textViewName);
        tvScreenName = findViewById(R.id.textViewScreenName);
        tvDescription = findViewById(R.id.textViewDescription);
        tvLocation = findViewById(R.id.textViewLocation);
        tweetList = findViewById(R.id.lvUserTweetList);
        btnFollowers = findViewById(R.id.btnFollowers);
        btnFollowing = findViewById(R.id.btnFollowing);

        toolbar = findViewById(R.id.custom_title_bar);
        setSupportActionBar(toolbar);

        Intent launchIntent = getIntent();

        if(launchIntent != null){
            String index = launchIntent.getStringExtra(AuthorizationManager.USER_ID);

            String id = String.valueOf(index);
            url += id;
            timelineURL += id;

            GetUser getUser = new GetUser();
            GetUserTimeline getTimeline = new GetUserTimeline();
            getUser.execute();
            getTimeline.execute();

        }

        btnFollowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent following = new Intent(DetailActivity.this, FollowActivity.class);
                following.putExtra(AuthorizationManager.USER_ID, user.getId_str());
                following.putExtra(AuthorizationManager.USER_FOLLOW_REQ, 0);
                startActivity(following);

            }
        });

        btnFollowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent followers = new Intent(DetailActivity.this, FollowActivity.class);
                followers.putExtra(AuthorizationManager.USER_ID, user.getId_str());
                followers.putExtra(AuthorizationManager.USER_FOLLOW_REQ, 1);
                startActivity(followers);
            }
        });
    }

    public class GetUser extends AsyncTask<Void, Void, User>{

        @Override
        protected User doInBackground(Void... voids) {

            try {
                OAuthRequest request = new OAuthRequest(Verb.GET, url);

                authService.signRequest(AuthorizationManager.accessToken, request);

                Response response = authService.execute(request);

                if (response.isSuccessful()) {
                    String res = response.getBody();

                    JSONObject userJsonObject = new JSONObject(res);
                    user = User.fromJSON(userJsonObject);

                    return user;
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

            return null;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);

            Picasso.get()
                    .load(user.getProfile_image_url())
                    .into(imageView);
            tvName.setText(user.getName());
            tvScreenName.setText(user.getScreen_name());
            tvDescription.setText(user.getDescription());
            tvLocation.setText(user.getLocation());
            Picasso.get()
                    .load(user.getProfile_banner_url())
                    .into(backgroundImage);

            String following = String.valueOf(user.getFriends_count());
            String followers = String.valueOf(user.getFollowers_count());

            btnFollowing.setText(following + " Following");
            btnFollowers.setText(followers + " Followers");
        }
    }

    private class GetUserTimeline extends AsyncTask<Void, Void, List<Tweet>>{


        @Override
        protected List<Tweet> doInBackground(Void... voids) {
            List<Tweet> tweets = new ArrayList<>();

            try{
                OAuthRequest request = new OAuthRequest(Verb.GET, timelineURL);

                authService.signRequest(AuthorizationManager.accessToken, request);

                Response response = authService.execute(request);

                if (response.isSuccessful()){
                    String res = response.getBody();

                    // JSONObject jo = new JSONObject(res);
                    JSONArray jsonArray = new JSONArray(res);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonTweet = jsonArray.getJSONObject(i);
                        Tweet tweet = Tweet.fromJSON(jsonTweet);

//                        Dataprovider.tweets.add(tweet);
                        tweets.add(tweet);
                    }

                    return tweets;
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

            return null;
        }

        @Override
        protected void onPostExecute(List<Tweet> tweets) {
            super.onPostExecute(tweets);

            adapter = new ListAdapter(DetailActivity.this, tweets);
            tweetList.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_timeline) {
            Intent timeline = new Intent(DetailActivity.this, TimeLineActivity.class);
            startActivity(timeline);
        } else if (id == R.id.action_user) {
            Intent signedInUserProfile = new Intent(DetailActivity.this, MainUserProfileActivity.class);
            startActivity(signedInUserProfile);
        } else if (id == R.id.action_post_tweet) {
            Intent postTweetIntent = new Intent(DetailActivity.this, PostTweetActivity.class);
            startActivity(postTweetIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
