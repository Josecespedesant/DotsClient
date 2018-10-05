package gui;

import sun.applet.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Main menu gui where players can choose their names.
 *
 * @author David Azofeifa
 */
public class MainMenuForm {
    private JFormattedTextField welcomeToDotsFormattedTextField;
    private JTextField textField1;
    private JFormattedTextField pleaseEnterYourNameFormattedTextField;
    private JPanel BienvenidaYRegistro;
    private JFrame frame;



    public MainMenuForm() {

        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                WaitingForm waitingForm = new WaitingForm();
                waitingForm.selfBuild();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public JPanel getPanel() {
        return this.BienvenidaYRegistro;
    }

    public void selfBuild() {
        this.frame = new JFrame("Dots");
        frame.setContentPane(new MainMenuForm().BienvenidaYRegistro);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void selfDestroy() {
        this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    public static void main(String[] args) {
        MainMenuForm mainMenuForm = new MainMenuForm();
        mainMenuForm.selfBuild();
    }
}
