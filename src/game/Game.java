package game;

import comm.Client;
import gui.*;
import json_parse.Parse;
import linkedlist.LinkedList;
import matrix.Matrix;
import org.json.simple.JSONObject;
import people.Player;

import java.io.IOException;

/**
 * Handles the game state.
 *
 * @author David Azofeifa H.
 */
public class Game {

    private Matrix matrix;
    private Player player1;
    private Player player2;
    private MainMenuForm mainMenuForm;
    private WaitingForm waitingForm;
    private MainGame mainGame;
    private GameWonForm gameWonForm;
    private GameLostForm gameLostForm;
    private GameDrawForm gameDrawForm;

    public Player getPlayer1() { return player1; }

    public void setPlayer1(Player player1) { this.player1 = player1; }

    public Player getPlayer2() { return player2; }

    public void setPlayer2(Player player2) { this.player2 = player2; }

    public Game(int rows, int columns, int initialValue) {
        this.matrix = new Matrix(rows, columns, initialValue);

        // Changes matrix values to please game format
        boolean changeRowValues = true;
        for (int i = 0; i < this.matrix.getRows(); i++) {
            if (changeRowValues) {
                boolean changeToOne = true;
                for (int j = 0; j < this.matrix.getColumns(); j++) {
                    if (changeToOne) {
                        this.matrix.changeValue(i, j, 1);
                        changeToOne = false;
                    }
                    else
                        changeToOne = true;
                }
                changeRowValues = false;
            }
            else {
                changeRowValues = true;
            }
        }
    }

    public String startMenu() throws IOException {
        this.mainMenuForm = new MainMenuForm();
        String playerName = this.mainMenuForm.selfBuild();
        this.waitingForm = new WaitingForm();
        return playerName;
    }

    public void startGame(String player1Name, String player2Name, int playerNum) throws IOException {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);

        this.mainGame = new MainGame(this.matrix, this.player1, this.player2, playerNum);
        this.mainGame.selfBuild();
    }

    public boolean checkIfGameOver() {
        boolean gameOver = true;
        for (int i=0; i<this.mainGame.getMatrix().getRows(); i++) {
            for (int j=0; j < this.mainGame.getMatrix().getColumns(); j++) {
                if (this.mainGame.getMatrix().viewValue(j, i) == 0) {
                    gameOver = false;
                    break;
                }
            }
            if (!gameOver){
                break;
            }
        }
        return gameOver;
    }

    public void endGame() {
        if (this.mainGame.getPlayer1().getScore() > this.mainGame.getPlayer1().getScore() && this.mainGame.getPlayerNum() == 1) {
            this.gameWonForm = new GameWonForm();
        }
        else if (this.mainGame.getPlayer1().getScore() > this.mainGame.getPlayer1().getScore() && this.mainGame.getPlayerNum() == 2) {
            this.gameLostForm = new GameLostForm();
        }
        else if (this.mainGame.getPlayer1().getScore() < this.mainGame.getPlayer1().getScore() && this.mainGame.getPlayerNum() == 2) {
            this.gameWonForm = new GameWonForm();
        }
        else if (this.mainGame.getPlayer1().getScore() < this.mainGame.getPlayer1().getScore() && this.mainGame.getPlayerNum() == 1) {
            this.gameWonForm = new GameWonForm();
        }
        else {
            this.gameDrawForm = new GameDrawForm();
        }
    }


    public void gameLost() {
        this.gameLostForm = new GameLostForm();
    }

    public void gameWon() {
        this.gameWonForm = new GameWonForm();
    }

    public void gameDraw() {
        this.gameDrawForm = new GameDrawForm();
    }

    public void updateGameState(LinkedList list) {
        // Replaces matrix with the one received from the server
        this.matrix = (Matrix) list.getHead().getData();

        // Updates player scores
        this.mainGame.getPlayer1().setScore( ((Player) list.getHead().getNext().getData()).getScore());
        this.mainGame.getPlayer2().setScore( ((Player) list.getHead().getNext().getNext().getData()).getScore());

        for (int i=0; i < this.matrix.getRows(); i++) {
            for (int j=0; j < this.matrix.getColumns(); j++) {
                this.mainGame.getMatrix().changeValue(i, j, this.matrix.viewValue(i,j));
            }
        }

    }

    public JSONObject getGameState(LinkedList posMouse) {
        Parse parser = new Parse();
        JSONObject jsonDoc = parser.gameStateToJson(this.matrix, this.player1, this.player2, posMouse);
        return jsonDoc;
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game(9, 9, 0);

        System.out.println(game.startMenu());
        game.startGame("hola", "adios", 1);
        LinkedList list = new LinkedList();
        Matrix matrix = new Matrix(9,9,0);
        matrix.changeValue(1,0,2);
        matrix.changeValue(1,1,1);
        matrix.changeValue(1, 2, 2);
        matrix.changeValue(0,1,3);
        matrix.changeValue(3,4,1);
        matrix.changeValue(1,2,1);
        matrix.changeValue(3,3,2);
        list.append(matrix);
        Player player1 = new Player("juanito");
        player1.setScore(100);
        Player player2 = new Player("paquita");
        player2.setScore(200);
        list.append(player1);
        list.append(player2);
        LinkedList list2 =  new LinkedList();
        list2.append(2);
        list2.append(4);
        list.append(list2);
        game.updateGameState(list);
    }

}
