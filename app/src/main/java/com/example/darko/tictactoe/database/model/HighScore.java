package com.example.darko.tictactoe.database.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class HighScore {

    @PrimaryKey(autoGenerate = true)
    private int _id = 0;

    @ColumnInfo(name = "score")
    private long score;

    @ColumnInfo(name = "name")
    private String name;

    public HighScore(String name, long score){

        this.name = name;
        this.score = score;

    }

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public long getScore() {
        return score;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(long score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name + " - " + score;
    }
}
