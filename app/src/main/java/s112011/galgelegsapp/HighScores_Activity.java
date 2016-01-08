package s112011.galgelegsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HighScores_Activity extends AppCompatActivity {

    HighScoreDAO highScoresDAO = new HighScoreDAO();
    ArrayList<HighScoreDTO> highScores = highScoresDAO.getScores();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores_);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.highscore_elements, R.id.playerName, highScores) {
            @Override
            public View getView(int position, View cachedView, ViewGroup parent) {
                View view = super.getView(position, cachedView, parent);

                TextView rank = (TextView) view.findViewById(R.id.rank);
                rank.setText("" + (position + 1));

                TextView name = (TextView) view.findViewById(R.id.playerName);
                name.setText(highScores.get(position).getName());

                TextView points = (TextView) view.findViewById(R.id.antalPoint);
                points.setText("" + highScores.get(position).getPoints());

                return view;
            }
        };

        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
    }
}
