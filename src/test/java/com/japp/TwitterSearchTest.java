package com.japp;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.junit.Rule;
import org.junit.rules.TestName;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:/WacTests-context.xml")
public class TwitterSearchTest {

    private static org.slf4j.Logger sLogger = LoggerFactory.getLogger(EtwitterUserDetailsServiceTest.class);
    private static final String screenName = "nsavage";
    private final TwitterTemplate twitterTemplate = new TwitterTemplate();
    @Autowired
    private Twitter twitter;
    @Autowired
    private WebApplicationContext wac;
    @Rule
    public TestName name = new TestName();
    private MockMvc mockMvc;
    private SessionHolder sessionHolder = new SessionHolder();

    @Test
    public void testTwitterFriends() {
        sLogger.debug("Running '{}'...", name.getMethodName());

        List<TwitterProfile> friends =
                twitterTemplate.friendOperations().getFriends(screenName);

        assertNotNull(friends);
        assertTrue(friends.size() == 0);

        sLogger.debug(screenName + "'s has friends: "+friends.size());
     
    }

    @Test
    public void testTwitterTimeLine() {

        List<Tweet> results = queryForTweets(screenName);
        assertTrue(results.size() > 0);
    }

    private List<Tweet> queryForTweets(String screenName) {

        TimelineOperations timelineOps = twitter.timelineOperations();
        List<Tweet> results = timelineOps.getUserTimeline(screenName);
        sLogger.info("Fond Twitter timeline for :" + screenName + " adding " + results.size()
                + " tweets to model");
        return results;
    }

 
    private static final class SessionHolder {

        private SessionWrapper session;

        public SessionWrapper getSession() {
            return session;
        }

        public void setSession(SessionWrapper session) {
            this.session = session;
        }
    }

    private static class SessionWrapper extends MockHttpSession {

        private final HttpSession httpSession;

        public SessionWrapper(HttpSession httpSession) {
            this.httpSession = httpSession;
        }

        @Override
        public Object getAttribute(String name) {
            return this.httpSession.getAttribute(name);
        }
    }
}