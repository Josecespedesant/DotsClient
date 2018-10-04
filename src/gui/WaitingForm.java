package gui;

import javax.swing.*;
import java.awt.event.WindowEvent;

/**
 * GUI where players wait for match to start.
 *
 * @author David Azofeifa H.
 */
public class WaitingForm {
    private JPanel queue;

    private JFrame frame = new JFrame("Dots");
    // TODO poder poner correctamente posicion de espera en la cola

    public JPanel getQueue() {
        return this.queue;
    }

    public void selfBuild() {
        this.frame.setContentPane(new WaitingForm().queue);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    public void selfDestroy() {
        this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }


}
