import java.util.*;

/**
 *
 *  思路
 *  1. 关注关系用Map<UserId, Set<UserId>>，因为用不到关注关系的顺序，为了更快的unfollow，这里使用set
 *  2. 因为推文要按时间顺序由近到远排列，用ArrayList<TweetId>，每来一条，就插入一条，索引是顺序，元素是tweetId，这里用ArrayList是要用索引去数
 *  3. 再用一个Map<UserId, ArrayList<Index>>存用户的推文，其中LinkedList里存的是2里面索引。合并多个用户的推文，就是合并多个链表，最后再从ArrayList里取tweetId
 * @author liji
 * @date 2021/12/12
 */
public class DesignTwitter355 {
    private Map<Integer, Set<Integer>> relation;
    private List<Integer> tweets;
    private Map<Integer, List<Integer>> userTweetIndexMap;

    public DesignTwitter355() {
        this.relation = new HashMap<>();
        tweets = new ArrayList<>();
        userTweetIndexMap = new HashMap<>();
    }

    /**
     * 时间：O(1)
     * 空间：整体O(N)
     * @param userId
     * @param tweetId
     */
    public void postTweet(int userId, int tweetId) {
        tweets.add(tweetId);
        userTweetIndexMap.computeIfAbsent(userId, key -> new ArrayList<>()).add(tweets.size() - 1);
    }

    /**
     * 关注数设为N，每人发推数K
     * 时间：O(lgN)
     * @param userId
     * @return
     */
    public List<Integer> getNewsFeed(int userId) {
        //首先拿到关注人
        Set<Integer> followees = new HashSet<>();
        followees.add(userId);
        Set<Integer> relations = relation.getOrDefault(userId, new HashSet<>());
        followees.addAll(relations);
        List<List<Integer>> tweetIndexList = new ArrayList<>();
        for (Integer uid : followees) {
            if (userTweetIndexMap.get(uid) != null) {
                tweetIndexList.add(userTweetIndexMap.get(uid));
            }
        }
        List<Integer> top10TweetsIndex = getTop10(tweetIndexList);
        List<Integer> tweets = new ArrayList<>();
        for(Integer tweetIndex : top10TweetsIndex) {
            tweets.add(this.tweets.get(tweetIndex));
        }
        return tweets;
    }

    private List<Integer> getTop10(List<List<Integer>> tweetIndexList) {
        //大顶堆
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((a, b) -> b.key - a.key);
        int[] indexs = new int[tweetIndexList.size()];
        for (int i = 0; i < tweetIndexList.size(); i++) {
            List<Integer> tweetIndex = tweetIndexList.get(i);
            //tweetIndex里是按时间从远到近顺序排列的，因为要取最近的10条，因此从后面开始取
            int key = tweetIndex.get(tweetIndex.size() - 1);
            indexs[i] = tweetIndex.size() - 1;
            priorityQueue.add(new Node(key, i));
        }
        while (res.size() < 10 && priorityQueue.peek() != null) {
            Node max = priorityQueue.poll();
            res.add(max.key);
            List<Integer> tweetIndex = tweetIndexList.get(max.index);
            if (indexs[max.index] > 0) {
                int key = tweetIndex.get(indexs[max.index] - 1);
                indexs[max.index] = indexs[max.index] -  1;
                priorityQueue.add(new Node(key, max.index));
            }
        }
        return res;
    }

    private static class Node {
        private int key;
        //tweetIndexList的索引
        private int index;

        Node(int key, int index) {
            this.key = key;
            this.index = index;
        }
    }

    /**
     * 时间：O(1)
     * 空间：整体O(N)
     * @param followerId
     * @param followeeId
     */
    public void follow(int followerId, int followeeId) {
        relation.computeIfAbsent(followerId, key -> new HashSet<>()).add(followeeId);
    }

    /**
     * 时间：O(1)
     * 空间：整体O(N)
     * @param followerId
     * @param followeeId
     */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> set = relation.get(followerId);
        if (set != null) {
            set.remove(followeeId);
        }
    }
}
