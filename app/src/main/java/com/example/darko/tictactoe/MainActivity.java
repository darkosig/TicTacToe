package com.example.darko.tictactoe;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.darko.tictactoe.database.model.HighScore;
import com.example.darko.tictactoe.database.model.RoomDB;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_play)
    Button btnPlay;
    @BindView(R.id.btn_highscores)
    Button btnHighScores;


    @BindView(R.id.btn_pos11)
    Button btnPos11;
    @BindView(R.id.btn_pos12)
    Button btnPos12;
    @BindView(R.id.btn_pos13)
    Button btnPos13;
    @BindView(R.id.btn_pos21)
    Button btnPos21;
    @BindView(R.id.btn_pos22)
    Button btnPos22;
    @BindView(R.id.btn_pos23)
    Button btnPos23;
    @BindView(R.id.btn_pos31)
    Button btnPos31;
    @BindView(R.id.btn_pos32)
    Button btnPos32;
    @BindView(R.id.btn_pos33)
    Button btnPos33;

    private int[] grid = new int[9];
    private int usedFields = 0;
    private boolean hasWinner = false;
    private boolean gameInProgress = false;
    private boolean gridDisabled = true;
    private boolean lastScoreEntered = false;
    private boolean dialogDismissed = false;
    private long date1;
    private long score;
    private String playerName;
    public static RoomDB database;
    public boolean isSecondPlayerEnabled = false;
    private MenuItem itemPlayer1;
    private MenuItem itemPlayer2;
    private int itemSelected = 1;
    private int winner = 0;
    private int onTurn = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            grid = savedInstanceState.getIntArray("grid");
            hasWinner = savedInstanceState.getBoolean("has_winner");
            usedFields = savedInstanceState.getInt("used_fields");
            date1 = savedInstanceState.getLong("date1");
            isSecondPlayerEnabled = savedInstanceState.getBoolean("is_second_player_enabled");
            winner = savedInstanceState.getInt("winner");
            onTurn = savedInstanceState.getInt("on_turn");
            itemSelected = savedInstanceState.getInt("item_selected");
            gridDisabled = savedInstanceState.getBoolean("grid_disabled");
            lastScoreEntered = savedInstanceState.getBoolean("last_score_entered");
            score = savedInstanceState.getLong("score");
            dialogDismissed = savedInstanceState.getBoolean("dialog_dismissed");

            for (int i = 0; i < 9; i++) {

                switch (i) {
                    case 0:
                        if (grid[i] == 1) {
                            btnPos11.setBackgroundResource(R.drawable.x);
                        } else if (grid[i] == 2) {
                            btnPos11.setBackgroundResource(R.drawable.o);
                        } else if (grid[i] == 0){
                            btnPos11.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
                        }
                        break;
                    case 1:
                        if (grid[i] == 1) {
                            btnPos12.setBackgroundResource(R.drawable.x);
                        } else if (grid[i] == 2) {
                            btnPos12.setBackgroundResource(R.drawable.o);
                        } else if (grid[i] == 0){
                            btnPos12.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
                        }
                        break;
                    case 2:
                        if (grid[i] == 1) {
                            btnPos13.setBackgroundResource(R.drawable.x);
                        } else if (grid[i] == 2) {
                            btnPos13.setBackgroundResource(R.drawable.o);
                        } else if (grid[i] == 0){
                            btnPos13.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
                        }
                        break;
                    case 3:
                        if (grid[i] == 1) {
                            btnPos21.setBackgroundResource(R.drawable.x);
                        } else if (grid[i] == 2) {
                            btnPos21.setBackgroundResource(R.drawable.o);
                        } else if (grid[i] == 0){
                            btnPos21.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
                        }
                    case 4:
                        if (grid[i] == 1) {
                            btnPos22.setBackgroundResource(R.drawable.x);
                        } else if (grid[i] == 2) {
                            btnPos22.setBackgroundResource(R.drawable.o);
                        } else if (grid[i] == 0){
                            btnPos22.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
                        }
                        break;
                    case 5:
                        if (grid[i] == 1) {
                            btnPos23.setBackgroundResource(R.drawable.x);
                        } else if (grid[i] == 2) {
                            btnPos23.setBackgroundResource(R.drawable.o);
                        } else if (grid[i] == 0){
                            btnPos23.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
                        }
                        break;
                    case 6:
                        if (grid[i] == 1) {
                            btnPos31.setBackgroundResource(R.drawable.x);
                        } else if (grid[i] == 2) {
                            btnPos31.setBackgroundResource(R.drawable.o);
                        } else if (grid[i] == 0){
                            btnPos31.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
                        }
                        break;
                    case 7:
                        if (grid[i] == 1) {
                            btnPos32.setBackgroundResource(R.drawable.x);
                        } else if (grid[i] == 2) {
                            btnPos32.setBackgroundResource(R.drawable.o);
                        } else if (grid[i] == 0){
                            btnPos32.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
                        }
                        break;
                    case 8:
                        if (grid[i] == 1) {
                            btnPos33.setBackgroundResource(R.drawable.x);
                        } else if (grid[i] == 2) {
                            btnPos33.setBackgroundResource(R.drawable.o);
                        } else if (grid[i] == 0){
                            btnPos33.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
                        }
                        break;
                }
            }

            if(gridDisabled){
                disableGrid();
            }

            if(gameInProgress){
                isXWon();
                isOWon();
            }
            if(hasWinner && !lastScoreEntered){
                if(winner == 1) {
                    inputPlayerName();
                } else if(winner == 2 && isSecondPlayerEnabled){
                    inputPlayerName();
                }
            }

            if(hasWinner && winner == 2 && !isSecondPlayerEnabled && !dialogDismissed){
                gameLostDialog();
            }

        } else {
            disableGrid();
        }

        database = Room.databaseBuilder(getApplicationContext(), RoomDB.class, "HighScoresDB").allowMainThreadQueries().build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        itemPlayer1 = menu.findItem(R.id.action_1pl);
        itemPlayer2 = menu.findItem(R.id.action_2pl);

        if(itemSelected == 1){
            itemPlayer1.setChecked(true);
        } else if (itemSelected == 2)  {
            itemPlayer2.setChecked(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_1pl) {
            isSecondPlayerEnabled = false;
            itemPlayer2.setChecked(false);
            itemPlayer1.setChecked(true);
            resetGame();
            itemSelected = 1;
        }

        if (item.getItemId() == R.id.action_2pl) {
            isSecondPlayerEnabled = true;
            itemPlayer1.setChecked(false);
            itemPlayer2.setChecked(true);
            resetGame();
            itemSelected = 2;
        }

        if (item.getItemId() == R.id.action_exit) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void resetGame(){
        onTurn = 1;
        hasWinner = false;
        usedFields = 0;
        lastScoreEntered = false;
        dialogDismissed = false;
        resetGridArray();
        resetGrid();
        enableGrid();
        date1 = System.currentTimeMillis();
    }

    @OnClick(R.id.btn_play)
    public void play() {
        resetGame();
    }

    @OnClick(R.id.btn_highscores)
    public void highScores() {
        Intent highScoreIntent = new Intent(MainActivity.this, HighScoresActivity.class);
        startActivity(highScoreIntent);
    }

    @OnClick(R.id.btn_pos11)
    public void btn11() {
        if (onTurn == 2) {
            btnPos11.setBackgroundResource(R.drawable.o);
            grid[0] = 2;
        } else {
            btnPos11.setBackgroundResource(R.drawable.x);
            grid[0] = 1;
        }
        btnPos11.setClickable(false);
        usedFields++;
        if (onTurn == 1) {
            isXWon();
        } else {
            isOWon();
        }
        changeTurn();
    }

    @OnClick(R.id.btn_pos12)
    public void btn12() {
        if (onTurn == 2) {
            btnPos12.setBackgroundResource(R.drawable.o);
            grid[1] = 2;
        } else {
            btnPos12.setBackgroundResource(R.drawable.x);
            grid[1] = 1;
        }
        btnPos12.setClickable(false);
        usedFields++;
        if (onTurn == 1) {
            isXWon();
        } else {
            isOWon();
        }
        changeTurn();
    }

    @OnClick(R.id.btn_pos13)
    public void btn13() {
        if (onTurn == 2) {
            btnPos13.setBackgroundResource(R.drawable.o);
            grid[2] = 2;
        } else {
            btnPos13.setBackgroundResource(R.drawable.x);
            grid[2] = 1;
        }
        btnPos13.setClickable(false);
        usedFields++;
        if (onTurn == 1) {
            isXWon();
        } else {
            isOWon();
        }
        changeTurn();
    }

    @OnClick(R.id.btn_pos21)
    public void btn21() {
        if (onTurn == 2) {
            btnPos21.setBackgroundResource(R.drawable.o);
            grid[3] = 2;
        } else {
            btnPos21.setBackgroundResource(R.drawable.x);
            grid[3] = 1;
        }
        btnPos21.setClickable(false);
        usedFields++;
        if (onTurn == 1) {
            isXWon();
        } else {
            isOWon();
        }
        changeTurn();
    }

    @OnClick(R.id.btn_pos22)
    public void btn22() {
        if (onTurn == 2) {
            btnPos22.setBackgroundResource(R.drawable.o);
            grid[4] = 2;
        } else {
            btnPos22.setBackgroundResource(R.drawable.x);
            grid[4] = 1;
        }
        btnPos22.setClickable(false);
        usedFields++;
        if (onTurn == 1) {
            isXWon();
        } else {
            isOWon();
        }
        changeTurn();
    }

    @OnClick(R.id.btn_pos23)
    public void btn23() {
        if (onTurn == 2) {
            btnPos23.setBackgroundResource(R.drawable.o);
            grid[5] = 2;
        } else {
            btnPos23.setBackgroundResource(R.drawable.x);
            grid[5] = 1;
        }
        btnPos23.setClickable(false);
        usedFields++;
        if (onTurn == 1) {
            isXWon();
        } else {
            isOWon();
        }
        changeTurn();
    }

    @OnClick(R.id.btn_pos31)
    public void btn31() {
        if (onTurn == 2) {
            btnPos31.setBackgroundResource(R.drawable.o);
            grid[6] = 2;
        } else {
            btnPos31.setBackgroundResource(R.drawable.x);
            grid[6] = 1;
        }
        btnPos31.setClickable(false);
        usedFields++;
        if (onTurn == 1) {
            isXWon();
        } else {
            isOWon();
        }
        changeTurn();
    }

    @OnClick(R.id.btn_pos32)
    public void btn32() {
        if (onTurn == 2) {
            btnPos32.setBackgroundResource(R.drawable.o);
            grid[7] = 2;
        } else {
            btnPos32.setBackgroundResource(R.drawable.x);
            grid[7] = 1;
        }
        btnPos32.setClickable(false);
        usedFields++;
        if (onTurn == 1) {
            isXWon();
        } else {
            isOWon();
        }
        changeTurn();
    }

    @OnClick(R.id.btn_pos33)
    public void btn33() {
        if (onTurn == 2) {
            btnPos33.setBackgroundResource(R.drawable.o);
            grid[8] = 2;
        } else {
            btnPos33.setBackgroundResource(R.drawable.x);
            grid[8] = 1;
        }
        btnPos33.setClickable(false);
        usedFields++;
        if (onTurn == 1) {
            isXWon();
        } else {
            isOWon();
        }
        changeTurn();
    }

    public void changeTurn(){
        if(isSecondPlayerEnabled){
            if(onTurn == 1){
                onTurn = 2;
            } else {
                onTurn = 1;
            }
        }
    }

    public void isXWon() {

        if (grid[0] == 1 && grid[1] == 1 && grid[2] == 1) {
            winner = 1;
            gameWon();
        } else if (grid[3] == 1 && grid[4] == 1 && grid[5] == 1) {
            winner = 1;
            gameWon();
        } else if (grid[6] == 1 && grid[7] == 1 && grid[8] == 1) {
            winner = 1;
            gameWon();
        } else if (grid[0] == 1 && grid[3] == 1 && grid[6] == 1) {
            winner = 1;
            gameWon();
        } else if (grid[1] == 1 && grid[4] == 1 && grid[7] == 1) {
            winner = 1;
            gameWon();
        } else if (grid[2] == 1 && grid[5] == 1 && grid[8] == 1) {
            winner = 1;
            gameWon();
        } else if (grid[0] == 1 && grid[4] == 1 && grid[8] == 1) {
            winner = 1;
            gameWon();
        } else if (grid[2] == 1 && grid[4] == 1 && grid[6] == 1) {
            winner = 1;
            gameWon();
        } else if (usedFields == 9 && !hasWinner) {
            noWinner();
        } else {
            if (usedFields < 8 && !isSecondPlayerEnabled) {
                AITurn();
            }
        }
    }

    public void AITurn() {
        int x;
        do {
            x = getRandomField();
        } while (grid[x] != 0);

        switch (x) {
            case 0:
                btnPos11.setBackgroundResource(R.drawable.o);
                btnPos11.setClickable(false);
                grid[0] = 2;
                usedFields++;
                isOWon();
                break;
            case 1:
                btnPos12.setBackgroundResource(R.drawable.o);
                btnPos12.setClickable(false);
                grid[1] = 2;
                usedFields++;
                isOWon();
                break;
            case 2:
                btnPos13.setBackgroundResource(R.drawable.o);
                btnPos13.setClickable(false);
                grid[2] = 2;
                usedFields++;
                isOWon();
                break;
            case 3:
                btnPos21.setBackgroundResource(R.drawable.o);
                btnPos21.setClickable(false);
                grid[3] = 2;
                usedFields++;
                isOWon();
                break;
            case 4:
                btnPos22.setBackgroundResource(R.drawable.o);
                btnPos22.setClickable(false);
                grid[4] = 2;
                usedFields++;
                isOWon();
                break;
            case 5:
                btnPos23.setBackgroundResource(R.drawable.o);
                btnPos23.setClickable(false);
                grid[5] = 2;
                usedFields++;
                isOWon();
                break;
            case 6:
                btnPos31.setBackgroundResource(R.drawable.o);
                btnPos31.setClickable(false);
                grid[6] = 2;
                usedFields++;
                isOWon();
                break;
            case 7:
                btnPos32.setBackgroundResource(R.drawable.o);
                btnPos32.setClickable(false);
                grid[7] = 2;
                usedFields++;
                isOWon();
                break;
            case 8:
                btnPos33.setBackgroundResource(R.drawable.o);
                btnPos33.setClickable(false);
                grid[8] = 2;
                usedFields++;
                isOWon();
                break;
        }
    }

    public void isOWon() {

        if (grid[0] == 2 && grid[1] == 2 && grid[2] == 2) {
            checkSecondPlayerStatus();
        } else if (grid[3] == 2 && grid[4] == 2 && grid[5] == 2) {
            checkSecondPlayerStatus();
        } else if (grid[6] == 2 && grid[7] == 2 && grid[8] == 2) {
            checkSecondPlayerStatus();
        } else if (grid[0] == 2 && grid[3] == 2 && grid[6] == 2) {
            checkSecondPlayerStatus();
        } else if (grid[1] == 2 && grid[4] == 2 && grid[7] == 2) {
            checkSecondPlayerStatus();
        } else if (grid[2] == 2 && grid[5] == 2 && grid[8] == 2) {
            checkSecondPlayerStatus();
        } else if (grid[0] == 2 && grid[4] == 2 && grid[8] == 2) {
            checkSecondPlayerStatus();
        } else if (grid[2] == 2 && grid[4] == 2 && grid[6] == 2) {
            checkSecondPlayerStatus();
        }
    }

    public void checkSecondPlayerStatus() {
        if (isSecondPlayerEnabled) {
            winner = 2;
            gameWon();
        } else {
            winner = 2;
            gameLost();
        }
    }


    public int getRandomField() {
        Random ran = new Random();
        int x = ran.nextInt(8);
        return x;
    }

    public void gameWon() {
        gameInProgress = false;
        hasWinner = true;
        disableGrid();
        score = (System.currentTimeMillis() - date1) / 1000;
        Toast.makeText(this, "Score: " + score, Toast.LENGTH_SHORT).show();
        inputPlayerName();

    }

    public void gameLost() {
        hasWinner = true;
        disableGrid();
        gameLostDialog();
    }

    public void noWinner() {
        disableGrid();
        Toast.makeText(this, "No Winner.", Toast.LENGTH_SHORT).show();
    }

    public void resetGridArray() {
        for (int i = 0; i < 9; i++) {
            grid[i] = 0;
        }
    }

    public void inputPlayerName() {

        String whoWon;
        if (winner == 1) {
            whoWon = "X has won!";
        } else {
            whoWon = "O has won!";
        }
        final EditText enterName = new EditText(this);
        enterName.setSingleLine();
        FrameLayout container = new FrameLayout(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 60;
        params.rightMargin = 60;
        enterName.setLayoutParams(params);
        container.addView(enterName);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(whoWon)
                .setMessage("Please enter your name:")
                .setView(container)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        playerName = String.valueOf(enterName.getText());
                        database.highScoreDAO().insert(new HighScore(playerName, score));
                        lastScoreEntered = true;
                    }
                })
                .setCancelable(false)
                .create();
        dialog.show();
    }

    public void gameLostDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("You Lost!!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogDismissed = true;
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    public void disableGrid() {
        btnPos11.setClickable(false);
        btnPos12.setClickable(false);
        btnPos13.setClickable(false);
        btnPos21.setClickable(false);
        btnPos22.setClickable(false);
        btnPos23.setClickable(false);
        btnPos31.setClickable(false);
        btnPos32.setClickable(false);
        btnPos33.setClickable(false);
        gridDisabled = true;
    }

    public void enableGrid() {
        btnPos11.setClickable(true);
        btnPos12.setClickable(true);
        btnPos13.setClickable(true);
        btnPos21.setClickable(true);
        btnPos22.setClickable(true);
        btnPos23.setClickable(true);
        btnPos31.setClickable(true);
        btnPos32.setClickable(true);
        btnPos33.setClickable(true);
        gridDisabled = false;
    }

    public void resetGrid() {
        btnPos11.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
        btnPos12.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
        btnPos13.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
        btnPos21.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
        btnPos22.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
        btnPos23.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
        btnPos31.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
        btnPos32.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
        btnPos33.setBackgroundColor(ContextCompat.getColor(this, R.color.background));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putIntArray("grid", grid);
        outState.putInt("used_fields", usedFields);
        outState.putBoolean("has_winner", hasWinner);
        outState.putLong("date1", date1);
        outState.putBoolean("is_second_player_enabled", isSecondPlayerEnabled);
        outState.putInt("winner", winner);
        outState.putInt("on_turn", onTurn);
        outState.putInt("item_selected", itemSelected);
        outState.putBoolean("grid_disabled", gridDisabled);
        outState.putBoolean("last_score_entered", lastScoreEntered);
        outState.putLong("score", score);
        outState.putBoolean("dialog_dismissed", dialogDismissed);
        super.onSaveInstanceState(outState);
    }
}


