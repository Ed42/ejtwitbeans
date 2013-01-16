package com.japp.controller;

import com.japp.domain.TokenizedTweet;
import com.japp.processor.TweetParser;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TweetController {

    private static org.slf4j.Logger sLogger = LoggerFactory.getLogger(TweetController.class);
    private final Twitter twitter;

    @Autowired
    public TweetController(Twitter twitter) {
        this.twitter = twitter;
    }

    @RequestMapping(value = "/tline", method = RequestMethod.GET)
    public String showTimeline(@RequestParam String timelineType, Model model) {

        sLogger.warn("Loading Twitter timeline for :" + timelineType);
        List<Tweet> results = queryForTweets(timelineType);

        model.addAttribute("timeline", results);
        model.addAttribute("timelineName", timelineType);
      
       return  "twitter/timeline";
    }

    private List<Tweet> queryForTweets(String screenName) {

        TimelineOperations timelineOps = twitter.timelineOperations();
        List<Tweet> results = timelineOps.getUserTimeline(screenName);
        sLogger.info("Fond Twitter timeline for :" + screenName + " adding " + results.size()
                + " tweets to model");
        return results;
    }
    
    
     @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String showSearch(@RequestParam String search, Model model) {

        sLogger.warn("Loading Twitter showSearch for : " + search);
        List<TokenizedTweet> results = searchForTweets(search);

        model.addAttribute("seResults", results);
        model.addAttribute("searchTerm", search);
       
       return  "twitter/search";
    }
    
    
       private List<TokenizedTweet> searchForTweets(String term) {

        SearchResults results = twitter.searchOperations().search(term);
        List<Tweet> tweets = results.getTweets();
        List<TokenizedTweet> tkTweets = new ArrayList<TokenizedTweet>();
       for (Tweet tweet : tweets) {
            TokenizedTweet tokenizedTweet = new TweetParser().parseTweet(tweet);
            tkTweets.add(tokenizedTweet);
            sLogger.warn("TweetController searchForTweets:  to [" + tokenizedTweet.getToUserId() + " ] : text data  [ " + tokenizedTweet.toString() + " ]");
        }
        return tkTweets;
    }
    
    
    
}
