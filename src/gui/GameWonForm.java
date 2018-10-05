package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * GUI that tells players they've won
 *
 * @author David Azofeifa H.
 */
public class GameWonForm {
    private JPanel gameWon;
    private JButton Restart;
    private JFrame frame;

    public GameWonForm() {
        Restart.addActionListener(new ActionListener() {
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
        this.frame = new JFrame("Dots");
        this.frame.setContentPane(new GameWonForm().gameWon);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    public void selfDestroy() {
        this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
