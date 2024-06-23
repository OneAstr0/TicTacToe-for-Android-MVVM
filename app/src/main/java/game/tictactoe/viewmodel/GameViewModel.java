package game.tictactoe.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import game.tictactoe.model.Cell;
import game.tictactoe.model.Player;

public class GameViewModel extends AndroidViewModel {

    private static final String STATE_NOTHING = "nothing";
    private static final String STATE_CROSS = "cross";
    private static final String STATE_ZERO = "zero";

    private Player player1;
    private Player player2;
    private MutableLiveData<Cell[][]> board;
    private MutableLiveData<Player> currentPlayer;
    private MutableLiveData<String> statusMessage;

    public GameViewModel(@NonNull Application application) {
        super(application);
        board = new MutableLiveData<>(new Cell[3][3]);
        currentPlayer = new MutableLiveData<>();
        statusMessage = new MutableLiveData<>();
        resetGame();
    }

    public void resetGame() {
        // Initialize players
        player1 = new Player("Player 1", 0, null);
        player2 = new Player("Player 2", 0, null);
        currentPlayer.setValue(player1);
        statusMessage.setValue("Первый ход крестиков!");

        // Initialize board
        Cell[][] newBoard = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newBoard[i][j] = new Cell(null, STATE_NOTHING);
            }
        }
        board.setValue(newBoard);
    }

    public LiveData<Cell[][]> getBoard() {
        return board;
    }

    public LiveData<Player> getCurrentPlayer() {
        return currentPlayer;
    }

    public LiveData<String> getStatusMessage() {
        return statusMessage;
    }

    public void onCellClicked(int row, int col) {
        if (board.getValue() == null) return;
        Cell[][] newBoard = board.getValue();
        if (newBoard[row][col].isEmpty()) {
            Player player = currentPlayer.getValue();
            if (player != null) {
                newBoard[row][col].setPlayer(player);
                newBoard[row][col].setImageLink(player == player1 ? STATE_CROSS : STATE_ZERO);
                board.setValue(newBoard);

                // Check for winner
                if (checkForWinner(newBoard, player)) {
                    statusMessage.setValue(player.getName() + " победил!");
                } else {
                    // Switch player
                    currentPlayer.setValue(player == player1 ? player2 : player1);
                    statusMessage.setValue("Ход " + currentPlayer.getValue().getName());
                }
            }
        }
    }

    private boolean checkForWinner(Cell[][] board, Player player) {
        // implement logic to check for winner
        // return true if the player has won, otherwise false
        return false;
    }
}
