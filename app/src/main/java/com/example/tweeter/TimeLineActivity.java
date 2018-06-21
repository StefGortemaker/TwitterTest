package com.example.tweeter;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.example.tweeter.model.Dataprovider;
import com.example.tweeter.model.Tweet;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.tweeter.AuthorizationManager.authService;

public class TimeLineActivity extends AppCompatActivity {

    private String homeTimeLineUrl = "https://api.twitter.com/1.1/statuses/home_timeline.json";

    private ListView homeTimeLineList;
    private ListAdapter listAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        homeTimeLineList = findViewById(R.id.HomeTimeLineListView);
        toolbar = findViewById(R.id.custom_title_bar);
        setSupportActionBar(toolbar);

        Intent launchIntent = getIntent();

        if(launchIntent != null){
            GetHomeTimeLine getHomeTimeLine = new GetHomeTimeLine();
            getHomeTimeLine.execute();
        }
    }

    private class GetHomeTimeLine extends AsyncTask<Void, Void, List<Tweet>>{
        @Override
        protected List<Tweet> doInBackground(Void... voids) {
            List<Tweet> tweets = new ArrayList<>();

            try{
                OAuthRequest request = new OAuthRequest(Verb.GET, homeTimeLineUrl);

                authService.signRequest(AuthorizationManager.accessToken, request);

                Response response = authService.execute(request);

                if (response.isSuccessful()){
                    String res = response.getBody();

                    JSONArray jsonArray = new JSONArray(res);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonTweet = jsonArray.getJSONObject(i);
                        Tweet tweet = Tweet.fromJSON(jsonTweet);

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

            listAdapter = new ListAdapter(TimeLineActivity.this, tweets);
            homeTimeLineList.setAdapter(listAdapter);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
