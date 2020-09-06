package lesson4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeListener implements ActionListener {
    private final JTextField textField;
    private final JTextArea inputArea;

    public ModeListener(JTextField textField, JTextArea inputArea) {
        this.textField = textField;
        this.inputArea = inputArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inputArea.setText(inputArea.getText()+textField.getText()+"\n");
        textField.setText("");
    }
}
