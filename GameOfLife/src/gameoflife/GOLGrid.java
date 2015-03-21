package gameoflife;
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
    }
    
    public GOLGrid(int width, int height) {
        // Contructor takes dimensions and inits randomly arranged grid 
        this.WIDTH = width;
        this.HEIGHT = height;
        this.grid = new boolean[WIDTH][HEIGHT];
    }
    
    public GOLGrid(boolean startArray[][]) {
        // Constructor takes user-defined input array as initial grid
        this.WIDTH = startArray.length;
        this.HEIGHT = startArray[0].length;
        this.grid = startArray;
    }
    
    
    // PUBLIC METHODS
    public boolean[] getNeighbors(int x, int y) {
        // STUB
        // Returns array with state of all neighbors, wrapping around ends of grid
        boolean neighbors[] = new boolean[8];
        return neighbors;
    }
    
    public boolean isCellAlive(int x, int y) {
        // STUB
        // Returns cell state
        return false;
    }
    
    public int countLiveNeighbors(int x, int y) {
        // STUB
        // Counts true neighbors (out of 8) of cell [x][y]
        // Checks wraps from opposite extreme sids of the grid
        return 0;
    }
}
