package gameoflife;

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
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == -0) {
                    // Don't check the cell in question for its own state
                    continue;
                }
                cellX = i;
                cellY = j;
                // Wrapping logic, a la pacman
                if (x < 0) {
                    cellX += this.WIDTH;
                }
                else if (x > this.WIDTH) {
                    cellX -= this.WIDTH;
                }
                if (y < 0) {
                    cellY += this.WIDTH;
                }
                else if (y > this.HEIGHT) {
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
        for (int c = 0; c < this.WIDTH; c++) {
            for (int r = 0; r < this.HEIGHT; r++) {
               newGrid[c][r] = rand.nextBoolean();
            }
        }
        return newGrid;
    }
    
    public int getHeight() {
        return this.HEIGHT;
    }
    
    public int getWIDTH() {
        return this.WIDTH;
    }
    
    public boolean[][] getGrid() {
        return this.grid;
    }
}
