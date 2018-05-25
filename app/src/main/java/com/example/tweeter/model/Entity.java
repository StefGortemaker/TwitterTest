package com.example.tweeter.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Entity {

    public static Entity fromJSON(JSONObject entityObject){
        Entity entity = new Entity();
        try{
            JSONObject hashtagJsonObject = entityObject.getJSONObject("hashtag");
            Hashtag hashtag = Hashtag.fromJSON(hashtagJsonObject);
            Dataprovider.hashtags.add(hashtag);

            JSONObject userMentionJsonObject = entityObject.getJSONObject("user_mentions");
            UserMention userMention = UserMention.fromJSON(userMentionJsonObject);
            Dataprovider.userMentions.add(userMention);

            JSONObject urlJsonObject = entityObject.getJSONObject("urls");
            Url url = Url.fromJSON(urlJsonObject);
            Dataprovider.urls.add(url);

        }catch(JSONException e){
            e.printStackTrace();
        }
        return entity;
    }
}
