package com.example.tweeter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Url {

    private String url;
    private String expanded_url;
    private String display_url;

    private ArrayList<Integer> indices = new ArrayList<>();

    public static Url fromJSON(JSONArray jsonArrayUrl) {
        Url url = new Url();
        if (jsonArrayUrl.length() != 0) {
            try {
                JSONObject jsonObjectURL = jsonArrayUrl.getJSONObject(0);
                if (jsonObjectURL != null) {
                    url.url = jsonObjectURL.getString("url");

                    url.expanded_url = jsonObjectURL.getString("expanded_url");
                    url.display_url = jsonObjectURL.getString("display_url");

                    JSONArray jsonIndices = jsonObjectURL.getJSONArray("indices");
                    for (int j = 0; j < jsonIndices.length(); j++) {
                        int a = jsonIndices.getInt(j);
                        url.indices.add(a);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return url;
    }
}
