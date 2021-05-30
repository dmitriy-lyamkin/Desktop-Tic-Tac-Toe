package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

public class StatusBar extends JPanel {
    final JLabel status = new JLabel();

    StatusBar(ActionListener listener) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        status.setName("LabelStatus");
        status.setPreferredSize(new Dimension(350, 20));
        add(status);
        status.setText(GameStatus.NOT_STARTED.getMessage());
        //status.setText(GameStatus.EMPTY.getMessage());

/*
        final var reset = new JButton();
        reset.setName("ButtonReset");
        reset.setText("Reset");
        reset.addActionListener(listener);
        add(reset);

 */
    }

    void setMessage(GameStatus state, Object... args) {
        status.setText(MessageFormat.format(state.getMessage(), args));
    }
}
