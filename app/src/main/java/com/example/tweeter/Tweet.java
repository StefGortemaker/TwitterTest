package com.example.tweeter;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Tweet {

    private String creatAt;
    private String stringID;
    private String text;
    private boolean truncated;

    private ArrayList<Entities> entities;

    public String getCreatAt() {
        return creatAt;
    }

    public String getStringID() {
        return stringID;
    }

    public String getText() {
        return text;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public static Tweet fromJSON(JSONObject tweetObj) {
        Tweet tweet = new Tweet();
        try {
            tweet.creatAt = tweetObj.getString("created_at");
            tweet.stringID = tweetObj.getString("id_str");
            tweet.text = tweetObj.getString("text");
        }catch(JSONException e){
            e.printStackTrace();
            return null;
        }
        return tweet;
    }

    private class Metadata{
        private String iso_language_code;
        private String result_type;
    }
}
