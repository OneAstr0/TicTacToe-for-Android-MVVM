package game.tictactoe.model;

public class Game {

    private static final String STATE_NOTHING = "nothing";
    private static final String STATE_CROSS = "cross";
    private static final String STATE_ZERO = "zero";

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Cell[][] board;

    public Game() {
        player1 = new Player("крестик");
        player2 = new Player("нолик");
        currentPlayer = player1;
        board = new Cell[3][3];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Cell(null, STATE_NOTHING);
            }
        }
    }

    public void resetGame() {
        player1 = new Player("крестик");
        player2 = new Player("нолик");
        currentPlayer = player1;
        initializeBoard();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public String getStatusMessage() {
        return "Ходит " + currentPlayer.getName();
    }

    public boolean placeElement(int row, int col) {
        if (board[row][col].isEmpty()) {
            board[row][col].setPlayer(currentPlayer);
            board[row][col].setImageLink(currentPlayer == player1 ? STATE_CROSS : STATE_ZERO);

            // update player status
            currentPlayer.addElement(board[row][col]);

            // check winner
            if (checkForWinner(currentPlayer)) {
                return true;
            } else {
                // switch player
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            }
        }
        return false;
    }

    private boolean checkForWinner(Player player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0].getPlayer() == player && board[i][1].getPlayer() == player && board[i][2].getPlayer() == player) ||
                    (board[0][i].getPlayer() == player && board[1][i].getPlayer() == player && board[2][i].getPlayer() == player)) {
                return true;
            }
        }

        if ((board[0][0].getPlayer() == player && board[1][1].getPlayer() == player && board[2][2].getPlayer() == player) ||
                (board[0][2].getPlayer() == player && board[1][1].getPlayer() == player && board[2][0].getPlayer() == player)) {
            return true;
        }

        return false;
    }

    public boolean isGameOver() {
        return checkForWinner(player1) || checkForWinner(player2);
    }

}
