package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Board extends JPanel {
    private static final Button[][] board = new Button[3][3];

    Board(ActionListener listener) {
        setSize(450, 450);
        setLayout(new GridLayout(3, 3));

        char[] l = new char[] {'A','B','C'};
        char[] n = new char[] {'3','2','1'};
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                Button button = new Button(String.valueOf(l[y]) + n[x]);
                //button.addActionListener(e -> actionPerformed(button));
                button.addActionListener(listener);
                board[x][y] = button;
                add(button);
            }
        }
    }

    public Button[][] getBoard() {
        return board;
    }

    public void resetBoard() {
        for (Button[] row : board)
            for (Button button: row)
                button.clear();
    }

    public void setEnabledBoard(boolean enabled) {
        Arrays.stream(board)
                .flatMap(Arrays::stream)
                .forEach(b -> b.setEnabled(enabled));
    }
}
