package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWonForm {
    private JPanel panel1;
    private JButton Restart;

    public GameWonForm() {
        Restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // TODO volver al menu inicial al presionar el boton
            }
        });
    }
}
