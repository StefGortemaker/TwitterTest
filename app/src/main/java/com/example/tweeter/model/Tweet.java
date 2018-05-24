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

    public static Tweet fromJSON(JSONObject tweetObj) {
        Tweet tweet = new Tweet();
        try {
            tweet.created_at = tweetObj.getString("created_at");
            tweet.id_str = tweetObj.getString("id_str");
            tweet.text = tweetObj.getString("text");
            tweet.id = tweetObj.getInt("id");

            tweet.retweet_count = tweetObj.getInt("retweet_count");
            tweet.favorite_count = tweetObj.getInt("favorite_count");
        }catch(JSONException e){
            e.printStackTrace();
        }
        return tweet;
    }

    private class Metadata{
        private String iso_language_code;
        private String result_type;
    }

    //getters
    public String getCreated_at() {
        return created_at;
    }

    public int getId() {
        return id;
    }

    public String getId_str() {
        return id_str;
    }

    public String getText() {
        return text;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public String getSource() {
        return source;
    }

    public int getIn_reply_to_status() {
        return in_reply_to_status;
    }

    public String getIn_reply_to_status_str() {
        return in_reply_to_status_str;
    }

    public int getIn_reply_to_user_id() {
        return in_reply_to_user_id;
    }

    public String getIn_reply_too_user_id_str() {
        return in_reply_too_user_id_str;
    }

    public String getIn_reply_to_screen_name() {
        return in_reply_to_screen_name;
    }

    public boolean isIs_quote_status() {
        return is_quote_status;
    }

    public int getRetweet_count() {
        return retweet_count;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public boolean isRetweeted() {
        return retweeted;
    }

    public String getLang() {
        return lang;
    }
}
