package gameoflife;

/**
 *
 * @author Marshall Ehlinger
 */
public class GameOfLife {

    public static void main(String[] args) {
        GOLGrid testGrid = new GOLGrid();
        ConsoleView.consoleDrawGrid(testGrid, "___", " X ");
    }
    
}
