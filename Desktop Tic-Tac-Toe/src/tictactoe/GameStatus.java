package tictactoe;

public enum GameStatus {
    /*
    NOT_STARTED("Game is not started", "Start"),
    IN_PROGRESS("Game in progress", "Reset"),
    DRAW("Draw", "Reset"),
    X_WINS("X wins", "Reset"),
    O_WINS("O wins", "Reset");*/

    NOT_STARTED("Game is not started", "Start"),
    IN_PROGRESS("The turn of {0} Player ({1})", "Reset"),
    DRAW("Draw", "Reset"),
    X_WINS("The {0} Player (X) wins", "Reset"),
    O_WINS("The {0} Player (O) wins", "Reset");

    final String message;
    final String flag;

    GameStatus(String message, String flag) {
        this.message = message;
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public String getFlag() {
        return flag;
    }
}
