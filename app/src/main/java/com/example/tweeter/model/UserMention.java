package com.example.tweeter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserMention {

    private String screen_name;
    private String name;
    private int id;
    private String id_str;

    private ArrayList<Integer> indices = new ArrayList<>();

    public static UserMention fromJSON(JSONArray jsonArrayUserMention) {
        UserMention userMention = new UserMention();
        if (jsonArrayUserMention.length() != 0) {
            try {
                JSONObject jsonObjectUserMention = jsonArrayUserMention.getJSONObject(0);

                if (jsonObjectUserMention != null) {
                    userMention.screen_name = jsonObjectUserMention.getString("screen_name");
                    userMention.name = jsonObjectUserMention.getString("name");
                    userMention.id = jsonObjectUserMention.getInt("id");
                    userMention.id_str = jsonObjectUserMention.getString("id_str");

                    JSONArray jsonIndices = jsonObjectUserMention.getJSONArray("indices");
                    for (int i = 0; i < jsonIndices.length(); i++) {
                        int a = jsonIndices.getInt(i);
                        userMention.indices.add(a);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return userMention;
    }
}
