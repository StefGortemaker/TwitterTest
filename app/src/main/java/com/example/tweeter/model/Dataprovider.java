package com.example.tweeter.model;

import java.util.ArrayList;

public class Dataprovider {

   private ArrayList<Tweet> tweets = new ArrayList<>() ;

   private void addTweet(Tweet tweet){
       tweets.add(tweet);
   }
}
