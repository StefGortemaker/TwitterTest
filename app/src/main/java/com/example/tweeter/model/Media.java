package com.example.tweeter.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Media extends BaseEntity{

    private String display_url;
    private String url;
    private String media_url;

    public Media(int startIndice, int endIndice) {
        super(startIndice, endIndice);
    }

    public static Media fromJSON(JSONObject jsonObjectMedia){
        Media media = new Media(0, 0);
        try{
            media.display_url = jsonObjectMedia.getString("display_url");
            media.media_url = jsonObjectMedia.getString("media_url_https");
            media.url = jsonObjectMedia.getString("url");

            JSONArray jsonIndices = jsonObjectMedia.getJSONArray("indices");
            media.setStartIndice(jsonIndices.getInt(0));
            media.setEndIndice(jsonIndices.getInt(1));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return media;
    }

    public String getDisplay_url() {
        return display_url;
    }

    public String getUrl() {
        return url;
    }

    public String getMedia_url() {
        return media_url;
    }
}
