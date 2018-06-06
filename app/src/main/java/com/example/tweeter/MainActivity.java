package com.example.tweeter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tweeter.model.Dataprovider;
import com.example.tweeter.model.Tweet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyAsyncTask myTask = new MyAsyncTask();

        listView = findViewById(R.id.listView);

        InputStream is = getBaseContext().getResources().openRawResource(R.raw.statuses);

        try {
            byte[] b = new byte[is.available()];
            is.read(b);
            String fileContent = new String(b);
            JSONObject jsonObject = new JSONObject(fileContent);
            JSONArray jsonArray = jsonObject.getJSONArray("statuses");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject explrObject = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(explrObject);
                Dataprovider.tweets.add(tweet);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException jsone) {
            jsone.printStackTrace();
        }

        ListAdapter adapter = new ListAdapter(this, Dataprovider.tweets);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
                detailIntent.putExtra("Taskindex", position);
                startActivity(detailIntent);
            }
        });

    }
}

