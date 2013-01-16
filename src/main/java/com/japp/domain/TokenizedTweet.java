package com.japp.domain;

import java.util.Date;
import java.util.Map;
import org.springframework.data.neo4j.annotation.GraphId;

public class TokenizedTweet {

    @GraphId
    private Long nodeId;
    private Map<String, Integer> tokenMap;
    private Boolean filtered;
    private Long tweetId;
    private Date createdAt;
    private Long fromUserId;
    private String toUserId;
    private String wordList;
    // to many nulls
    // private Integer reTweetCount;
    
    
    public TokenizedTweet() {
    }

    public TokenizedTweet(Map<String, Integer> tokenMap, Long tweetId , Date createdAt, Long fromUserId, String toUserId ) {

        this.tokenMap = tokenMap;
        this.filtered = false;
        this.tweetId  = tweetId;
        this.createdAt  = createdAt;
        this.fromUserId  = fromUserId;
        this.toUserId   = toUserId;
      
    }

    public Boolean getFiltered() {
        return filtered;
    }

    public void setFiltered(Boolean filtered) {
        this.filtered = filtered;
    }

    public Map<String, Integer> getTokenMap() {
        return tokenMap;
    }

    public void setTokenMap(Map<String, Integer> tokenMap) {
        this.tokenMap = tokenMap;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }
    
    @Override
    public String toString() {
        return "TokenizedTweet [ tokenMap=" + tokenMap + "]";
    }
    
     
    public String getWordList() {
        wordList = "[ tweet words: " + tokenMap + "]";
        return  wordList;
    }
    
}
