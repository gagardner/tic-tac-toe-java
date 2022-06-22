/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticTacToe;

import java.util.ArrayList;

/**
 *
 * @author Glen
 */
public class GameLogic {
    public enum Player {
        O,
        X
    }
    
    private int fieldSize;
    private Player currentPlayer = Player.X;
    private Player[][] board;
    private boolean isGameOver = false;
    private int moveCounter = 0;
    
    public GameLogic(int fieldSize) {
        this.fieldSize = fieldSize;
        this.board = new Player[fieldSize][fieldSize];
    }
    
    public int getFieldSize() {
        return this.fieldSize;
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public boolean isPositionPlayed(int column, int row) {
        return board[row][column] != null;
    }
    
    public void makeAMove(int column, int row) {
            board[row][column] = currentPlayer;
            setCurrentPlayer(); 
            System.out.println("win check: " + winChecker());  
            //System.out.println("status of diag score: " + checkDiagonals());
    }
    
    public void gameOver() {
        this.isGameOver = true;
    }
    
    public boolean isGameOver() {
        return isGameOver;
    }
    
    public String winChecker() {
        if (winForO(checkRows()) == true 
                || winForO(checkColumns()) == true
                || winForO(checkDiagonals()) == true) {
            gameOver();

            return "O wins";
        } else if (winForX(checkRows()) == true
                || winForX(checkColumns()) == true
                || winForX(checkDiagonals()) == true) {
            gameOver();
            
            return "X wins";
        } 
        
        moveCounter++;
        if (moveCounter == 9) {
            gameOver();
        }
        
        return "Draw";
        
    }
    
    public boolean winForO(int score) {
        return score == -3;
    }
    
    public boolean winForX(int score) {
        return score == 3;
    }
    
   
    
    public void setCurrentPlayer() { 
         if(currentPlayer == Player.O) {
            this.currentPlayer = Player.X;
        } else {
            this.currentPlayer = Player.O;
        }
    }
    
    
    public int checkRows() {
        int score = 0;
        
        for (int row = 0; row < this.board.length; row++) {
            //score = 0;
            for (int column = 0; column < this.board[row].length; column++) {
                //System.out.println("row: " + row + " column: " + column);
                if (this.board[row][column] == null) {
                    continue;
                } else if (this.board[row][column].equals(Player.X)) {
                   score++;
                } else if (this.board[row][column].equals(Player.O)) {
                   score--;
                }
            }
            
            if (score == 3 || score == -3) {
                return score;
            }
        }
        
        return score;
    }
    
    public int checkColumns() {
        int score = 0;
        
        for (int row = 0; row < this.board.length; row++) {
            score = 0;
            for (int column = 0; column < this.board[row].length; column++) {
                //System.out.println("row: " + row + " column: " + column);
                if (this.board[column][row] == null) {
                    continue;
                } else if (this.board[column][row].equals(Player.X)) {
                   score++;
                } else if (this.board[column][row].equals(Player.O)) {
                   score--;
                }
            }
            
            if (score == 3 || score == -3) {
                return score;
            }
        }
        
        return score;
    }    
    
    public int checkDiagonals() {
        int score = 0;
        int firstScore = 0;
        int secondScore = 0;
        //int lastRow = this.board.length - 1;

        for (int goingRight = 0; goingRight < this.board.length; goingRight++) {
            if (this.board[goingRight][goingRight] == null) {
                continue;
            } else if (this.board[goingRight][goingRight].equals(Player.X)){
                firstScore++;
            } else if (this.board[goingRight][goingRight].equals(Player.O)) {
                firstScore--;
            }  
        }
        
        int row = 0;
        for (int column = this.board.length - 1; column >= 0; column--) {
            
            if (this.board[row][column] == null) { 
                    continue;
                } else if (this.board[row][column].equals(Player.X)) {
                    secondScore++;
                } else if (this.board[row][column].equals(Player.O)) {
                    secondScore--;
                }
            
            row++;
            }
        
        if (firstScore == 3 || firstScore == -3) {
            score = firstScore;
        } else if (secondScore == 3 || secondScore == -3) {
            score = secondScore; 
        }
  
        return score;
    }
}
