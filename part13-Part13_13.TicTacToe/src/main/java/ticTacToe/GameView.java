/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 *
 * @author Glen
 */
public class GameView {
    
    private GameLogic gameLogic;
    private Label gameStatus;
    
    public GameView(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.gameStatus = new Label("");
    }
    
    public BorderPane getView() {
        updateCurrentPlayer();
        BorderPane layout = new BorderPane();
        
        GridPane buttons = drawGrid(gameLogic.getFieldSize());
        buttons.setPadding(new Insets(10, 10, 10, 10));

        layout.setTop(this.gameStatus);
        layout.setCenter(buttons);

        return layout;
    }
    
    public GridPane drawGrid(int size) {
        GridPane gridLayout = new GridPane();
        Button[][] buttons = new Button[size][size];
        
        for (int x = 0; x < gameLogic.getFieldSize(); x++) {
            for (int y = 0; y < gameLogic.getFieldSize(); y++) {
                
                Button button = new Button(" ");
                button.setFont(Font.font("Monospaced", 40));
                button.setOnMouseClicked(action -> {
                    int column = GridPane.getColumnIndex(button);
                    int row = GridPane.getRowIndex(button);
                   
                    if (!gameLogic.isPositionPlayed(column, row) && !gameLogic.isGameOver()) {
                        button.setText(gameLogic.getCurrentPlayer().toString());
                        gameLogic.makeAMove(column, row);
                        updateCurrentPlayer();
                    }
                    
                    if (gameLogic.isGameOver() == true) {
                        setGameOverText();
                    }
                });
                
                buttons[x][y] = button;
                gridLayout.add(button, x, y);
            }
        }
        
        return gridLayout;
    }
    
    private void updateCurrentPlayer() {
        this.gameStatus.setText("Turn: " + gameLogic.getCurrentPlayer());
    }
    
    private void setGameOverText() {
        this.gameStatus.setText("Game over! " + gameLogic.winChecker());
    }
}
