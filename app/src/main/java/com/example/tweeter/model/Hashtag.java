package com.example.tweeter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Hashtag extends BaseEntity{

    private String text;

    public Hashtag(int startIndice, int endIndice) {
        super(startIndice, endIndice);
    }

    public String getText(){ return text; }

    public static Hashtag fromJSON(JSONObject jsonObjectHashtag) {
        Hashtag hashtag = new Hashtag(0, 0);
            try {
                //JSONObject jsonObjectHashtag = jsonArrayHashtag.getJSONObject(0);
                if (jsonObjectHashtag != null) {
                    hashtag.text = jsonObjectHashtag.getString("text");

                    JSONArray JSONindices = jsonObjectHashtag.getJSONArray("indices");
                    hashtag.setStartIndice(JSONindices.getInt(0));
                    hashtag.setEndIndice(JSONindices.getInt(1));

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        return hashtag;
    }

}