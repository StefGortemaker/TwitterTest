package com.example.tweeter;

import java.util.ArrayList;
import java.util.List;

public class Dataprovider {

   public static List<Tweet> tweets = new ArrayList<>() ;

   private void addTweet(Tweet tweet){
       tweets.add(tweet);
   }
}
