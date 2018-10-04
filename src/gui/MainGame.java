package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MainGame {

    private JPanel game;
    private JFrame frame;

    public MainGame() {
        this.game = new JPanel();
        this.game.setLayout(null);
        this.game.setSize(640,480);
        this.game.setBackground(Color.blue);
    }

    public void selfBuild() {
        this.frame = new JFrame("Dots");
        frame.setContentPane(new MainGame().game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void selfDestroy() {
        this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }


    public static void main(String[] args) {
        MainGame mainGame = new MainGame();
        mainGame.selfBuild();
    }
}
