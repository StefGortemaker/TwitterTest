package com.example.tweeter;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tweeter.model.User;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.example.tweeter.AuthorizationManager.authService;

public class DetailActivity extends AppCompatActivity {

    private String url = "https://api.twitter.com/1.1/users/show.json?user_id=";

    ImageView imageView, backgroundImage;
    TextView tvName, tvScreenName, tvDescription, tvLocation;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.custom_title_bar);
        setSupportActionBar(toolbar);

        imageView = findViewById(R.id.profileImage);
        backgroundImage = findViewById(R.id.backgroundImage);
        tvName = findViewById(R.id.textViewName);
        tvScreenName = findViewById(R.id.textViewScreenName);
        tvDescription = findViewById(R.id.textViewDescription);
        tvLocation = findViewById(R.id.textViewLocation);

        Intent launchIntent = getIntent();

        if(launchIntent != null){
            String index = launchIntent.getStringExtra(MainActivity.USER_ID);

            String id = index;
            url += id;

            GetUser getUser = new GetUser();
            getUser.execute();
        }


    }

    public class GetUser extends AsyncTask<Void, Void, User>{

        @Override
        protected User doInBackground(Void... voids) {

            User user;

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
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
            Intent timelineEvent = new Intent(DetailActivity.this, ListActivity.class);
            startActivity(timelineEvent);
            return true;
        }else if (id == R.id.action_search){

            return true;
        }else if (id == R.id.action_user){

            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
