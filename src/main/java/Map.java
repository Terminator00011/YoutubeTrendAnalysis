    /*
    A red-black tree maintains the following invariants:
    1. A node is either red or black
    2. The root is always black
    3. A red node always has black children (a null reference is considered to refer to a black node) Or No two consecutive red nodes
    4. The number of black nodes in any path from the root to a leaf is the same
    5. Null nodes are attached to the leaves and are black
    */
    //https://www.geeksforgeeks.org/insertion-in-red-black-tree/
    //https://www.youtube.com/watch?v=_Q-eNqTOxlE&t=1389s
    import java.math.BigInteger;
    import java.io.*;

    public class Map {
    private Node root;
    private boolean rightRotation;
    private boolean leftRotation;
    private boolean rightLeftRotation;
    private boolean leftRightRotation;

    public Map()
    {
        this.root = null;
        this.rightRotation = false;
        this.leftRotation = false;
        this.rightLeftRotation = false;
        this.leftRightRotation = false;
    }

    //insert key is videoID
    public void insert(String channelID, String channelTitle, String videoTitle, String publishDate, BigInteger views, String videoID, BigInteger likeCount, BigInteger dislikeCount)
    {
        //Empty tree case
        if (this.root == null)
        {
            this.root = new Node(channelID, channelTitle, videoTitle, publishDate, views, videoID, likeCount, dislikeCount);
            this.root.swapColor();
        }
        else
            this.root = helperInsert(this.root, channelID, channelTitle, videoTitle, publishDate, views, videoID, likeCount, dislikeCount);
    }

    private Node helperInsert(Node curr, String channelID, String channelTitle, String videoTitle, String publishDate, BigInteger views, String videoID, BigInteger likeCount, BigInteger dislikeCount)
    {
        boolean consecutiveRed = false;

        //Base case
        if (curr == null)
        {
            return new Node(channelID, channelTitle, videoTitle, publishDate, views, videoID, likeCount, dislikeCount);
        }
        //Right subtree
        else if (videoID.compareTo(curr.getVideoID()) > 0)
        {
            curr.right = helperInsert(curr.right, channelID, channelTitle, videoTitle, publishDate, views, videoID, likeCount, dislikeCount);
            curr.right.parent = curr;
            if (curr != this.root)
            {
                if (curr.red && curr.right.red)
                    consecutiveRed = true;
            }
        }
        //Left subtree
        else if (videoID.compareTo(curr.getVideoID()) < 0)
        {
            curr.left = helperInsert(curr.left, channelID, channelTitle, videoTitle, publishDate, views, videoID, likeCount, dislikeCount);
            curr.left.parent = curr;
            if (curr != this.root)
            {
                if (curr.red && curr.left.red)
                    consecutiveRed = true;
            }
        }

        //One of these if statements will execute in the stack frame of the parent node and will preform the correct rotation
        //Get the rotation info from the previous stack frame, reset to false after a rotation occurs
        if (rightRotation)
        {
            curr = rightRotation(curr);
            rightRotation = false;
        }
        else if (leftRotation)
        {
            curr = leftRotation(curr);
            leftRotation = false;
        }
        else if (leftRightRotation)
        {
            curr = leftRightRotation(curr);
            leftRightRotation = false;
        }
        else if (rightLeftRotation)
        {
            curr = rightLeftRotation(curr);
            rightLeftRotation = false;
        }

        //Check for consecutive red nodes
        if (consecutiveRed)
        {
            //check to see which child we are working with in order to choose the other one as the uncle
            //left child (right child is uncle)
            if(curr.parent.left == curr)
            {
                //black uncle case (rotations)
                if (curr.parent.right == null || !curr.parent.right.red)
                {
                    //left-left case: right rotation
                    if (curr.left != null && curr.left.red)
                    {
                        rightRotation = true;
                    }
                    //left-right case: left-right rotation
                    else if (curr.right != null && curr.right.red)
                    {
                        leftRightRotation = true;
                    }
                }
                //red uncle case (flip colors)
                else
                {
                    //swap both grandparents children to black and if the grandparent isn't the root then swap to red
                    curr.black();
                    curr.parent.right.black();
                    if (curr.parent != this.root)
                        curr.parent.red();
                }
            }
            //right child (left child is uncle)
            else
            {
                //black uncle case (rotations)
                if (curr.parent.left == null || !curr.parent.left.red)
                {
                    //right-left case: right-left rotation
                    if (curr.left != null && curr.left.red)
                    {
                        rightLeftRotation = true;
                    }
                    //right-right case: left rotation
                    else if (curr.right != null && curr.right.red)
                    {
                        leftRotation = true;
                    }
                }
                //red uncle case (flip colors)
                else
                {
                    //swap both grandparents children to black and if the grandparent isn't the root then swap to red
                    curr.black();
                    curr.parent.left.black();
                    if (curr.parent != this.root)
                        curr.parent.red();
                }
            }
        }


        return curr;
    }

    private Node leftRotation(Node curr)
    {
        Node temp = curr.right;
        curr.right = temp.left;
        temp.left = curr;
        curr.parent = temp;
        if (curr.right != null)
            curr.right.parent = curr;
        temp.black();
        temp.right.red();
        curr.red();
        return temp;
    }

    private Node rightRotation(Node curr)
    {
        Node temp = curr.left;
        curr.left = temp.right;
        temp.right = curr;
        curr.parent = temp;
        if (curr.left != null)
            curr.left.parent = curr;
        temp.black();
        temp.left.red();
        curr.red();
        return temp;
    }
    private Node rightLeftRotation(Node curr)
    {
        //Step 1
        Node temp = curr.right;
        curr.right = temp.left;
        temp.left = temp.left.right;
        curr.right.right = temp;
        //Step 2
        return leftRotation(curr);
    }
    private Node leftRightRotation(Node curr)
    {
        //Step 1
        Node temp = curr.left;
        curr.left = temp.right;
        temp.right = temp.right.left;
        curr.left.left = temp;
        //Step 2
        return rightRotation(curr);
    }

    public void inOrder(int arg)
    {
        this.root = helperInOrder(this.root, arg);
    }

    private Node helperInOrder(Node curr, int arg)
    {
        if (curr == null)
            return null;

        curr.left = helperInOrder(curr.left, arg);

        if(arg == 1)
            System.out.println(curr.getPublishDate());
        else if(arg == 2)
            System.out.println(curr.getViews());
        else if(arg == 3)
            System.out.println("Like count: " + curr.getLikeCount() + "\n" + "Dislike count: " + curr.getDislikeCount());
       
        curr.right = helperInOrder(curr.right, arg);

        return curr;
    }

    private void printDecider(Node curr, int arg)
    {
        
    }

}