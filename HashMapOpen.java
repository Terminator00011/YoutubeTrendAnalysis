// Referenced https://www.geeksforgeeks.org/method-class-hashcode-method-in-java/

import java.math.BigInteger;

public class HashMapOpen {

    Node[] hashMapOpen;
    int size;
    double loadFactor;
    boolean isLinearProbing;

    private BigInteger viewTotal;
    private  BigInteger likeTotal;
    private Node maxView;
    private Node maxLike;

    public HashMapOpen(boolean isLinearProbing)
    {
        this.hashMapOpen = new Node[16];
        this.size = 0;
        this.loadFactor = 0.75;
        this.isLinearProbing = isLinearProbing;
        this.viewTotal = BigInteger.ZERO;
        this.likeTotal = BigInteger.ZERO;
        this.maxView = null;
        this.maxLike = null;
    }

    public void insert(String channelID, String channelTitle, String videoTitle, String publishDate, BigInteger views, String videoID, BigInteger likeCount, BigInteger dislikeCount)
    {

        //check for load factor size / hashMap.length == loadFactor
        if ((double)size / (double)hashMapOpen.length == loadFactor)
        {
            reSize();
        }

        //get index from hash function and mod it by hashMap.length
        int index = videoID.hashCode() % hashMapOpen.length;

        boolean available = false;
        int counter = 1;

        //Check if there is a node already at the index
        while (!available) {
            // Check if there is a collision
            if (hashMapOpen[index] != null) {

                if (isLinearProbing) {
                    index++;
                }
                else {
                    index += (counter * counter);
                    counter++;
                }

                boolean inBounds = false;

                while (!inBounds) {
                    if (index > hashMapOpen.length - 1) {
                        if (isLinearProbing)
                            index = 0;
                        else
                            index = index - hashMapOpen.length;
                    }
                    if (index < hashMapOpen.length)
                        inBounds = true;
                }
            }
            else {
                //Insert and increase size by 1
                Node video = new Node(channelID, channelTitle, videoTitle, publishDate, views, videoID, likeCount, dislikeCount);

                if (video.getViews() == null)
                    video.setViews(BigInteger.ZERO);
                if (video.getLikeCount() == null)
                    video.setLikes(BigInteger.ZERO);

                hashMapOpen[index] = video;
                size++;
                available = true;
            }
        }

    }

    private void reSize()
    {
        Node[] temp = hashMapOpen;
        hashMapOpen = new Node[hashMapOpen.length * 2];

        for (int i = 0; i < temp.length; i++)
        {
            hashMapOpen[i] = temp[i];
        }
    }

    public void viewAndLikeTotalHMO()
    {
        for (int i = 0; i < hashMapOpen.length; i++)
        {
            if (hashMapOpen[i] != null)
            {
                if(maxLike == null && maxView == null)
                {
                    maxView = hashMapOpen[i];
                    maxLike = hashMapOpen[i];
                }
                else
                {
                    if (hashMapOpen[i].getViews().compareTo(maxView.getViews()) > 0)
                        maxView = hashMapOpen[i];
                    if (hashMapOpen[i].getLikeCount().compareTo(maxLike.getLikeCount()) > 0)
                        maxLike = hashMapOpen[i];
                }

                if (hashMapOpen[i] != null)
                {
                    viewTotal = viewTotal.add(hashMapOpen[i].getViews());
                    likeTotal = likeTotal.add(hashMapOpen[i].getLikeCount());
                }
            }
        }
    }

    public int getTotalSize() { return size; }
    public BigInteger getViewTotal() { return viewTotal; }
    public BigInteger getLikeTotal() { return likeTotal; }
    public Node getMaxView() { return maxView; }
    public Node getMaxLike() { return maxLike; }

}
