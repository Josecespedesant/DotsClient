package game;

import comm.Client;
import gui.*;
import json_parse.Parse;
import linkedlist.LinkedList;
import matrix.Matrix;
import org.json.simple.JSONObject;
import people.Player;

/**
 * Handles the game state.
 *
 * @author David Azofeifa H.
 */
public class Game {

    private Matrix matrix;
    private Player player1;
    private Player player2;

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

    public String startMenu() {
        MainMenuForm mainMenuForm = new MainMenuForm();
        String playerName = mainMenuForm.selfBuild();
        WaitingForm waitingForm = new WaitingForm();
        return playerName;
    }

    public void startGame() {
        MainGame mainGame = new MainGame();
    }

    public void gameLost() {
        GameLostForm gameLostForm = new GameLostForm();
    }

    public void gameWon() {
        GameWonForm gameWonForm = new GameWonForm();
    }

    public void updateGameState(LinkedList list) {
        // Replaces matrix with the one received from the server
        this.matrix = (Matrix) list.getHead().getData();

        // Updates player scores
        this.player1.setScore( ((Player) list.getHead().getNext().getData()).getScore());
        this.player2.setScore( ((Player) list.getHead().getNext().getNext().getData()).getScore());
    }

    public JSONObject getGameState(LinkedList posMouse) {
        Parse parser = new Parse();
        JSONObject jsonDoc = parser.gameStateToJson(this.matrix, this.player1, this.player2, posMouse);
        return jsonDoc;
    }

    public static void main(String[] args) {
        Game game = new Game(5, 5, 1);
        System.out.println(game.startMenu());
    }

}
