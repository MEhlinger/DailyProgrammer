package gameoflife;

/**
 *
 * @author Marshall Ehlinger
 * 
 *  Displays a Conway's Game of Life grid to the console
 */
public class ConsoleView {
    
    public static void consoleDrawGrid(GOLGrid grid, String liveCell, String deadCell) {
        clearConsole();
        for (int r = 0; r < grid.getWidth(); r++) {
            for (int c = 0; c < grid.getHeight(); c++) {
                if (grid.isCellAlive(c, r)) {
                    System.out.print(liveCell);
                } else {
                    System.out.print(deadCell);
                }
            }
            System.out.print("\n");
        }
    }
    
    public final static void clearConsole()
{
    try
    {
        final String os = System.getProperty("os.name");

        if (os.contains("Windows"))
        {
            Runtime.getRuntime().exec("cls");
        }
        else
        {
            Runtime.getRuntime().exec("clear");
        }
    }
    catch (final Exception e)
    {
        System.out.println("Weak-ass Exception handling: console clear failure.");
    }
}
}
