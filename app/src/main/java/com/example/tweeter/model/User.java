package com.example.tweeter.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    private String id_str;

    private String name;
    private String screen_name;

    private String location;
    private String description;

    private int followers_count;
    private int friends_count;

    private String profile_image_url;
    private String profile_banner_url;

    private boolean following;
    private boolean follow_request_sent;
    private boolean notifications;

    private String translator_type;

    private Entity entities;

    public String getId_str() {
        return id_str;
    }

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

    public int getFollowers_count() {
        return followers_count;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public boolean isFollowing() {
        return following;
    }

    public String getProfile_banner_url() {
        return profile_banner_url;
    }

    public static User fromJSON(JSONObject jsonObject){
        User user = new User();
        try{
            user.id_str = jsonObject.getString("id_str");
            user.name = jsonObject.getString("name");
            user.screen_name = jsonObject.getString("screen_name");
            user.location = jsonObject.getString("location");
            user.profile_image_url = jsonObject.getString("profile_image_url");
            user.description = jsonObject.getString("description");
            user.profile_banner_url = jsonObject.getString("profile_banner_url");
            user.followers_count = jsonObject.getInt("followers_count");
            user.friends_count = jsonObject.getInt("friends_count");
            user.following = jsonObject.getBoolean("following");

        }catch(JSONException e){
            e.printStackTrace();
        }
        return user;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }
}
