package gameoflife;

import static java.lang.System.arraycopy;
import java.util.Random;

/**
 *
 * @author Marshall Ehlinger
 * 
 * Creates a rectangular grid for use with Conway's Game of Life
 */
public class GOLGrid {
    // INSTANCE VARIABLES
    private boolean grid[][];
    
    final private int DEFAULT_WIDTH = 10;
    final private int DEFAULT_HEIGHT = 10;
    
    final private int HEIGHT, WIDTH;
    
    // CONSTRUCTORS
    public GOLGrid() {
        // Constrtuctor initalizes randomly arranged grid of default dimensions
        this.WIDTH = DEFAULT_WIDTH;
        this.HEIGHT = DEFAULT_HEIGHT;
        this.grid = new boolean[WIDTH][HEIGHT];
        grid = getRandomGrid();
    }
    
    public GOLGrid(int width, int height) {
        // Contructor takes dimensions and inits randomly arranged grid 
        this.WIDTH = width;
        this.HEIGHT = height;
        this.grid = new boolean[WIDTH][HEIGHT];
        this.grid = getRandomGrid();
    }
    
    public GOLGrid(boolean startArray[][]) {
        // Constructor takes user-defined input array as initial grid
        this.WIDTH = startArray.length;
        this.HEIGHT = startArray[0].length;
        this.grid = startArray;
    }
    

    // PUBLIC METHODS
    public boolean[] getNeighbors(int x, int y) {
        // Returns array with state of all neighbors, wrapping around ends of grid
        boolean neighbors[] = new boolean[8];
        int cellX, cellY;
        int count = 0;
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                if (c == 0 && r == 0) {
                    // Don't check the cell in question for its own state
                    continue;
                }
                cellX = x + c;
                cellY = y + r;
                // Wrapping logic, a la pacman
                if (cellX < 0) {
                    cellX += this.WIDTH;
                }
                else if (cellX >= this.WIDTH) {
                    cellX -= this.WIDTH;
                }
                if (cellY < 0) {
                    cellY += this.HEIGHT;
                }
                else if (cellY >= this.HEIGHT) {
                    cellY -= this.HEIGHT;
                }
                
                neighbors[count++] = isCellAlive(cellX, cellY);
            }
        }
        return neighbors;
    }
    
    public boolean isCellAlive(int x, int y) {
        // Returns cell state
        return this.grid[x][y];
    }
    
    public int countLiveNeighbors(int x, int y) {
        // Counts true neighbors (out of 8) of cell [x][y]
        int liveNeighbors = 0;
        boolean neighbors[] = getNeighbors(x, y);
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i]) {
                liveNeighbors++;
            }
        }
        return liveNeighbors;
    }
    
    private boolean[][] getRandomGrid() {
        // Returns 2d bool grid sized to current grid's dimensions
        // For constructors only, at this point
        boolean[][] newGrid = new boolean[this.WIDTH][this.HEIGHT];
        Random rand = new Random();
        for (int r = 0; r < this.WIDTH; r++) {
            for (int c = 0; c < this.HEIGHT; c++) {
               newGrid[c][r] = rand.nextBoolean();
            }
        }
        return newGrid;
    }
    
    public void updateGame() {
        int numNeighbors;
        boolean[][] newGrid = new boolean[this.getWidth()][this.getHeight()];

        for (int r = 0; r < this.getWidth(); r++) {
                for (int c = 0; c < this.getHeight(); c++) {
                    numNeighbors = this.countLiveNeighbors(c, r);
                    if (this.isCellAlive(c, r)) {
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

        // Copy new grid to the main grid
        this.setGrid(newGrid);
    }
    
    
    public int getHeight() {
        return this.HEIGHT;
    }
    
    public int getWidth() {
        return this.WIDTH;
    }
    
    public boolean[][] getGrid() {
        return this.grid;
    }
    
    public void setGrid(boolean[][] newGrid) {
        for (boolean[] column : this.grid) {
            arraycopy(newGrid, 0, this.grid, 0, this.grid.length);
        }
    }
}
