package com.japp;

import com.japp.processor.TweetParser;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import org.springframework.social.twitter.api.Tweet;

public class TweetParserTest {
    @Test
    public void tokenize() {
        String text = "Twitter is an online social networking service and microblogging service" //
                + " that enables its users to send and read text-based posts of up to 140 characters," //
                + " known as \"tweets\". You can signup and test it yourself.";
        Map<String, Integer> tokenMap = new TweetParser().tokenize(text);
        
        assertEquals(tokenMap.size(),  25);
        
        List<Integer > list1 = new  ArrayList<Integer>();
        List<Integer > list2 = new  ArrayList<Integer>();
         List<Integer > list3 = new  ArrayList<Integer>();
        
          for (Entry<String, Integer> e : tokenMap.entrySet()) {
              if (e.getKey().equals("microblogging")) list1.add(e.getValue());
              if (e.getKey().equals("and")) list2.add(e.getValue());
              if (e.getKey().equals("unknown")) list3.add(e.getValue());
            System.out.println(e.getKey() + ": " + e.getValue());
        }
        assertTrue(list1.get(0) == 1);
        assertTrue(list2.get(0)==  3);
        
        assertTrue(list3.size()==  0);
        
        

 
    }
}