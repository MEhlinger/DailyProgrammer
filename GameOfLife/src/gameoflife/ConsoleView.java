package gameoflife;

/**
 *
 * @author Marshall Ehlinger
 * 
 *  Displays a Conway's Game of Life grid to the console
 */
public class ConsoleView {
    
    public static void consoleDrawGrid(GOLGrid grid, String liveCell, String deadCell) {
        for (int c = 0; c < grid.getWidth(); c++) {
            for (int r = 0; r < grid.getHeight(); r++) {
                if (grid.isCellAlive(c, r)) {
                    System.out.print(liveCell);
                } else {
                    System.out.print(deadCell);
                }
            }
            System.out.print("\n");
        }
    }
}
