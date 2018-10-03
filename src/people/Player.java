package people;

/**
 * Represents player
 *
 * @author David Azofeifa H.
 */
public class Player {

    private int score = 0;
    private boolean activeTurn = false;
    private String name;

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }

    public boolean isActiveTurn() { return activeTurn; }

    public void setActiveTurn(boolean activeTurn) { this.activeTurn = activeTurn; }

    public String getName() { return name; }

    public Player(String name) {
        this.name = name;
    }

}
