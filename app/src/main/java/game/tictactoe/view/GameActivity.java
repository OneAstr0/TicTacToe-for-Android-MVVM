package game.tictactoe.view;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import game.tictactoe.R;
import game.tictactoe.model.Cell;
import game.tictactoe.viewmodel.GameViewModel;
import game.tictactoe.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity {

    private GameViewModel gameViewModel;
    private ActivityGameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        binding.setViewModel(gameViewModel);
        binding.setLifecycleOwner(this);

        gameViewModel.getBoard().observe(this, board -> {
            if (board != null) {
                updateBoard(board);
            }
        });

        gameViewModel.getStatusMessage().observe(this, message -> {
            binding.outputInfo.setText(message);
        });

        setupClickListeners();
    }

    private void setupClickListeners() {
        binding.cell00.setOnClickListener(v -> gameViewModel.onCellClicked(0, 0));
        binding.cell01.setOnClickListener(v -> gameViewModel.onCellClicked(0, 1));
        binding.cell02.setOnClickListener(v -> gameViewModel.onCellClicked(0, 2));
        binding.cell10.setOnClickListener(v -> gameViewModel.onCellClicked(1, 0));
        binding.cell11.setOnClickListener(v -> gameViewModel.onCellClicked(1, 1));
        binding.cell12.setOnClickListener(v -> gameViewModel.onCellClicked(1, 2));
        binding.cell20.setOnClickListener(v -> gameViewModel.onCellClicked(2, 0));
        binding.cell21.setOnClickListener(v -> gameViewModel.onCellClicked(2, 1));
        binding.cell22.setOnClickListener(v -> gameViewModel.onCellClicked(2, 2));
    }

    private void updateBoard(Cell[][] board) {

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                updateCell(getCellView(i, j), board[i][j]);
            }
        }

        boolean gameOver = gameViewModel.isGameOver();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                getCellView(i, j).setClickable(!gameOver);
            }
        }

        binding.restartButton.setVisibility(gameOver ? View.VISIBLE : View.GONE);
    }

    private View getCellView(int row, int col) {
        switch (row) {
            case 0:
                switch (col) {
                    case 0: return binding.cell00;
                    case 1: return binding.cell01;
                    case 2: return binding.cell02;
                }
                break;
            case 1:
                switch (col) {
                    case 0: return binding.cell10;
                    case 1: return binding.cell11;
                    case 2: return binding.cell12;
                }
                break;
            case 2:
                switch (col) {
                    case 0: return binding.cell20;
                    case 1: return binding.cell21;
                    case 2: return binding.cell22;
                }
                break;
        }
        throw new IllegalArgumentException("Invalid cell coordinates: " + row + ", " + col);
    }


    private void updateCell(View cell, Cell data) {
        if (data != null) {
            if (data.getImageLink().equals("nothing")) {
                cell.setBackgroundResource(R.drawable.nothing);
            } else if (data.getImageLink().equals("cross")) {
                cell.setBackgroundResource(R.drawable.cross);
            } else if (data.getImageLink().equals("zero")) {
                cell.setBackgroundResource(R.drawable.zero);
            }
        }
    }

    public void restartGame(View view) {
        gameViewModel.restartGame();
    }

}
