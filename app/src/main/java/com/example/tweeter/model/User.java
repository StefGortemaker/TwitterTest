package com.example.tweeter.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    private int id;
    private String id_str;

    private String name;
    private String screen_name;

    private String location;
    private String description;
    private String url;

    private boolean protect;
    private int followers_count;
    private int friends_count;
    private int listed_count;
    private int favourites_count;
    private boolean verified;
    private int statuses_count;

    private String created_at;

    private int utc_offset;
    private String time_zone;
    private boolean geo_enabled;
    private String lang;

    private boolean contributors_enabled;
    private boolean is_translator;
    private boolean is_translation_enabled;

    private String profile_background_image_url;
    private String profile_background_image_url_https;
    private boolean profile_background_title;
    private String profile_image_url;
    private String profile_image_url_https;
    private String profile_banner_url;
    private String profile_link_color;
    private String profile_sidebar_border_color;
    private String profile_sidebar_fill_color;
    private String profile_text_color;
    private String profile_use_background_color;

    private boolean has_extended_profile;
    private boolean default_profile;
    private boolean default_profile_image;

    private boolean following;
    private boolean follow_request_sent;
    private boolean notifications;

    private String translator_type;

    private Entity entities;

    public String getName() {
        return name;
    }

    public String getScreen_name() {
        return "@" + screen_name;
    }

    public String getLocation() {
        return location;
    }

    public String getProfile_image_url(){
        return profile_image_url;
    }
    public String getDescription() { return description; }

    public String getProfile_banner_url() {
        return profile_banner_url;
    }

    public static User fromJSON(JSONObject jsonObject){
        User user = new User();
        try{
            user.name = jsonObject.getString("name");
            user.screen_name = jsonObject.getString("screen_name");
            user.location = jsonObject.getString("location");
            user.profile_image_url = jsonObject.getString("profile_image_url");
            user.description = jsonObject.getString("description");
            user.profile_banner_url = jsonObject.getString("profile_banner_url");

        }catch(JSONException e){
            e.printStackTrace();
        }
        return user;
    }

}
