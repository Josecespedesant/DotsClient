package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * GUI that tells players they've lost.
 *
 * @author David Azofeifa H.
 */
public class GameLostForm {
    private JPanel gameOver;
    private JButton restartButton;
    private JPanel retartForm;
    private JFrame frame = new JFrame("Dots");

    public GameLostForm() {

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JFrame frame = new JFrame("Dots");
                frame.setContentPane(new MainMenuForm().getPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
    }

    public void selfBuild() {
        this.frame.setContentPane(new GameLostForm().gameOver);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    public void selfDestroy() {
        this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    public static void main(String[] args) {
        // TODO eliminar cuando se termina testing
        GameLostForm gameLostForm = new GameLostForm();
        gameLostForm.selfBuild();
    }
}
