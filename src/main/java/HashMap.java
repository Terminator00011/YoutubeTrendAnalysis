// Referenced https://www.geeksforgeeks.org/method-class-hashcode-method-in-java/

import java.math.BigInteger;

public class HashMap {


    Map[] hashMap;
    int size;
    double loadFactor;

    public HashMap()
    {
        this.hashMap = new Map[16];
        this.size = 0;
        this.loadFactor = 0.75;
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

    public void printHashMap(int arg)
    {
        for (int i = 0; i < hashMap.length; i++)
        {
            if(hashMap[i] != null)
                hashMap[i].inOrder(arg);
        }
    }
}
