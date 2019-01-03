/** 
 * Name: Kenneth Shinn
 * PennKey: kshinn
 * Recitation: 215
 * 
 * Execution: new OrderedPair(int x, int y)
 * 
 * This class creates an ordered pair of ints
 */
public class OrderedPair {
    // fields
    private int x;
    private int y;
    
    /**
     * Description: the constructor for the OrderedPair class
     * Input: an int for x, and an int for y
     * Output void
     */
    public OrderedPair(int x, int y) {
        // set the x and y
        this.x = x;
        this.y = y;
    }
    
    /**
     * Description: setter for x
     * Input: an int for x
     * Output void
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Description: setter for y
     * Input: an int for y
     * Output void
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Description: getter for x
     * Input: void
     * Output int for x
     */
    public int getX() {
        return x;
    }
    
    /**
     * Description: getter for y
     * Input: void
     * Output int for y
     */
    public int getY() {
        return y;
    }
}