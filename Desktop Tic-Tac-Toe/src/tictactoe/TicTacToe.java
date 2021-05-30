package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;

public class TicTacToe extends JFrame implements ActionListener {
    private final Board board = new Board(this);
    private final StatusBar statusBar = new StatusBar(this::reset);
    public final ToolBar toolBar = new ToolBar(this/*this::reset*/);

    private final String TURN_X = "X";
    private final String TURN_O = "O";
    private final String TURN_NONE = " ";
    private String whoseTurn = TURN_NONE; //TURN_X;
    private GameStatus status = GameStatus.NOT_STARTED; //23.05.2021

    public TicTacToe() {
        setJMenuBar(new Menu(this::processMenu));
        add(board, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        add(toolBar, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 490);
        setBackground(Color.WHITE);
        setTitle("Tic Tac Toe");
        setResizable(false);
        setVisible(true);
        board.setEnabledBoard(false);
        setStatus(GameStatus.NOT_STARTED);
    }

    void processMenu(final ActionEvent e) {
        final var item = (JMenuItem) e.getSource();
        reset();
        if (item.getName().equals("MenuHumanHuman")) {
            toolBar.Player1.setText(PlayerChooser.Type.Human.name());
            toolBar.Player2.setText(PlayerChooser.Type.Human.name());
            setStatus(GameStatus.IN_PROGRESS);
        } else if (item.getName().equals("MenuHumanRobot")) {
            toolBar.Player1.setText(PlayerChooser.Type.Human.name());
            toolBar.Player2.setText(PlayerChooser.Type.Robot.name());
            setStatus(GameStatus.IN_PROGRESS);
        } else if (item.getName().equals("MenuRobotHuman")) {
            toolBar.Player1.setText(PlayerChooser.Type.Robot.name());
            toolBar.Player2.setText(PlayerChooser.Type.Human.name());
            setStatus(GameStatus.IN_PROGRESS);
        } else if (item.getName().equals("MenuRobotRobot")) {
            toolBar.Player1.setText(PlayerChooser.Type.Robot.name());
            toolBar.Player2.setText(PlayerChooser.Type.Robot.name());
            setStatus(GameStatus.IN_PROGRESS);
        }
        else {
            this.dispose();
        }
    }

    public void setStatus(GameStatus status) {
        this.status = status;
        board.setEnabledBoard(!whoseTurn.equals(TURN_NONE));

        //statusBar.status.setText(status.getMessage());
        PlayerChooser currentPlayer = (PlayerChooser) (whoseTurn.equals(TURN_X) ? toolBar.Player1 : toolBar.Player2);
        statusBar.setMessage(status,currentPlayer.getText(), whoseTurn);

        toolBar.reset.setText(status.getFlag());
        if (status.getFlag().equals("Reset")) {
            toolBar.Player1.setEnabled(false);
            toolBar.Player2.setEnabled(false);
        } else {
            toolBar.Player1.setEnabled(true);
            toolBar.Player2.setEnabled(true);
        }

        if (status == GameStatus.NOT_STARTED) {
            board.resetBoard();
            whoseTurn = TURN_X;
            board.setEnabledBoard(false);
        }

    }

    public void setStatus(Consumer<GameStatus> status, GameStatus gameStatus) {
        status.accept(gameStatus);
        statusBar.status.setText(gameStatus.getMessage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final var button = (JButton) e.getSource();

        if (button.getText().equals("Reset") || button.getText().equals("Start")) {
            reset();
        } else

        if (button.getText().equals(" ") && !whoseTurn.equals(TURN_NONE) ) {
            button.setText(whoseTurn);
            setStatus(getGameStatus(button)); //
        } else {
            // ignore
        }
    }

    public void reset() {
        if (status.equals(GameStatus.NOT_STARTED))
            setStatus(GameStatus.IN_PROGRESS);
        else setStatus(GameStatus.NOT_STARTED);
    }


    public void reset(ActionEvent e) {
        if (status.equals(GameStatus.NOT_STARTED))
            setStatus(GameStatus.IN_PROGRESS);
        else setStatus(GameStatus.NOT_STARTED);

        /*
        Consumer<GameStatus> gameStatusConsumer = s -> {
            board.resetBoard();
            board.setEnabledBoard(true);
            whoseTurn = TURN_X;
        };
        setStatus(gameStatusConsumer, GameStatus.NOT_STARTED);
        */
    }

    public GameStatus getGameStatus(JButton button) {
        Button[][] cells = board.getBoard();
        // WIN
        for (int x = 0; x < cells.length; x++) {
            if (!cells[x][0].getText().equals(" ") && cells[x][0].getText().equals(cells[x][1].getText()) &&
                    cells[x][0].getText().equals(cells[x][2].getText())) {
                whoseTurn = TURN_NONE;
                return cells[x][0].getText().equals("X") ? GameStatus.X_WINS : GameStatus.O_WINS;
            }
        }

        for (int y = 0; y < cells.length; y++) {
            if (!cells[0][y].getText().equals(" ") && cells[0][y].getText().equals(cells[1][y].getText()) &&
                    cells[0][y].getText().equals(cells[2][y].getText())) {
                whoseTurn = TURN_NONE;
                return cells[0][y].getText().equals("X") ? GameStatus.X_WINS : GameStatus.O_WINS;
            }
        }

        if (!cells[0][0].getText().equals(" ") && cells[0][0].getText().equals(cells[1][1].getText()) &&
                cells[0][0].getText().equals(cells[2][2].getText())) {
            whoseTurn = TURN_NONE;
            return cells[0][0].getText().equals("X") ? GameStatus.X_WINS : GameStatus.O_WINS;
        }
        else
        if (!cells[2][0].getText().equals(" ") && cells[2][0].getText().equals(cells[1][1].getText()) &&
                cells[2][0].getText().equals(cells[0][2].getText())) {
            whoseTurn = TURN_NONE;
            return cells[2][0].getText().equals("X") ? GameStatus.X_WINS : GameStatus.O_WINS;
        }

        // IN_PROGRESS
        for (int x = 0; x < cells.length; x++)
            for (int y = 0; y < cells[x].length; y++) {
                if (cells[x][y].getText().equals(" ") ) {
                    whoseTurn = whoseTurn.equals(TURN_NONE) || whoseTurn.equals(TURN_O) ? TURN_X : TURN_O;
                    return GameStatus.IN_PROGRESS;
                }
            }

        // DRAW
        whoseTurn = TURN_NONE;
        return GameStatus.DRAW;
    }



}