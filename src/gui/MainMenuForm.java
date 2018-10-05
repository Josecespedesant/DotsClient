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

    private String playerName;



    public MainMenuForm() {

        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerName = textField1.getText();
                selfDestroy();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public JPanel getPanel() {
        return this.BienvenidaYRegistro;
    }

    public String selfBuild() {
        this.frame = new JFrame("Dots");
        this.frame.setContentPane(this.BienvenidaYRegistro);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);

        while (playerName == null) {
            System.out.println("No name");
        }
        return playerName;
    }

    public void selfDestroy() {
        this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    public static void main(String[] args) {
        MainMenuForm mainMenuForm = new MainMenuForm();
        mainMenuForm.selfBuild();
    }
}
