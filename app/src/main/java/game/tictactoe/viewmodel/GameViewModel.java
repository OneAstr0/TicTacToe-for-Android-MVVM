package game.tictactoe.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import game.tictactoe.model.Cell;
import game.tictactoe.model.Game;
import game.tictactoe.model.Player;

public class GameViewModel extends AndroidViewModel {

    private Game game;
    private MutableLiveData<Cell[][]> board;
    private MutableLiveData<String> statusMessage;

    public GameViewModel(@NonNull Application application) {
        super(application);
        game = new Game();
        board = new MutableLiveData<>(game.getBoard());
        statusMessage = new MutableLiveData<>(game.getStatusMessage());
    }

    public void resetGame() {
        game.resetGame();
        board.setValue(game.getBoard());
        statusMessage.setValue(game.getStatusMessage());
    }

    public LiveData<Cell[][]> getBoard() {
        return board;
    }

    public LiveData<String> getStatusMessage() {
        return statusMessage;
    }

    public void onCellClicked(int row, int col) {
        if (game.placeElement(row, col)) {
            board.setValue(game.getBoard());
            statusMessage.setValue(game.getCurrentPlayer().getName() + " победил!");
        } else {
            board.setValue(game.getBoard());
            statusMessage.setValue(game.getStatusMessage());
        }
    }
}
