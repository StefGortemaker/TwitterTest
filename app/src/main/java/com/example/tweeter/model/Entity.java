package com.example.tweeter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Entity {

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
