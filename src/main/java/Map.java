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

public class Map {
    private Node root;
    private boolean rightRotation;
    private boolean leftRotation;
    private boolean rightLeftRotation;
    private boolean leftRightRotation;
    private BigInteger size;
    private BigInteger viewTotal;
    private  BigInteger likeTotal;

    private Node maxView;
    private Node maxLike;

    public Map()
    {
        this.root = null;
        this.rightRotation = false;
        this.leftRotation = false;
        this.rightLeftRotation = false;
        this.leftRightRotation = false;
        this.viewTotal = BigInteger.ZERO;
        this.likeTotal = BigInteger.ZERO;
        this.size = BigInteger.ZERO;
        this.maxView = null;
        this.maxLike = null;
    }

    //insert key is videoID
    public void insert(String channelID, String channelTitle, String videoTitle, String publishDate, BigInteger views, String videoID, BigInteger likeCount, BigInteger dislikeCount)
    {
        //Empty tree case
        if (this.root == null)
        {
            if (views == null)
                views = BigInteger.ZERO;
            if (likeCount == null)
                likeCount = BigInteger.ZERO;
            this.root = new Node(channelID, channelTitle, videoTitle, publishDate, views, videoID, likeCount, dislikeCount);
            size = size.add(BigInteger.ONE);
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
            size = size.add(BigInteger.ONE);
            if (views == null)
                views = BigInteger.ZERO;
            if (likeCount == null)
                likeCount = BigInteger.ZERO;
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
            consecutiveRed = false;
        }
        else if (leftRotation)
        {
            curr = leftRotation(curr);
            leftRotation = false;
            consecutiveRed = false;

        }
        else if (leftRightRotation)
        {
            curr = leftRightRotation(curr);
            leftRightRotation = false;
            consecutiveRed = false;
        }
        else if (rightLeftRotation)
        {
            curr = rightLeftRotation(curr);
            rightLeftRotation = false;
            consecutiveRed = false;
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

    public void viewTotal()
    {
        this.root = helperViewTotal(this.root);
    }

    private Node helperViewTotal(Node curr)
    {
        if (curr == null)
            return null;
        //Initialize maxView node with root
        if (curr == this.root)
        {
            maxView = curr;
            if (maxView.getViews() == null)
                maxView.setViews(BigInteger.ZERO);
        }
        //Check for max value
        else
        {
            if (curr.getViews() != null)
            {
                if (curr.getViews().compareTo(maxView.getViews()) > 0)
                {
                    maxView = curr;
                }
            }
        }

        

        curr.left = helperViewTotal(curr.left);

        if (curr.getViews() != null)
            viewTotal = viewTotal.add(curr.getViews());

        curr.right = helperViewTotal(curr.right);

        return curr;
    }

    public void likeTotal()
    {
        this.root = helperLikeTotal(this.root);
    }

    private Node helperLikeTotal(Node curr)
    {
        if (curr == null)
            return null;
        //Initialize maxLike node with root
        if (curr == this.root)
        {
            maxLike = curr;
            if (maxLike.getLikeCount() == null)
                maxLike.setLikes(BigInteger.ZERO);
        }
        //Check for max value
        else {
            if (curr.getLikeCount() != null)
            {
                if (curr.getLikeCount().compareTo(maxLike.getLikeCount()) > 0)
                {
                    maxLike = curr;
                }
            }
        }

        curr.left = helperLikeTotal(curr.left);

        if (curr.getLikeCount() != null)
            likeTotal = likeTotal.add(curr.getLikeCount());

        curr.right = helperLikeTotal(curr.right);

        return curr;
    }

    private Node leftRotation(Node curr)
    {
        Node temp = curr.right;
        Node y = temp.left;
        temp.left = curr;
        curr.right = y;
        curr.parent = temp;
        if(y!=null)
            y.parent = curr;
        temp.black();
        temp.left.red();

        return temp;
    }

    private Node rightRotation(Node curr)
    {
        Node x = curr.left;
        Node y = x.right;
        x.right = curr;
        curr.left = y;
        curr.parent = x;
        if(y!=null)
            y.parent = curr;

        x.black();
        x.right.red();
        return x;
    }
    private Node rightLeftRotation(Node curr)
    {
        curr.right = rightRotation(curr.right);
        curr.right.parent = curr;
        curr = leftRotation(curr);
        curr.black();
        curr.red();
        return curr;
    }
    private Node leftRightRotation(Node curr)
    {
        curr.left = leftRotation(curr.left);
        curr.left.parent = curr;
        curr = rightRotation(curr);
        curr.black();
        curr.right.red();

        return curr;
    }

    public BigInteger getViewTotal() { return viewTotal; }

    public BigInteger getSize() { return size; }

    public Node getMaxView() { return maxView; }

    public BigInteger getLikeTotal() { return likeTotal; }

    public Node getMaxLike() { return maxLike; }
}