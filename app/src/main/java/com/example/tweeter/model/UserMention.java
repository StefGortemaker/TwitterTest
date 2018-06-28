package com.example.tweeter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserMention extends BaseEntity{

    private String screen_name;
    private String name;
    private int id;
    private String id_str;

    public String getScreen_name() {
        return screen_name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getId_str() {
        return id_str;
    }

    public UserMention(int startIndice, int endIndice) {
        super(startIndice, endIndice);
    }

    public static UserMention fromJSON(JSONObject jsonObjectUserMention) {
        UserMention userMention = new UserMention(0,0);
        // if (jsonObjectUserMention != null) {
            try {
                //JSONObject jsonObjectUserMention = jsonArrayUserMention.getJSONObject(0);

                if (jsonObjectUserMention != null) {
                    userMention.screen_name = jsonObjectUserMention.getString("screen_name");
                    userMention.name = jsonObjectUserMention.getString("name");
                    userMention.id = jsonObjectUserMention.getInt("id");
                    userMention.id_str = jsonObjectUserMention.getString("id_str");

                    JSONArray jsonIndices = jsonObjectUserMention.getJSONArray("indices");
                    userMention.setStartIndice(jsonIndices.getInt(0));
                    userMention.setEndIndice(jsonIndices.getInt(1));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        //}

        return userMention;
    }
}
