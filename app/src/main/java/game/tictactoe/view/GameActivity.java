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
        updateCell(binding.cell00, board[0][0]);
        updateCell(binding.cell01, board[0][1]);
        updateCell(binding.cell02, board[0][2]);
        updateCell(binding.cell10, board[1][0]);
        updateCell(binding.cell11, board[1][1]);
        updateCell(binding.cell12, board[1][2]);
        updateCell(binding.cell20, board[2][0]);
        updateCell(binding.cell21, board[2][1]);
        updateCell(binding.cell22, board[2][2]);
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
}
