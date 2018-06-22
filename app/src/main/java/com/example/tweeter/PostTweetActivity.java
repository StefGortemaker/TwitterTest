package com.example.tweeter;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.tweeter.model.Dataprovider;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

import static com.example.tweeter.AuthorizationManager.authService;

public class PostTweetActivity extends AppCompatActivity {

    private String postTweetURL = "https://api.twitter.com/1.1/statuses/update.json?status=";

    EditText etTwitterText;
    Button btnPostTweet;
    ImageView userProfileImage;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_tweet);

        final PostTweet postTweet = new PostTweet();

        etTwitterText = findViewById(R.id.etTweetText);
        btnPostTweet = findViewById(R.id.bPostTweet);
        userProfileImage = findViewById(R.id.ptIVuserImage);


        toolbar = findViewById(R.id.custom_title_bar);

        Picasso.get()
                .load(Dataprovider.signedInuUser.getProfile_image_url())
                .into(userProfileImage);
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
                finish();
            }
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
}
