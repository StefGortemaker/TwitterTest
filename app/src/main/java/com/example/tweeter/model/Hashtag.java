package com.example.tweeter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Hashtag{

    private String text;

    private ArrayList<Integer> indices;

    public static Hashtag fromJSON(JSONObject hashtagObject){
        Hashtag hashtag = new Hashtag();
        try{
            hashtag.text = hashtagObject.getString("text");

            JSONArray jsonArray = hashtagObject.getJSONArray("indices");
            for(int i = 0; i < jsonArray.length(); i++) {
                int a = jsonArray.getInt(i);
                hashtag.indices.add(a);
            }

        }catch(JSONException e){
            e.printStackTrace();
        }
        return hashtag;
    }
}