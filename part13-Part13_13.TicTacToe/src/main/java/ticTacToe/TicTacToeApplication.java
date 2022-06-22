package ticTacToe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class TicTacToeApplication extends Application {
    
    private GameLogic gameLogic;
    
    @Override
    public void start(Stage stage) {
        
        GameView gameView = new GameView(new GameLogic(3));
        Scene scene = new Scene(gameView.getView());
        stage.setScene(scene);
        stage.show();    
    }

    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
    }
}
