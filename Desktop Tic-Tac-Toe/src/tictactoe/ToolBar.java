package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ToolBar extends JPanel {
    final JButton reset = new JButton();
    final JButton Player1 = new PlayerChooser();
    final JButton Player2 = new PlayerChooser();

    ToolBar(ActionListener listener) {
        setSize(450, 20);
        setLayout(new GridLayout(1, 3));

        Player1.setName("ButtonPlayer1");
        Player2.setName("ButtonPlayer2");

        reset.setName("ButtonStartReset");
        reset.setText("Start");
        reset.addActionListener(listener);

        add(Player1);
        add(reset);
        add(Player2);

    }
}
