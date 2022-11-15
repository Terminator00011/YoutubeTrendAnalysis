public class Node 
{
    private String channelID;
    private String channelTitle;
    private String videoTitle;
    private String publishDate;
    private int views;
    private String videoID;

    public String getChannelID() { return channelID; }
    public String getChannelTitle() { return channelTitle; }
    public String getVideoTitle() { return videoTitle; }
    public String getPublishDate() { return publishDate; }
    public int getViews() { return views; }
    public String getVideoID() { return videoID; }


    public Node(String channelID, String channelTitle, String videoTitle, String publisDate, int views, String videoID)
    {
        this.channelID = channelID;
        this.channelTitle = channelTitle;
        this.videoTitle = videoTitle;
        this.publishDate = publisDate;
        this.views = views;
        this.videoID = videoID;
    }
    
}
