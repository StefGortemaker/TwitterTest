package com.example.tweeter;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.tweeter.model.Tweet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

         /*InputStream is = getBaseContext().getResources().openRawResource(R.raw.statuses);

        try {
            byte [] b = new byte[is.available()];
            is.read(b);
            String fileContent = new JSONObject();
        } catch (IOException e){
            e.printStackTrace();
        }
        */
    }

    public class GetTimeLineForMainActivityTask extends AsyncTask<Void, Void, List<Tweet>> {

        @Override
        protected List<Tweet> doInBackground(Void... voids) {

            List<Tweet> respones = new ArrayList<>();

            // OAuthRequest requestForTimeLine = new OAuthRequest(Verb.GET, )
            return null;
        }
    }
    }

