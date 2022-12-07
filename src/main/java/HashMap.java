// Referenced https://www.geeksforgeeks.org/method-class-hashcode-method-in-java/

import java.math.BigInteger;

public class HashMap {


    Map[] hashMap;
    int size;
    double loadFactor;

    private BigInteger totalSize;
    private BigInteger viewTotal;
    private  BigInteger likeTotal;
    private Node maxView;
    private Node maxLike;

    public HashMap()
    {
        this.hashMap = new Map[16];
        this.size = 0;
        this.loadFactor = 0.75;
        this.totalSize = BigInteger.ZERO;
        this.viewTotal = BigInteger.ZERO;
        this.likeTotal = BigInteger.ZERO;
        this.maxView = null;
        this.maxLike = null;
    }

    public void insert(String channelID, String channelTitle, String videoTitle, String publishDate, BigInteger views, String videoID, BigInteger likeCount, BigInteger dislikeCount)
    {

        //check for load factor size / hashMap.length == loadFactor
        if ((double)size / (double)hashMap.length == loadFactor)
        {
            reSize();
        }

        //get index from hash function and mod it by hashMap.length
        int index = Math.abs(videoID.hashCode()) % hashMap.length;

        //check if hashMap[index] is empty, insert and increase size by 1
        if (hashMap[index] == null)
        {
            hashMap[index] = new Map();
        }

        //Insert into already existing RB tree and increase size by 1
        hashMap[index].insert(channelID, channelTitle, videoTitle, publishDate, views, videoID, likeCount, dislikeCount);
        size++;
    }

    private void reSize()
    {
        Map[] temp = hashMap;
        hashMap = new Map[hashMap.length * 2];

        for (int i = 0; i < temp.length; i++)
        {
            hashMap[i] = temp[i];
        }
    }

    public void viewAndLikeTotalHM()
    {
        for (int i = 0; i < hashMap.length; i++)
        {
            if (hashMap[i] != null)
            {
                //Calculates every element's view total and highest viewed video
                hashMap[i].viewTotal();
                //Calculates every element's like total and highest liked video
                hashMap[i].likeTotal();

                //Initialize maxView and maxLike with first element
                if (maxLike == null && maxView == null)
                {
                    maxView = hashMap[i].getMaxView();
                    maxLike = hashMap[i].getMaxLike();
                }
                //Compare and update max if necessary
                else
                {
                    if (hashMap[i].getMaxView().getViews().compareTo(maxView.getViews()) > 0)
                        maxView = hashMap[i].getMaxView();
                    if (hashMap[i].getMaxView().getLikeCount().compareTo(maxLike.getLikeCount()) > 0)
                        maxLike = hashMap[i].getMaxLike();
                }

                viewTotal = viewTotal.add(hashMap[i].getViewTotal());
                totalSize = totalSize.add(hashMap[i].getSize());
                likeTotal = likeTotal.add(hashMap[i].getLikeTotal());
            }
        }
    }

    public BigInteger getTotalSize() { return totalSize; }

    public BigInteger getViewTotal() { return viewTotal; }

    public BigInteger getLikeTotal() { return likeTotal; }

    public Node getMaxView() { return maxView; }

    public Node getMaxLike() { return maxLike; }

    public void printHashMap(int arg)
    {
        for (int i = 0; i < hashMap.length; i++)
        {
            if(hashMap[i] != null)
                hashMap[i].inOrder(arg);
        }
    }
}
