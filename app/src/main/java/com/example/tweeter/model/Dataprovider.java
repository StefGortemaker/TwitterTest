package com.example.tweeter.model;

import java.util.ArrayList;
import java.util.List;

public class Dataprovider {

   public static List<Tweet> tweets = new ArrayList<>() ;
   public static List<User> users = new ArrayList<>() ;
   public static List<Entity> entities = new ArrayList<>();
   public static ArrayList<Hashtag> hashtags = new ArrayList<>();
   public static ArrayList<Symbol> symbols = new ArrayList<>();
   public static ArrayList<UserMention> userMentions = new ArrayList<>();
   public static ArrayList<Url> urls = new ArrayList<>();
   public static ArrayList<Media> media = new ArrayList<>();

   //signed in user
   public static User signedInuUser;

}
