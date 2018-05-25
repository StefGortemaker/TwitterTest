package com.example.tweeter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserMention{

    private String screen_name;
    private String name;
    private int id;
    private String id_str;

    private ArrayList<Integer> indices;

    public static UserMention fromJSON(JSONObject userMentionObject){
        UserMention userMention = new UserMention();
        try{
            userMention.screen_name = userMentionObject.getString("screen_name");
            userMention.name = userMentionObject.getString("name");
            userMention.id = userMentionObject.getInt("id");
            userMention.id_str = userMentionObject.getString("id_str");

            JSONArray jsonArray = userMentionObject.getJSONArray("indices");
            for(int i = 0; i < jsonArray.length(); i++) {
                int a = jsonArray.getInt(i);
                userMention.indices.add(a);
            }

        }catch(JSONException e){
            e.printStackTrace();
        }

        return userMention;
    }
}
