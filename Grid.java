/** 
 * Name: Kenneth Shinn
 * PennKey: kshinn
 * Recitation: 215
 * 
 * Execution: new Grid()
 * 
 * This class creates a 4x4 grid for the 2048 game, and it handles the movement
 * and collision of the tiles
 */
public class Grid {
    // fields
    public enum Direction { UP, DOWN, LEFT, RIGHT };
    private Tile[][] grid; // grid represented by a 2D Tile array
    private int numOfTiles = 0; // tile counter
    private int numOfMoves = 0; //to keep track of the number of moves
    private boolean gameOver = false;
    
    /**
     * Description: the constructor for the Tile class
     * Input: void
     * Output void
     */
    public Grid() {
        // create new 4x4 2D array to represent grid
        grid = new Tile[4][4];
        
        // get 2 random open spots, put tile there
        placeNewTile();
        placeNewTile();
    }
    
    /**
     * Description: draw the grid
     * Input: void
     * Output void
     */
    public void draw() {
        // do not draw if the grid has not been initialized
        if (grid == null) {
            return;
        }
        
        // clear the screen
        PennDraw.clear(204, 192, 178);
        
        // draw the grid lines
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setPenRadius(.01);
        for (int i = 0; i <= 4; i++) {
            PennDraw.line(0, i * .25, 1, i * .25);
        }
        for (int i = 0; i <= 4; i++) {
            PennDraw.line(i * .25, 0, i * .25, 1);
        }
        
        // go through all the spots, draw the tiles
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null) {
                    // draw
                    grid[i][j].draw();
                }
            }
        }
    }
    
    /**
     * Description: move the tiles as indicated by direction
     * Input: a direction
     * Output void
     */
    public void move(Direction d) {
        
        // create a boolean to see if there is a merge
        boolean hasMerged = false;
        
        // shift and collide different directions depending on input direction
        if (d == Direction.UP) {
            // go through every column and merge up
            for (int i = 0; i < grid.length; i++) {
                for (int j = grid[i].length - 1; j >= 0; j--) {
                    // make sure that only one tile is merged at a time
                    // if the tile underneath is the same number, merge
                    if (canMerge(i, j, 1, 0)) {
                        merge(i, j, 1, 0);
                        // there has been a shift
                        hasMerged = true;
                    }
                    // if tile 2 spaces under and empty between, merge
                    else if (canMerge(i, j, 2, 0)) {
                        merge(i, j, 2, 0);
                        // there has been a shift
                        hasMerged = true;
                    }
                    // if tile 3 spaces underneath and empty between, merge
                    else if (canMerge(i, j, 3, 0)) {
                        merge(i, j, 3, 0);
                        // there has been a shift
                        hasMerged = true;
                    }
                } 
            }
        } else if (d == Direction.DOWN) {
            // go through every column and merge down
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    // make sure that only one tile is merged at a time
                    // if the tile above is the same number, merge
                    if (canMerge(i, j, -1, 0)) {
                        merge(i, j, -1, 0);
                        // there has been a shift
                        hasMerged = true;
                    }
                    // if tile 2 spaces above and empty between, merge
                    else if (canMerge(i, j, -2, 0)) {
                        merge(i, j, -2, 0);
                        // there has been a shift
                        hasMerged = true;
                    }
                    // if tile 3 spaces above and empty between, merge
                    else if (canMerge(i, j, -3, 0)) {
                        merge(i, j, -3, 0);
                        // there has been a shift
                        hasMerged = true;
                    }
                }
            }
        } else if (d == Direction.RIGHT) {
            // go through every column and merge right
            for (int i = 0; i < grid.length; i++) {
                for (int j = grid[i].length - 1; j >= 0; j--) {
                    // make sure that only one tile is merged at a time
                    // if the tile left is the same number, merge
                    if (canMerge(j, i, 0, 1)) {
                        merge(j, i, 0, 1);
                        // there has been a shift
                        hasMerged = true;
                    }
                    // if tile 2 spaces left and empty between, merge
                    else if (canMerge(j, i, 0, 2)) {
                        merge(j, i, 0, 2);
                        // there has been a shift
                        hasMerged = true;
                    }
                    // if tile 3 spaces left and empty between, merge
                    else if (canMerge(j, i, 0, 3)) {
                        merge(j, i, 0, 3);
                        // there has been a shift
                        hasMerged = true;
                    }
                }
            }
        } else if (d == Direction.LEFT) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    // make sure that only one tile is merged at a time
                    // if the tile left is the same number, merge
                    if (canMerge(j, i, 0, -1)) {
                        merge(j, i, 0, -1);
                        // there has been a shift
                        hasMerged = true;
                    }
                    // if tile 2 spaces left and empty between, merge
                    else if (canMerge(j, i, 0, -2)) {
                        merge(j, i, 0, -2);
                        // there has been a shift
                        hasMerged = true;
                    }
                    // if tile 3 spaces left and empty between, merge
                    else if (canMerge(j, i, 0, -3)) {
                        merge(j, i, 0, -3);
                        // there has been a shift
                        hasMerged = true;
                    }
                }
            }
        }
        
        // shift the tiles in direction indicated, see if tiles have shifted
        boolean hasShifted = shift(d);
        
        // add one to the number of moves 
        numOfMoves++;
        
        // only generate a new tile if there is a shift or a merge
        if (hasShifted || hasMerged) {
            // place new tile
            placeNewTile();
        }
        
        // draw the grid after moving and placing the tiles
        draw();
        
        // see if the player has lost or not
        if (numOfTiles == 16 && !movePossible()) {
            
            // lose the game
            Tile.lose(numOfMoves);
            
            // it is game over
            gameOver = true;
            
        }
        // see if the player has won
        else if (Tile.getHighestNum() == 2048) {
            
            // win the game
            Tile.win(numOfMoves);
            
            // it is game over
            gameOver = true;
        }
    }
    
    /**
     * Description: this method finds an empty space in the grid and places a
     * new tile there
     * Input: void
     * Output void
     */
    private void placeNewTile() {
        
        // create Ordered Pair array to store all grid indices that are empty
        OrderedPair[] spots = new OrderedPair[16 - numOfTiles];
        
        // index counter for the Ordered Pair array
        int idx = 0;
        
        // go through all the spots, find the ones that are empty
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == null) {
                    // put the indices as pair into the ordered pair array
                    spots[idx++] = new OrderedPair(i, j);
                }
            }
        }
        
        // create new tile and put it in random open spot
        OrderedPair newTile = spots[(int) (Math.random() * spots.length)];
        int x = newTile.getX();
        int y = newTile.getY();
        grid[x][y] = new Tile(.125 + x * .25, .125 + y * .25);
        
        // increase the tile counter by 1
        numOfTiles++;
    }
    
    /**
     * Description: this method returns whether or not the game is over
     * Input: void
     * Output: boolean of whether it is game over
     */
    public boolean isGameOver() {
        return gameOver;
    }
    
    /**
     * Description: this method returns whether or not there is a move possible
     * Input: void
     * Output: boolean of whether or not there is a move possible
     */
    public boolean movePossible() {
        // create boolean to determine if theres a move possible
        boolean possible = false;
        
        // go though the columns and check if there is a merge possible
        for (int i = 0; i < grid.length; i++) {
            for (int j = grid[i].length - 1; j >= 0; j--) {
                // check if the tile below is same
                if (j - 1 >= 0) {
                    // move possible even if only one move possible
                    possible = possible || 
                        grid[i][j].getNum() == grid[i][j - 1].getNum();
                }
                // check if the tile to the right is the same
                if (i + 1 < 4) {
                    possible = possible || 
                        grid[i][j].getNum() == grid[i + 1][j].getNum();
                }
            }
        }
        return possible;
    }
    
    /**
     * Description: this method collides tiles
     * Input: index i and j of the receving tile and displacement away of 
     * tile being merged
     * Output: void
     */
    private void merge(int i, int j, int dj, int di) {
        // merge
        grid[i][j].combine(grid[i - di][j - dj]);
        
        // get rid of the other tile and adjust tile counter
        grid[i - di][j - dj] = null;
        numOfTiles--;
    }
    
    /**
     * Description: this method checks if two tiles are able to be merged
     * Input: index i and j of the receving tile and displacement away of 
     * tile being merged
     * Output: boolean
     */
    private boolean canMerge(int i, int j, int dj, int di) {
        // check if indexes are in bounds and the tiles are not null
        if (i - di >= 0 && i - di < 4 &&
            j - dj >= 0 && j - dj < 4 &&
            grid[i][j] != null && grid[i - di][j - dj] != null) {
            
            // create boolean to see if there is emptiness between tiles
            boolean empBetween = false;
            
            // if merging tile more than 1 space away, must be empty between
            if (dj == 1 || di == 1 || dj == -1 || di == -1) { // 1 space diff
                empBetween = true;
            } else if (dj == 2) { // 2 spaces down
                empBetween = grid[i][j - 1] == null;
            } else if (dj == -2) { // 2 spaces up
                empBetween = grid[i][j + 1] == null;
            } else if (dj == 3) { // 3 spaces down
                empBetween = grid[i][j - 2] == null && grid[i][j - 1] == null;
            } else if (dj == -3) { // 3 spaces up
                empBetween = grid[i][j + 2] == null && grid[i][j + 1] == null;
            } else if (di == 2) { // 2 spaces left
                empBetween = grid[i - 1][j] == null;
            } else if (di == -2) { // 2 spaces right
                empBetween = grid[i + 1][j] == null;
            } else if (di == 3) { // 3 spaces left
                empBetween = grid[i - 2][j] == null && grid[i - 1][j] == null;
            } else if (di == -3) { // 3 spaces right
                empBetween = grid[i + 2][j] == null && grid[i + 1][j] == null;
            } 
            
            // return whether tiles are shiftable and have the same number
            return empBetween && 
                grid[i][j].getNum() == grid[i - di][j - dj].getNum();
            
        } else { // indexes are not in bounds or the tiles are null
            return false;
        }
    }
    
    /**
     * Description: this method shifts the tiles in the direction indicated and
     * returns whether or not there has been shift
     * Input: a Direction
     * Output: boolean
     */
    private boolean shift(Direction d) {
        // boolean to determine if there's been a shift
        boolean hasShifted = false;
        
        // if shift up
        if (d == Direction.UP) {
            for (int i = 0; i < grid.length; i++) {
                // index from top
                int idx = 3;
                for (int j = grid[i].length - 1; j >= 0; j--) {
                    // if this tile is not empty, shift it to a higher spot
                    if (grid[i][j] != null) {
                        // move the tile's coordinates up
                        for (int k = 0; k < idx - j; k++) {
                            grid[i][j].moveUp();
                        }
                        // move higher spot in the array if in different spots
                        if (grid[i][j] != grid[i][idx]) {
                            grid[i][idx--] = grid[i][j];
                            
                            // there's been a shift
                            hasShifted = true;
                            
                            // get rid of the copy of this tile in old spot
                            grid[i][j] = null;
                        } else {
                            idx--;
                        }
                    }
                }
            }
        } 
        // if shift down
        else if (d == Direction.DOWN) {
            for (int i = 0; i < grid.length; i++) {
                // index from bottom
                int idx = 0;
                for (int j = 0; j < grid[i].length; j++) {
                    // if this tile is not empty, shift it to a lower spot
                    if (grid[i][j] != null) {
                        // move the tile's coordinates down
                        for (int k = 0; k < j - idx; k++) {
                            grid[i][j].moveDown();
                        }
                        // move lower spot in the array if in different spots
                        if (grid[i][j] != grid[i][idx]) {
                            grid[i][idx++] = grid[i][j];
                            
                            // there's been a shift
                            hasShifted = true;
                            
                            // get rid of the copy of this tile in old spot
                            grid[i][j] = null;
                        } else {
                            idx++;
                        }
                    }
                }
            }
        }
        // if shift right
        else if (d == Direction.RIGHT) {
            for (int i = 0; i < grid.length; i++) {
                // index from right
                int idx = 3;
                for (int j = grid[i].length - 1; j >= 0; j--) {
                    // if this tile is not empty, shift it to a righter spot
                    if (grid[j][i] != null) {
                        // move the tile's coordinates up
                        for (int k = 0; k < idx - j; k++) {
                            grid[j][i].moveRight();
                        }
                        // move right in the array if in different spots
                        if (grid[j][i] != grid[idx][i]) {
                            grid[idx--][i] = grid[j][i];
                            
                            // there's been a shift
                            hasShifted = true;
                            
                            // get rid of the copy of this tile in old spot
                            grid[j][i] = null;
                        } else {
                            idx--;
                        }
                    }
                }
            }
        }
        // if shift left
        else if (d == Direction.LEFT) {
            for (int i = 0; i < grid.length; i++) {
                // index from left
                int idx = 0;
                for (int j = 0; j < grid[i].length; j++) {
                    // if this tile is not empty, shift it to a lefter spot
                    if (grid[j][i] != null) {
                        // move the tile's coordinates up
                        for (int k = 0; k < j - idx; k++) {
                            grid[j][i].moveLeft();
                        }
                        // move left in the array if in different spots
                        if (grid[j][i] != grid[idx][i]) {
                            grid[idx++][i] = grid[j][i];
                            
                            // there's been a shift
                            hasShifted = true;
                            
                            // get rid of the copy of this tile in old spot
                            grid[j][i] = null;
                        } else {
                            idx++;
                        }
                    }
                }
            }
        }
        return hasShifted;
    }
}