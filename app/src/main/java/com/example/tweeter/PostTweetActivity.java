package com.example.tweeter;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

import static com.example.tweeter.AuthorizationManager.authService;

public class PostTweetActivity extends AppCompatActivity {

    private String postTweetURL = "https://api.twitter.com/1.1/statuses/update.json?status=";

    EditText etTwitterText;
    Button btnPostTweet;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_tweet);

        final PostTweet postTweet = new PostTweet();

        etTwitterText = findViewById(R.id.etTweetText);
        btnPostTweet = findViewById(R.id.bPostTweet);

        toolbar = findViewById(R.id.custom_title_bar);

        btnPostTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etTwitterText.getText().toString().matches("")){
                    postTweetURL += urlEncode(etTwitterText.getText().toString());
                    postTweet.execute();
                }

            }
        });
    }

    public class PostTweet extends AsyncTask<Void, Void, Response>{

        @Override
        protected Response doInBackground(Void... voids) {
            try{
                OAuthRequest request = new OAuthRequest(Verb.POST, postTweetURL);

                authService.signRequest(AuthorizationManager.accessToken, request);

                Response response = authService.execute(request);

                return response;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            if (response.isSuccessful()){
                Toast toast = Toast.makeText(getApplicationContext(), "Tweet Posted", Toast.LENGTH_SHORT);
                toast.show();
            }
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
            Intent timelineIntent = new Intent(PostTweetActivity.this, ListActivity.class);
            startActivity(timelineIntent);
            return true;
        } // else if (id == R.id.action_post_tweet){
//            Intent postTweetIntent = new Intent(PostTweetActivity.this, PostTweetActivity.class);
//            startActivity(postTweetIntent);
//        }

        return super.onOptionsItemSelected(item);
    }

    private String urlEncode (String tweet) {
        try {
            return URLEncoder.encode(tweet, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
