package gameoflife;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Marshall Ehlinger
 */
public class GuiOfLife extends Application {
    
    final int WIDTH = 35;
    final int HEIGHT = WIDTH;
    final int WIN_WIDTH = 950;
    final int WIN_HEIGHT = WIN_WIDTH;
    
    @Override
    public void start(Stage primaryStage) {
        
        BorderPane bpane = new BorderPane();
        GridPane gpane = new GridPane();
        gpane.setAlignment(Pos.CENTER);
        bpane.setCenter(gpane);
        bpane.setStyle("-fx-background-color: #000066");
        
        GOLGrid GOL = new GOLGrid(WIDTH, HEIGHT);
        
        Button cellButtons[][] = new Button[WIDTH][HEIGHT];
        
        for (int col = 0; col < cellButtons.length; col++) {
            for (int row = 0; row < cellButtons[0].length; row++) {
                cellButtons[col][row] = new Button("");
                cellButtons[col][row].setStyle("-fx-base: #0066F0");
                cellButtons[col][row].setMinWidth((WIN_WIDTH / WIDTH));
                cellButtons[col][row].setMaxHeight(cellButtons[col][row].getMinWidth());
                
                cellButtons[col][row].setOnAction( e -> {
                    Button thisButton = (Button) e.getSource();
                    GOL.invertCellState(GridPane.getRowIndex(thisButton), GridPane.getColumnIndex(thisButton));
                    if (GOL.isCellAlive(GridPane.getRowIndex(thisButton), GridPane.getColumnIndex(thisButton))) {
                        thisButton.setStyle("-fx-base: #000000");
                    } else {
                        thisButton.setStyle("-fx-base: #0066F0");
                    }
                });
            }
        }
        
        for (int col = 0; col < cellButtons.length; col++) {
            for (int row = 0; row < cellButtons[0].length; row++) {
                gpane.add(cellButtons[row][col], col, row);
            }
        }
        
        EventHandler<ActionEvent> updateGui = e -> {
            GOL.updateGame();
            for (int col = 0; col < cellButtons.length; col++) {
                for (int row = 0; row < cellButtons[0].length; row++) {
                    if (GOL.isCellAlive(col, row)) {
                        cellButtons[col][row].setStyle("-fx-base: #000000");
                    } else {
                        cellButtons[col][row].setStyle("-fx-base: #0066F0");
                    }
                }
            }
        };
        
        Timeline gameAnimate = new Timeline(new KeyFrame(Duration.millis(200), updateGui));
        gameAnimate.setCycleCount(Timeline.INDEFINITE);
        gameAnimate.play();
        
        Scene scene = new Scene(bpane, WIN_WIDTH, WIN_HEIGHT);
        
        primaryStage.setTitle("Conway's Game of Life");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
