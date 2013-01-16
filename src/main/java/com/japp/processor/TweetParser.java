package com.japp.processor;

import com.japp.controller.AdminController;
import com.japp.domain.TokenizedTweet;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.Tweet;

public class TweetParser {
    
    private static final int MIN_TOKEN_LENGTH = 3;
    
    private static org.slf4j.Logger sLogger = LoggerFactory.getLogger(AdminController.class);
    
    
       public TokenizedTweet parseTweet( Tweet tweet) {
        sLogger.info("parsing tweet " + tweet);

        Long id = (Long) tweet.getId();
        String text = tweet.getText();
        if (text != null) {
            TokenizedTweet tkTweet = new TokenizedTweet( tokenize(text), tweet.getId(), 
                                            tweet.getCreatedAt(), tweet.getFromUserId(), tweet.getFromUser());
            tkTweet.setFiltered( true);
            return tkTweet;
                }
        return null;
    }

    public Map<String, Integer> tokenize(String text) {
        Map<String, Integer> tokenMap = new HashMap<String, Integer>();
        StringTokenizer st = new StringTokenizer(text, "\"{}[]:;|<>?`'.,/~!@#$%^&*()_-+= \t\n\r\f\\");

        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.length() < MIN_TOKEN_LENGTH) {
                continue;
            }
            Integer count = tokenMap.containsKey(token) ? tokenMap.get(token) + 1 : 1;
            tokenMap.put(token, count);
        }
        return tokenMap;
    }
    
    
}
