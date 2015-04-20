package gameoflife;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Marshall Ehlinger
 */
public class GuiOfLife extends Application {
    
    final int WIDTH = 30;
    final int HEIGHT = WIDTH;
    
    Rectangle2D res = Screen.getPrimary().getVisualBounds();
    final double WIN_WIDTH =  res.getWidth() * .5;
    final double WIN_HEIGHT = res.getHeight() * .8;
    
    private boolean playing = false;
    private GOLGrid GOL;
    
    Button cellButtons[][];

    
    @Override
    public void start(Stage primaryStage) {
        
        BorderPane bpane = new BorderPane();
        GridPane gpane = new GridPane();
        HBox buttonBar = new HBox();
        
        bpane.setMaxSize(WIN_WIDTH, WIN_HEIGHT);
        
        gpane.setAlignment(Pos.CENTER);
        bpane.setCenter(gpane);
        bpane.setLeft(buttonBar);
        bpane.setStyle("-fx-background-color: #000066");
        
        GOL = new GOLGrid(WIDTH, HEIGHT, true);
        
        // Create grid of buttons
        cellButtons= new Button[WIDTH][HEIGHT];
        
        for (int col = 0; col < cellButtons.length; col++) {
            for (int row = 0; row < cellButtons[0].length; row++) {
                cellButtons[col][row] = new Button("");
                cellButtons[col][row].setStyle("-fx-base: #0066F0");
                int cellSize = (int) (bpane.getWidth());
                cellButtons[col][row].setMinWidth(cellSize);
                cellButtons[col][row].setMaxHeight(cellSize);
                
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
        
        // Initialize button colors, add to visual pane
        for (int col = 0; col < cellButtons.length; col++) {
            for (int row = 0; row < cellButtons[0].length; row++) {
                if (GOL.isCellAlive(col, row)) {
                        cellButtons[col][row].setStyle("-fx-base: #000000");
                    } else {
                        cellButtons[col][row].setStyle("-fx-base: #0066F0");
                    }
                gpane.add(cellButtons[row][col], col, row);
            }
        }
        
        // Animation / Game Logic Call
        EventHandler<ActionEvent> updateGui = e -> {
            GOL.updateGame();
            updateCellColors();
        };
        
        Timeline gameAnimate = new Timeline(new KeyFrame(Duration.millis(200), updateGui));
        gameAnimate.setCycleCount(Timeline.INDEFINITE);
        
        // UI Buttons
        Button btnPlayPause = new Button("Play");
        btnPlayPause.setOnAction( e -> {
            this.playing = !playing;
            if (playing) {
                gameAnimate.play();
                btnPlayPause.setText("Pause");
            } else {
                gameAnimate.pause();
                btnPlayPause.setText("Play");
            }
        });
        buttonBar.getChildren().add(btnPlayPause);
        
        
        Button btnClear = new Button("Clear");
        btnClear.setOnAction( e -> {
            GOL.clearGrid();
            updateCellColors();
        });
        buttonBar.getChildren().add(btnClear);
        
        Button btnRandomize = new Button("Randomize");
        btnRandomize.setOnAction( e -> {
            GOL.setGrid(GOL.getRandomGrid());
            updateCellColors();
        });
        buttonBar.getChildren().add(btnRandomize);
        
        // GUI Final Set-Up
        Scene scene = new Scene(bpane, WIN_WIDTH, WIN_HEIGHT);
        
        primaryStage.setTitle("Conway's Game of Life");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
}
    
    private void updateCellColors() {
            for (int col = 0; col < cellButtons.length; col++) {
                for (int row = 0; row < cellButtons[0].length; row++) {
                    if (GOL.isCellAlive(col, row)) {
                        cellButtons[col][row].setStyle("-fx-base: #000000");
                    } else {
                        cellButtons[col][row].setStyle("-fx-base: #0066F0");
                    }
                }
            }
        }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
