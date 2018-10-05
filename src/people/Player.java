package people;

/**
 * Represents player
 *
 * @author David Azofeifa H.
 */
public class Player {

    private boolean activeTurn = false;
    private String name;
    private int score = 0;

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }

    public boolean getActiveTurn() { return activeTurn; }

    public void setActiveTurn(boolean activeTurn) { this.activeTurn = activeTurn; }

    public String getName() { return name; }

    public Player(String name) {
        this.name = name;
    }

}