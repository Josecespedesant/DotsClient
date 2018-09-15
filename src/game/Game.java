package game;

import matrix.Matrix;
import people.Player;

/**
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
    }

    public void addPlayerPoints(int points,int playerNumber) {
        switch (playerNumber) {
            case 1:
                player1.setScore(player1.getScore() + points);
                break;
            case 2:
                player2.setScore(player2.getScore() + points);
                break;
            default:
                break;
        }
    }
}
