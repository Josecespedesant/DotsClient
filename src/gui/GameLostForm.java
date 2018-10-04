package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLostForm {
    private JPanel panel1;
    private JButton restartButton;

    public GameLostForm() {
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // TODO volver al menu inicial al presionar el boton
            }
        });
    }
}
