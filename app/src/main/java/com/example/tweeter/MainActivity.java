package com.example.tweeter;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.example.tweeter.AuthorizationManager.authService;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    OAuth1RequestToken reqToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.logInView);

        getRequestToken getrequestToken = new getRequestToken();
        getrequestToken.execute();

        /*
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
        */

        webView.setWebViewClient(new WebViewClient(){
        @Override public boolean shouldOverrideUrlLoading (WebView view, String url) {
            if (url.startsWith("https://tweeter.com")) {
                // Everything is ok... Check verifier
                Uri uri = Uri.parse(url);

                String verifier = uri.getQueryParameter("oauth_verifier");
                GetResponse getResponse = new GetResponse();
                getResponse.execute(verifier);
            }
            return false;
        }
    });

    }
    public class getRequestToken extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {

            String url = "";

            try {
            reqToken = authService.getRequestToken();
            url = authService.getAuthorizationUrl(reqToken);

            }catch (IOException i){
                i.printStackTrace();
            }catch (ExecutionException Ee) {
                Ee.printStackTrace();
            }catch (InterruptedException IE){
                IE.printStackTrace();
            }

            return url;
        }

        @Override
        protected void onPostExecute(String url) {
            webView.loadUrl(url);
        }
    }

    public class GetResponse extends AsyncTask<String, Void, Response>{

        @Override
        protected Response doInBackground(String... voids) {

            try {
                final OAuth1AccessToken accessToken = authService.getAccessToken(reqToken, voids[0]);

                OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json");

                authService.signRequest(accessToken, request);

                final Response response = authService.execute(request);

                return response;
            }catch (IOException i){
            i.printStackTrace();
            }catch (ExecutionException Ee) {
            Ee.printStackTrace();
            }catch (InterruptedException IE){
            IE.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Response s) {
            if (s.isSuccessful()){
                Intent intent = new Intent(MainActivity.this, com.example.tweeter.ListActivity.class);
                startActivity(intent);
            }
        }
    }


}

