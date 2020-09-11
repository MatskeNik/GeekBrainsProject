package advanced_gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Calculator extends JFrame {
    public Calculator() {
        setTitle("Calculator v2.0");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(new Rectangle(0, 0, 300, 500));


        setLayout(new GridLayout(2, 1));

        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        JTextField inputField = new JTextField();
        inputField.setEditable(false);
        top.add(inputField);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(4, 3));

        List<JButton> buttons = createBasicButtons();
        for (JButton btn : buttons) {
            bottom.add(btn);
        }

        JComboBox<ButtonMode> modes = new JComboBox<>();
        modes.addItem(ButtonMode.BASIC);
        modes.addItem(ButtonMode.ENGINEER);
        modes.addItem(ButtonMode.PROGRAMMING);
        modes.addActionListener(new ModeListener(bottom));
        bottom.add(modes);

        add(top);
        add(bottom);



        setVisible(true);
    }

    private java.util.List<JButton> createBasicButtons() {
        java.util.List<JButton> basicButtons = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            basicButtons.add(new JButton(String.valueOf(i)));
        }
        basicButtons.add(new JButton("C"));
        basicButtons.add(new JButton("+"));
        basicButtons.add(new JButton("-"));
        basicButtons.add(new JButton("*"));
        basicButtons.add(new JButton("/"));

        return basicButtons;
    }

}
