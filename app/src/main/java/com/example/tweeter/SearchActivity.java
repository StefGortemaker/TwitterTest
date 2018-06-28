package com.example.tweeter;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.example.tweeter.model.Tweet;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.tweeter.AuthorizationManager.authService;

public class SearchActivity extends AppCompatActivity {

    private String url;

    private Toolbar toolbar;
    private SearchView searchView;
    private ListView listViewSearch ;
    private ListAdapter listAdapter;
    private List<Tweet> tweets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = findViewById(R.id.custom_title_bar);
        searchView = findViewById(R.id.searchView);
        listViewSearch = findViewById(R.id.listview_search);
        setSupportActionBar(toolbar);

        Intent launchIntent = getIntent();

        if (launchIntent != null) {

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    url = "https://api.twitter.com/1.1/search/tweets.json?q=";
                    url += urlEncode(query);
                    GetSearch getSearch = new GetSearch();
                    getSearch.execute();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }

            });
        }
    }

    private class GetSearch extends AsyncTask<Void, Void, List<Tweet>> {
        @Override
        protected List<Tweet> doInBackground(Void... voids) {

            try {
                tweets.clear();
                OAuthRequest request = new OAuthRequest(Verb.GET, url);

                authService.signRequest(AuthorizationManager.accessToken, request);

                Response response = authService.execute(request);

                if (response.isSuccessful()) {
                    String res = response.getBody();

                    JSONObject jsonObject = new JSONObject(res);
                    JSONArray jsonArray = jsonObject.getJSONArray("statuses");

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

            listAdapter = new ListAdapter(SearchActivity.this, tweets);
            listViewSearch.setAdapter(listAdapter);
        }
    }

    private String urlEncode (String tweet) {
        try {
            return URLEncoder.encode(tweet, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
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
            Intent timeline = new Intent(SearchActivity.this, TimeLineActivity.class);
            startActivity(timeline);
        } else if (id == R.id.action_user) {
            Intent signedInUserProfile = new Intent(SearchActivity.this, MainUserProfileActivity.class);
            startActivity(signedInUserProfile);
        } else if (id == R.id.action_post_tweet) {
            Intent postTweetIntent = new Intent(SearchActivity.this, PostTweetActivity.class);
            startActivity(postTweetIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
