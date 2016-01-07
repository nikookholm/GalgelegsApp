package s112011.galgelegsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by KimDrewes on 07-01-2016.
 */
class HighScoreAdapter extends ArrayAdapter<HighScoreDTO> {

    Activity context;
    ArrayList<HighScoreDTO> highscores;

    public HighScoreAdapter(Activity context, ArrayList<HighScoreDTO> highscores) {
        super(context, R.layout.highscore_elements);
        this.context = context;
        this.highscores = highscores;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.highscore_elements, null, true);

        TextView rank = (TextView) rowView.findViewById(R.id.rank);
        rank.setText((position + 1));

        TextView name = (TextView) rowView.findViewById(R.id.playerName);
        rank.setText(highscores.get(position).getName());

        TextView points = (TextView) rowView.findViewById(R.id.antalPoint);
        rank.setText(highscores.get(position).getPoints());

        return rowView;
    }



}
