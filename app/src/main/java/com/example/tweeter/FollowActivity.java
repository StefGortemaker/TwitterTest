package com.example.tweeter;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tweeter.model.User;
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

public class FollowActivity extends AppCompatActivity {

    private String url;

    List<User> followUsers;
    String cursor;
    ListView lv;

    FollowAdapter adapter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv = findViewById(R.id.HomeTimeLineListView);
        toolbar = findViewById(R.id.custom_title_bar);
        setSupportActionBar(toolbar);

        if (adapter != null) {
            adapter.clear();
            adapter.notifyDataSetChanged();
        }

        Intent launchIntent = getIntent();
        if (launchIntent != null) {
            String index = launchIntent.getStringExtra(AuthorizationManager.USER_ID);
            int req = launchIntent.getIntExtra(AuthorizationManager.USER_FOLLOW_REQ, -1);

            if (req == 1) {
                url = "https://api.twitter.com/1.1/followers/list.json?user_id=" + index;
            } else {
                url = "https://api.twitter.com/1.1/friends/list.json?user_id=" + index;
            }

            followUsers = new ArrayList<>();

            GetFollowers getFollowers = new GetFollowers();
            getFollowers.execute();
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent userProfile = new Intent(FollowActivity.this, DetailActivity.class);
                User user = followUsers.get(position);
                String userid = user.getId_str();
                userProfile.putExtra(AuthorizationManager.USER_ID, userid);
                startActivity(userProfile);
            }
        });
    }

    private class GetFollowers extends AsyncTask<Void, Void, List<User>> {

        @Override
        protected List<User> doInBackground(Void... voids) {
            try {
                OAuthRequest request = new OAuthRequest(Verb.GET, url);

                authService.signRequest(AuthorizationManager.accessToken, request);

                Response response = authService.execute(request);

                if (response.isSuccessful()){
                    String res = response.getBody();

                    JSONObject jo = new JSONObject(res);
                    cursor = jo.getString("next_cursor_str");
                    JSONArray jsonArray = jo.getJSONArray("users");

                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonUser = jsonArray.getJSONObject(i);
                        User user = User.fromJSON(jsonUser);
                        followUsers.add(user);
                    }
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

            return followUsers;
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);

            adapter = new FollowAdapter(FollowActivity.this, followUsers);
            lv.setAdapter(adapter);
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
            Intent timelineIntent = new Intent(FollowActivity.this, TimeLineActivity.class);
            startActivity(timelineIntent);
            return true;
        } else if (id == R.id.action_post_tweet){
            Intent postTweetIntent = new Intent(FollowActivity.this, PostTweetActivity.class);
            startActivity(postTweetIntent);
        }  else if (id == R.id.action_user) {
            Intent userIntent = new Intent(FollowActivity.this, MainUserProfileActivity.class);
            startActivity(userIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
