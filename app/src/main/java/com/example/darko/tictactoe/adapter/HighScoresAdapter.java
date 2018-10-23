package com.example.darko.tictactoe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.darko.tictactoe.R;
import com.example.darko.tictactoe.database.model.HighScore;

import java.util.List;

public class HighScoresAdapter extends ArrayAdapter<HighScore>{

    private static class ViewHolder {
        private TextView tvId;
        private TextView tvName;
        private TextView tvScore;
    }

    public HighScoresAdapter(Context context, int textViewResourceId, List<HighScore> items){
        super(context, textViewResourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.row_highscore, parent, false);

            holder = new ViewHolder();
            holder.tvId = (TextView) convertView.findViewById(R.id.tv_Id);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvScore = (TextView) convertView.findViewById(R.id.tv_score);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HighScore score = getItem(position);
        if (score != null) {
            holder.tvId.setText(Integer.toString(position + 1) + ".");
            holder.tvName.setText(score.getName());
            holder.tvScore.setText("Score: " + Long.toString(score.getScore()) + " sec");
        }

        return convertView;
    }

}
