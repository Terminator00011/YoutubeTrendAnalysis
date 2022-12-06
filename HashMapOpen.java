// Referenced https://www.geeksforgeeks.org/method-class-hashcode-method-in-java/ to know how to use Java's hash function

import java.math.BigInteger;

public class HashMapOpen {

    Node[] hashMapOpen;
    int size;
    double loadFactor;
    boolean isLinearProbing;

    public HashMapOpen(boolean isLinearProbing)
    {
        this.hashMapOpen = new Node[16];
        this.size = 0;
        this.loadFactor = 0.75;
        this.isLinearProbing = isLinearProbing;
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
                hashMapOpen[index] = video;
                size++;
                available = true;
                System.out.println(counter - 1);
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

}
