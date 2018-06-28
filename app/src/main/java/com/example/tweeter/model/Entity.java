package com.example.tweeter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Entity {

    private List<Hashtag> hashtags = new ArrayList<>();
    private List<Media> media;
    private List<Url> urls = new ArrayList<>();
    private List<UserMention> userMentions = new ArrayList<>();
    private List<Symbol> symbols;

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public List<UserMention> getUserMentions() {
        return userMentions;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public static Entity fromJSON(JSONObject jsonObject) {
        Entity entity = new Entity();
        try {

            JSONArray jsonArrayHastag = jsonObject.getJSONArray("hashtags");
            for (int i = 0; i < jsonArrayHastag.length(); i++) {
                Hashtag hashtag = Hashtag.fromJSON(jsonArrayHastag.getJSONObject(i));
                entity.hashtags.add(hashtag);
            }

            JSONArray jsonArrayUserMention = jsonObject.getJSONArray("user_mentions");
            for (int i = 0; i < jsonArrayUserMention.length(); i++) {
                UserMention userMention = UserMention.fromJSON(jsonArrayUserMention.getJSONObject(i));
                entity.userMentions.add(userMention);
            }

            JSONArray jsonArrayUrl = jsonObject.getJSONArray("urls");
            for(int i = 0 ; i < jsonArrayUrl.length(); i++) {
                Url url = Url.fromJSON(jsonArrayUrl.getJSONObject(i));
                entity.urls.add(url);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
