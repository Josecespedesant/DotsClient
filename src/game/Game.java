package game;

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

    public Game(Player player1, Player player2, int rows, int columns, int initialValue) {
        this.player1 = player1;
        this.player2 = player2;
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
        Player player1 = new Player("David");
        Player player2 = new Player("Jose Antorio");
        Game game = new Game(player1, player2, 9, 9, 0);
        game.matrix.printMatrix();
    }

}
