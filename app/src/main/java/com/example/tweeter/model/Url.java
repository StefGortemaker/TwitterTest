package com.example.tweeter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Url extends BaseEntity {

    private String url;
    private String expanded_url;
    private String display_url;

    public Url(int startIndice, int endIndice) {
        super(startIndice, endIndice);
    }

    public String getUrl() {
        return url;
    }

    public String getExpanded_url() {
        return expanded_url;
    }

    public String getDisplay_url() {
        return display_url;
    }

    public static Url fromJSON(JSONObject jsonObjectURL) {
        Url url = new Url(0,0);
       // if (jsonArrayUrl.length() != 0) {
            try {
              //  JSONObject jsonObjectURL = jsonArrayUrl.getJSONObject(0);
                if (jsonObjectURL != null) {
                    url.url = jsonObjectURL.getString("url");

                    url.expanded_url = jsonObjectURL.getString("expanded_url");
                    url.display_url = jsonObjectURL.getString("display_url");

                    JSONArray jsonIndices = jsonObjectURL.getJSONArray("indices");
                    url.setStartIndice(jsonIndices.getInt(0));
                    url.setEndIndice(jsonIndices.getInt(1));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        //}
        return url;
    }
}
