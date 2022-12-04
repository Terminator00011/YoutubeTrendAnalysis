import java.math.BigInteger;

public class Node 
{
    private String channelID;

    private String channelTitle;
    private String videoTitle;
    private String publishDate;
    private BigInteger views;

    //MAP KEY
    private String videoID;

    //Value
    private BigInteger likeCount;
    private BigInteger dislikeCount;

    private Node left = null;
    private Node right = null;

    public String getChannelID() { return channelID; }
    public String getChannelTitle() { return channelTitle; }
    public String getVideoTitle() { return videoTitle; }
    public String getPublishDate() { return publishDate; }
    public BigInteger getViews() { return views; }
    public String getVideoID() { return videoID; }
    public BigInteger getLikeCount() { return likeCount; }
    public BigInteger getDislikeCount() { return dislikeCount; }

    public Node(String channelID, String channelTitle, String videoTitle, String publisDate, BigInteger views, String videoID, BigInteger likeCount, BigInteger dislikeCount)
    {
        this.channelID = channelID;
        this.channelTitle = channelTitle;
        this.videoTitle = videoTitle;
        this.publishDate = publisDate;
        this.views = views;
        this.videoID = videoID;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount; 
    }
}