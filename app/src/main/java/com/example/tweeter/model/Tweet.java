package com.example.tweeter.model;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Tweet {

    private String created_at;
    private int id;
    private String id_str;
    private String text;
    private boolean truncated;
    private String source;

    private int in_reply_to_status;
    private String in_reply_to_status_str;
    private int in_reply_to_user_id;
    private String in_reply_too_user_id_str;
    private String in_reply_to_screen_name;

//    private geo;
//    private coordinates;
//    private place;
//    private contributors;

    private boolean is_quote_status;
    private int retweet_count;
    private int favorite_count;
    private boolean favorited;
    private boolean retweeted;
    private String lang;

    private ArrayList<Entity> entities;

    public String getCreatAt() {
        return created_at;
    }

    public String getStringID() {
        return id_str;
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
            tweet.created_at = tweetObj.getString("created_at");
            tweet.id_str = tweetObj.getString("id_str");
            tweet.text = tweetObj.getString("text");
        }catch(JSONException e){
            e.printStackTrace();
        }
        return tweet;
    }

    private class Metadata{
        private String iso_language_code;
        private String result_type;
    }
}
