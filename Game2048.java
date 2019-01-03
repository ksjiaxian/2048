/** 
 * Name: Kenneth Shinn
 * PennKey: kshinn
 * Recitation: 215
 * 
 * Execution: java Game2048
 * 
 * This class creates the game 2048. Tiles move and collide with other
 * tiles to create a new tile with twice the number. Every new move
 * generates another 2 or 4 tile. Player wins when the 2048 tile is achieved
 */
public class Game2048 {
    // grid for the 2048 game
    private static Grid grid;
    
    public static void main(String[] args) {
        // create new grid
        grid = new Grid();
        
        // enable animation for the game
        PennDraw.enableAnimation(30);
        
        // set the background color
        PennDraw.clear(204, 192, 178);
        
        // draw the starting grid
        grid.draw();
        
        // main game loop
        while (!grid.isGameOver()) {
            
            // handle movement and control
            if (PennDraw.hasNextKeyTyped()) {
                
                // update the grid depending on the key pressed
                char control = PennDraw.nextKeyTyped();
                if (control == 'w' || control == 'W') { // w for up
                    grid.move(Grid.Direction.UP);
                } else if (control == 'a' || control == 'A') { // a for left
                    grid.move(Grid.Direction.LEFT);
                } else if (control == 's' || control == 'S') { // s for down
                    grid.move(Grid.Direction.DOWN);
                } else if (control == 'd' || control == 'D') { // d for right
                    grid.move(Grid.Direction.RIGHT);
                }
            }
            // advance to the next frame
            PennDraw.advance();
        }
    }
}