package com.example.tweeter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Entity {

    private List<Hashtag> hashtags;
    private List<Media> media;
    private List<Url> urls;
    private List<UserMention> userMentions;
    private List<Symbol> symbols;


    public static Entity fromJSON(JSONObject jsonObject){
        Entity entity = new Entity();
        try{

            JSONArray jsonArrayHastag = jsonObject.getJSONArray("hashtags");
            //Hashtag hashtag =
            Hashtag.fromJSON(jsonArrayHastag);
            //Dataprovider.hashtags.add(hashtag);

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
