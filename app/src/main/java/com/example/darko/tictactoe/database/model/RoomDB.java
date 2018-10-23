package com.example.darko.tictactoe.database.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {HighScore.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {

    public abstract HighScoreDAO highScoreDAO();

}
