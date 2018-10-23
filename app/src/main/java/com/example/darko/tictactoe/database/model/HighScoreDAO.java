package com.example.darko.tictactoe.database.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface HighScoreDAO {

    @Query("SELECT * FROM HighScore ORDER BY score ASC LIMIT 15")
    List<HighScore> selectAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (HighScore highScore);

    @Delete
    void delete (HighScore highScore);
}
