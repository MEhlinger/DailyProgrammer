package gameoflife;

/**
 *
 * @author Marshall Ehlinger
 */
public class GameOfLife {

    public static void main(String[] args) {
        GOLGrid grid = new GOLGrid();
        
        int numNeighbors;
        boolean newGrid[][] = new boolean[grid.getWidth()][grid.getHeight()];

        ConsoleView.consoleDrawGrid(grid, "___", " X ");
        
        // Main "Game" Loop
        while (true) {
            for (int c = 0; c < grid.getWidth(); c++) {
                for (int r = 0; r < grid.getHeight(); r++) {
                    numNeighbors = grid.countLiveNeighbors(c, r);
                    if (grid.isCellAlive(c, r)) {
                        if (numNeighbors > 3 || numNeighbors < 2) {
                            newGrid[c][r] = false;
                        }
                    } else if (numNeighbors == 3) {
                        newGrid[c][r] = true;
                    }
                }
            }

            // Copy new grid to the main grid
            grid.setGrid(newGrid);

            // Display Grid
            ConsoleView.consoleDrawGrid(grid, "___", " X ");
        }
    }
}
