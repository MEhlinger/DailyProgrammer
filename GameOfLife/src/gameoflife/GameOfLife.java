package gameoflife;

import java.util.Arrays;

/**
 *
 * @author Marshall Ehlinger
 */
public class GameOfLife {

    public static void main(String[] args) {
        GOLGrid grid = new GOLGrid(25, 25);
        
        int numNeighbors;
        boolean newGrid[][] = new boolean[grid.getWidth()][grid.getHeight()];
        
        // Initial print
        ConsoleView.consoleDrawGrid(grid, "(X)", "( )");
        // Main "Game" Loop
        while (true) {
            for (int r = 0; r < grid.getWidth(); r++) {
                for (int c = 0; c < grid.getHeight(); c++) {
                    numNeighbors = grid.countLiveNeighbors(c, r);
                    if (grid.isCellAlive(c, r)) {
                        if (numNeighbors < 2 || numNeighbors > 3) {
                            newGrid[c][r] = false; // Any live cell w/ less than 2 or more than 3 live neighbors dies
                        } else {
                            newGrid[c][r] = true; // Any live cell w/ 2 or 3 live neighbors lives on to the next generation
                        }
                    } 
                    else if (numNeighbors == 3) {
                         newGrid[c][r] = true; // Any dead cell w/ exactly 3 live neighbors revives
                    }
                }
            }

            // Copy new grid to the main grid, and clear the now-old newGrid
            grid.setGrid(newGrid);
            newGrid = new boolean[grid.getWidth()][grid.getHeight()];

            // Display Grid
            ConsoleView.consoleDrawGrid(grid, "(X)", "( )");
        }
    }
}
