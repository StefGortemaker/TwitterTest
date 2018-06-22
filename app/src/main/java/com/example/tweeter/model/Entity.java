package com.example.tweeter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Entity {

    private List<Hashtag> hashtags;
    private List<Media> media;
    private List<Url> urls;
    private List<UserMention> userMentions;
    private List<Symbol> symbols;

    private Entity(){
        hashtags = new ArrayList<>();
        media = new ArrayList<>();
        urls = new ArrayList<>();
        userMentions = new ArrayList<>();
        symbols = new ArrayList<>();
    }

    public static Entity fromJSON(JSONObject jsonObject){
        Entity entity = new Entity();
        try{

            JSONArray jsonArrayHastag = jsonObject.getJSONArray("hashtags");
            for (int i = 0; i < jsonArrayHastag.length(); i++) {
                entity.hashtags.add(Hashtag.fromJSON(jsonArrayHastag));
            }
            JSONArray jsonArrayUserMention = jsonObject.getJSONArray("user_mentions");
            //UserMention userMention =
            UserMention.fromJSON(jsonArrayUserMention);
            //Dataprovider.userMentions.add(userMention);

            JSONArray jsonArrayUrl = jsonObject.getJSONArray("urls");
            //Url url =
            Url.fromJSON(jsonArrayUrl);
            //Dataprovider.urls.add(url);

        }catch(JSONException e){
            e.printStackTrace();
        }
        return entity;
    }
}
