package com.example.tweeter.model;

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


    //getters
    public int getId() {
        return id;
    }

    public String getId_str() {
        return id_str;
    }

    public String getName() {
        return name;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public boolean isProtect() {
        return protect;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public int getListed_count() {
        return listed_count;
    }

    public int getFavourites_count() {
        return favourites_count;
    }

    public boolean isVerified() {
        return verified;
    }

    public int getStatuses_count() {
        return statuses_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public int getUtc_offset() {
        return utc_offset;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public boolean isGeo_enabled() {
        return geo_enabled;
    }

    public String getLang() {
        return lang;
    }

    public boolean isContributors_enabled() {
        return contributors_enabled;
    }

    public boolean isIs_translator() {
        return is_translator;
    }

    public boolean isIs_translation_enabled() {
        return is_translation_enabled;
    }

    public String getProfile_background_image_url() {
        return profile_background_image_url;
    }

    public String getProfile_background_image_url_https() {
        return profile_background_image_url_https;
    }

    public boolean isProfile_background_title() {
        return profile_background_title;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public String getProfile_image_url_https() {
        return profile_image_url_https;
    }

    public String getProfile_banner_url() {
        return profile_banner_url;
    }

    public String getProfile_link_color() {
        return profile_link_color;
    }

    public String getProfile_sidebar_border_color() {
        return profile_sidebar_border_color;
    }

    public String getProfile_sidebar_fill_color() {
        return profile_sidebar_fill_color;
    }

    public String getProfile_text_color() {
        return profile_text_color;
    }

    public String getProfile_use_background_color() {
        return profile_use_background_color;
    }

    public boolean isHas_extended_profile() {
        return has_extended_profile;
    }

    public boolean isDefault_profile() {
        return default_profile;
    }

    public boolean isDefault_profile_image() {
        return default_profile_image;
    }

    public boolean isFollowing() {
        return following;
    }

    public boolean isFollow_request_sent() {
        return follow_request_sent;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public String getTranslator_type() {
        return translator_type;
    }

    public Entity getEntities() {
        return entities;
    }
}
