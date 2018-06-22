package com.example.tweeter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Hashtag {

    private String text;

    private ArrayList<Integer> indices = new ArrayList<>();

    public String getText(){ return text; }

    public ArrayList<Integer> getIndices() {
        return indices;
    }

    public int getStartIndiceIndex(){
        return indices.indexOf(0);
    }

    public int getEndIndiceIndex(){
        return indices.indexOf(1);
    }

    public static Hashtag fromJSON(JSONArray jsonArrayHashtag) {
        Hashtag hashtag = new Hashtag();
        if (jsonArrayHashtag.length() != 0) {
            try {
                JSONObject jsonObjectHashtag = jsonArrayHashtag.getJSONObject(0);
                if (jsonObjectHashtag != null) {
                    hashtag.text = jsonObjectHashtag.getString("text");


                    JSONArray JSONindices = jsonObjectHashtag.getJSONArray("indices");
                    for (int j = 0; j < JSONindices.length(); j++) {
                        int a = JSONindices.getInt(j);
                        hashtag.indices.add(a);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return hashtag;
    }
}