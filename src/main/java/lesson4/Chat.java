package lesson4;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.*;

public class Chat  extends JFrame {
    public Chat() {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(new Rectangle(0, 0, 500, 500));

        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        JTextArea inputArea = new JTextArea();
        inputArea.setEditable(false);
        top.add(inputArea);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(1, 2));
        JTextField inputMessages = new JTextField();
        inputMessages.addActionListener(new ModeListener(inputMessages, inputArea));
        inputMessages.setEditable(true);
        bottom.add(inputMessages);

        JButton sendMessage = new JButton("Отправить");
        sendMessage.addActionListener(new ModeListener(inputMessages, inputArea));
        bottom.add(sendMessage);

        add(top, CENTER);
        add(bottom, SOUTH);

        setVisible(true);
    }
}
