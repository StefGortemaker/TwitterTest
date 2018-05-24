package com.example.tweeter.model;

import java.util.ArrayList;

public class Entity {

    private ArrayList<hashtag> hashtags;
    private ArrayList<symbol> symbols;
    private ArrayList<userMention> userMentions;
    private ArrayList<Url> urls;
    private ArrayList<Media> media;

    public class hashtag{

        private String text;

        private ArrayList<Integer> indices;
    }

    public class symbol{

    }

    public class userMention{

        private String screen_name;
        private String name;
        private int id;
        private String id_str;

        private ArrayList<Integer> indices;
    }

    public class Url{

        private String url;
        private String expanded_url;
        private String display_url;

        private ArrayList<Integer> indices;
    }

    public class Media{

    }
}
