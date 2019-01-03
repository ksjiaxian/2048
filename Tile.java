/** 
 * Name: Kenneth Shinn
 * PennKey: kshinn
 * Recitation: 215
 * 
 * Execution: new Tile(double x, double y)
 * 
 * This class creates a tile in the game 2048. These tiles can move and collide
 * with other tiles to create a new tile with twice the number
 */
public class Tile {
    // fields
    private int num; // num of the tile
    private double x; // x coor of the tile
    private double y; // y coor of the tile
    private static int highestNum = 0; // num of the highest tile
    
    /**
     * Description: a constructor for the Tile class
     * Input: double x coordinate and double y coordinate
     * Output void
     */
    public Tile(double x, double y) {
        // make sure the x and y coordinates are valid
        if (x > 1.0 || x < 0.0) {
            throw new IllegalArgumentException("x-coordinate out of bounds");
        }
        if (y > 1.0 || y < 0.0) {
            throw new IllegalArgumentException("y-coordinate out of bounds");
        }
        
        // set the x and y coordinates
        this.x = x;
        this.y = y;
        
        // set the num of the tile as randomly as either 2 or 4
        if (Math.random() < .85) {
            num = 2;
            // set the highest num as this number is there's nothing higher
            if (highestNum < 2) {
                highestNum = 2;
            }
        } else {
            num = 4;
            // set the highest num as this number is there's nothing higher
            if (highestNum < 4) {
                highestNum = 4;
            }
        }
    }
    
    /**
     * Description: display the winning message and the number of moves made
     * Input: the amount of moves made
     * Output void
     */
    public static void win(int moves) {
        // draw background for the message
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledRectangle(.5, .5, .4, .2);
        
        // draw the winning message
        PennDraw.setPenColor(PennDraw.GREEN);
        PennDraw.setFontBold();
        PennDraw.setFontSize(70);
        PennDraw.text(.5, .6, "You Won!");
        
        // draw the number of moves made
        PennDraw.setFontPlain();
        PennDraw.setFontSize(40);
        PennDraw.text(.5, .35, "Moves made: " + moves);
    }
    
    /**
     * Description: display the losing message and the number of moves made
     * Input: the amount of moves made
     * Output void
     */
    public static void lose(int moves) {
        // draw background for the message
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledRectangle(.5, .5, .4, .2);
        
        // draw the winning message
        PennDraw.setPenColor(PennDraw.RED);
        PennDraw.setFontBold();
        PennDraw.setFontSize(70);
        PennDraw.text(.5, .6, "You Lost!");
        
        // draw the number of moves made
        PennDraw.setFontPlain();
        PennDraw.setFontSize(40);
        PennDraw.text(.5, .35, "Moves made: " + moves);
    }
    
    /**
     * Description: combine two tiles when they collide
     * Input: another tile
     * Output void
     */    
    public void combine(Tile t) {
        // combine the two tiles if they have the same number
        if (num == t.getNum()) { 
            num = num + t.getNum();
            
            // set the highest num as this number is there's nothing higher
            if (highestNum < num) {
                highestNum = num;
            }
        }
    }
    
    /**
     * Description: draw the tile
     * Input: void
     * Output void
     */
    public void draw() {        
        // change color depending on the value of the tile
        switch(num) {
            case 2: PennDraw.setPenColor(239, 230, 221);
            break;
            case 4: PennDraw.setPenColor(236, 224, 202);
            break;
            case 8: PennDraw.setPenColor(242, 177, 119);
            break;
            case 16: PennDraw.setPenColor(243, 149, 103);
            break;
            case 32: PennDraw.setPenColor(245, 124, 95);
            break;
            case 64: PennDraw.setPenColor(249, 92, 57);
            break;
            case 128: PennDraw.setPenColor(236, 208, 109);
            break;
            case 256: PennDraw.setPenColor(237, 204, 97);
            break;
            case 512: PennDraw.setPenColor(239, 198, 81);
            break;
            case 1024: PennDraw.setPenColor(235, 195, 65);
            break;
            case 2048: PennDraw.setPenColor(241, 194, 46);
            break;
            default: PennDraw.setPenColor(PennDraw.WHITE);
        }
        // draw a square with the number printed on top
        PennDraw.filledSquare(x, y, .120);
        
        // draw the num
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setFontSize(40);
        PennDraw.text(x, y, "" + num);
    }
    
    /**
     * Description: moves the tile's location up by one space
     * Input: void
     * Output void
     */
    public void moveUp() {
        y += 0.25;
    }
    
    /**
     * Description: moves the tile's location down by one space
     * Input: void
     * Output void
     */
    public void moveDown() {
        y -= 0.25;
    }
    
    /**
     * Description: moves the tile's location right by one space
     * Input: void
     * Output void
     */
    public void moveRight() {
        x += 0.25;
    }
    
    /**
     * Description: moves the tile's location left by one space
     * Input: void
     * Output void
     */
    public void moveLeft() {
        x -= 0.25;
    }
    
    /**
     * Description: a getter of a tile's x coordinate
     * Input: void
     * Output a double representing the tile's x coordinate
     */
    public double getX() {
        return x;
    }
    
    /**
     * Description: a getter of a tile's y coordinate
     * Input: void
     * Output a double representing the tile's y coordinate
     */
    public double getY() {
        return y;
    }
    
    /**
     * Description: a getter of a tile's num
     * Input: void
     * Output an int representing the tile's num
     */
    public int getNum() {
        return num;
    }
    
    /**
     * Description: a static getter of the highest num of a tile
     * Input: void
     * Output an int representing the highest num of a tile
     */
    public static int getHighestNum() {
        return highestNum;
    }
}