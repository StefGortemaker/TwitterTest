package com.example.tweeter;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tweeter.model.Dataprovider;
import com.example.tweeter.model.User;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.example.tweeter.AuthorizationManager.authService;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.logInView);

        getRequestToken getrequestToken = new getRequestToken();
        getrequestToken.execute();

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
            AuthorizationManager.reqToken = authService.getRequestToken();
            url = authService.getAuthorizationUrl(AuthorizationManager.reqToken);

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
                AuthorizationManager.accessToken = authService.getAccessToken(AuthorizationManager.reqToken, voids[0]);

                OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json");

                authService.signRequest(AuthorizationManager.accessToken, request);

                final Response response = authService.execute(request);

                String res = response.getBody();
                JSONObject jo = new JSONObject(res);

                Dataprovider.signedInuUser = User.fromJSON(jo);


                return response;
            }catch (IOException i){
            i.printStackTrace();
            }catch (ExecutionException Ee) {
            Ee.printStackTrace();
            }catch (InterruptedException IE){
            IE.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Response response) {
            if (response.isSuccessful()){
                Intent intent = new Intent(MainActivity.this, com.example.tweeter.MainUserProfileActivity.class);
                startActivity(intent);
            }
        }
    }


}

