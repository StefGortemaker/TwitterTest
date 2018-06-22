package com.example.tweeter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Entity {

    public ArrayList<Hashtag> hashtags = new ArrayList<>();
    public ArrayList<UserMention> userMentions = new ArrayList<>();
    public ArrayList<Url> urls = new ArrayList<>();

    public ArrayList<Url> getUrls() {
        return urls;
    }

    public ArrayList<UserMention> getUserMentions() {
        return userMentions;
    }

    public ArrayList<Hashtag> getHashtags() {
        return hashtags;
    }

    public static Entity fromJSON(JSONObject jsonObject){
        Entity entity = new Entity();
        try{

            JSONArray jsonArrayHastag = jsonObject.getJSONArray("hashtags");
            Hashtag hashtag = Hashtag.fromJSON(jsonArrayHastag);
            entity.hashtags.add(hashtag);
            Dataprovider.hashtags.add(hashtag);

            JSONArray jsonArrayUserMention = jsonObject.getJSONArray("user_mentions");
            UserMention userMention = UserMention.fromJSON(jsonArrayUserMention);
            entity.userMentions.add(userMention);
            Dataprovider.userMentions.add(userMention);

            JSONArray jsonArrayUrl = jsonObject.getJSONArray("urls");
            Url url = Url.fromJSON(jsonArrayUrl);
            entity.urls.add(url);
            Dataprovider.urls.add(url);

        }catch(JSONException e){
            e.printStackTrace();
        }
        return entity;
    }
}
