package game.tictactoe.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> navigateToGame;

    public MainViewModel(@NonNull Application application) {
        super(application);
        navigateToGame = new MutableLiveData<>();
    }

    public LiveData<Boolean> getNavigateToGame() {
        return navigateToGame;
    }

    public void onPlayButtonClicked() {
        navigateToGame.setValue(true);
    }

    public void doneNavigating() {
        navigateToGame.setValue(false);
    }
}
