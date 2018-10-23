package com.example.darko.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.darko.tictactoe.adapter.HighScoresAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.darko.tictactoe.MainActivity.database;

public class HighScoresActivity extends AppCompatActivity {

    @BindView(R.id.lv_scores)
    ListView lv_scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        ButterKnife.bind(this);

        HighScoresAdapter adapter = new HighScoresAdapter(this, 0, database.highScoreDAO().selectAll());
        lv_scores.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
