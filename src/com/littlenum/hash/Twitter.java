package com.littlenum.hash;

import java.util.*;

/**
 * Created by hero on 2017/11/6.
 * 355. Design Twitter
 */
public class Twitter {
        class Tweet {
            int user;
            int id;

            public Tweet(int user, int id) {
                this.user = user;
                this.id = id;
            }
        }

        private List<Tweet> allTweetList = new ArrayList<>();
        //key follower
        private Map<Integer, Set<Integer>> follwer = new HashMap<>();
        private Map<Integer, List<Integer>> userOwnTweets = new HashMap<>();

        /**
         * Initialize your data structure here.
         */
        public Twitter() {

        }

        /**
         * Compose a new tweet.
         */
        public void postTweet(int userId, int tweetId) {
            Tweet t = new Tweet(userId, tweetId);
            allTweetList.add(t);
            int index = allTweetList.size() - 1;
            List<Integer> own = userOwnTweets.get(userId);
            if (own == null) {
                own = new ArrayList<>();
                userOwnTweets.put(userId, own);
            }
            own.add(index);
        }

        /**
         * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
         */
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> allList = new ArrayList<>();
            List<Integer> own = userOwnTweets.get(userId);
            if (own != null) {
                allList.addAll(own);
            }
            Set<Integer> myFollowee = follwer.get(userId);
            if (myFollowee != null && myFollowee.size() > 0) {
                for (int fol : myFollowee) {
                    List<Integer> their = userOwnTweets.get(fol);
                    if (their != null && their.size() > 0) {
                        allList.addAll(their);
                    }
                }
            }
            if (allList.size() > 0) {
                Collections.sort(allList);
                List<Integer> news = new ArrayList<>();
                for (int i = allList.size() - 1; i >= 0 && i >= allList.size() - 10; i--) {
                    news.add(allTweetList.get(allList.get(i)).id);
                }
                return news;
            }
            return new ArrayList<>();
        }

        /**
         * Follower follows a followee. If the operation is invalid, it should be a no-op.
         */
        public void follow(int followerId, int followeeId) {
            if (followeeId == followerId) {
                return;
            }
            Set<Integer> follwers = follwer.get(followerId);
            if (follwers == null) {
                follwers = new HashSet<>();
                follwer.put(followerId, follwers);
            }
            if (!follwers.contains(followeeId)) {
                follwers.add(followeeId);
            }
        }

        /**
         * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
         */
        public void unfollow(int followerId, int followeeId) {
            Set<Integer> follwers = follwer.get(followerId);
            if (follwers != null) {
                follwers.remove(followeeId);
            }
        }
}
