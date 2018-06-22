package com.example.tweeter;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tweeter.model.Dataprovider;
import com.example.tweeter.model.Tweet;
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

public class MainUserProfileActivity extends AppCompatActivity{

    private String timelineURL = "https://api.twitter.com/1.1/statuses/user_timeline.json?user_id=";

    ImageView imageView, backgroundImage;
    TextView tvName, tvScreenName, tvDescription, tvLocation;
    ListView tweetList;

    ListAdapter adapter;

    Toolbar toolbar ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tweetList = findViewById(R.id.lvUserTweetList);

        timelineURL += Dataprovider.signedInuUser.getId_str();

        imageView = findViewById(R.id.profileImage);
        backgroundImage = findViewById(R.id.backgroundImage);
        tvName = findViewById(R.id.textViewName);
        tvScreenName = findViewById(R.id.textViewScreenName);
        tvDescription = findViewById(R.id.textViewDescription);
        tvLocation = findViewById(R.id.textViewLocation);

        toolbar = findViewById(R.id.custom_title_bar);
        setSupportActionBar(toolbar);

        Intent launchIntent = getIntent();

        if (launchIntent != null){

            GetUserTimeline getTimeline = new GetUserTimeline();
            getTimeline.execute();

            Picasso.get()
                    .load(Dataprovider.signedInuUser.getProfile_image_url())
                    .into(imageView);
            tvName.setText(Dataprovider.signedInuUser.getName());
            tvScreenName.setText(Dataprovider.signedInuUser.getScreen_name());
            tvDescription.setText(Dataprovider.signedInuUser.getDescription());
            tvLocation.setText(Dataprovider.signedInuUser.getLocation());
            Picasso.get()
                    .load(Dataprovider.signedInuUser.getProfile_banner_url())
                    .into(backgroundImage);
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

            adapter = new ListAdapter(MainUserProfileActivity.this, tweets);
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
            Intent timelineIntent = new Intent(MainUserProfileActivity.this, TimeLineActivity.class);
            startActivity(timelineIntent);
            return true;
        } else if (id == R.id.action_post_tweet){
            Intent postTweetIntent = new Intent(MainUserProfileActivity.this, PostTweetActivity.class);
            startActivity(postTweetIntent);
        }

        return super.onOptionsItemSelected(item);
    }


}
