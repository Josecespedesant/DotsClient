package gui;

import game.Game;

import javax.swing.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

public class MainMenuForm {
    private JFormattedTextField welcomeToDotsFormattedTextField;
    private JTextField textField1;
    private JFormattedTextField pleaseEnterYourNameFormattedTextField;
    private JPanel BienvenidaYRegistro;

    public MainMenuForm() {
        textField1.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent inputMethodEvent) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent inputMethodEvent) {

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
