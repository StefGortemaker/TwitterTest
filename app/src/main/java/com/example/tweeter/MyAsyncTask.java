package com.example.tweeter;

import android.os.AsyncTask;

import com.example.tweeter.model.Tweet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyAsyncTask extends AsyncTask <String, Double, Tweet[]> {

    @Override
    protected Tweet[] doInBackground(String... input) {

        try {
            //creating the url which needs to be conected to
            URL url = new URL("goedkopeserver.vanruud.nl/tweets.json");

            //connecting to the URL and returns an input stream which can be read;
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //reading the input stream from the URL
            InputStream inputStream = urlConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new Tweet[0];
    }

    @Override
    protected void onProgressUpdate(Double... progress) {
        super.onProgressUpdate(progress);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Tweet[] tweets) {
        super.onPostExecute(tweets);
    }
}
