package tictactoe;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    Button(String name) {
        super.setText(" ");
        super.setFont(new Font("TimesRoman", Font.BOLD, 50));
        super.setName("Button" + name);
        super.setFocusPainted(false);
    }

    public void clear() {
        super.setText(" ");
    }
}
