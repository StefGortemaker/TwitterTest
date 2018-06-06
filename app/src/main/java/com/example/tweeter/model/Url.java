package com.example.tweeter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Url{

    private String url;
    private String expanded_url;
    private String display_url;

    private ArrayList<Integer> indices;

    public static Url fromJSON(JSONObject urlObject){
        Url url = new Url();
        try{
            url.url = urlObject.getString("url");
            url.expanded_url = urlObject.getString("expanded_url");
            url.display_url = urlObject.getString("display_url");

            JSONArray jsonArray = urlObject.getJSONArray("indices");
            for(int i = 0; i < jsonArray.length(); i++) {
                int a = jsonArray.getInt(i);
                url.indices.add(a);
            }

        }catch(JSONException e){
            e.printStackTrace();
        }
        return url;
    }
}
