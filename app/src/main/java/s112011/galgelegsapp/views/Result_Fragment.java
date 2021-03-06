package s112011.galgelegsapp.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import s112011.galgelegsapp.diverse.App;
import s112011.galgelegsapp.domæne.HighScoreDAO;
import s112011.galgelegsapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Result_Fragment extends Fragment implements View.OnClickListener {

    HighScoreDAO highScoreDAO = new HighScoreDAO();
    View root;
    TextView  points, time, word;
    Spil spil;
    ImageView resultView;
    Button again;


    public Result_Fragment(){
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        spil = (Spil)getActivity();


        root =  inflater.inflate(R.layout.fragment_result_, container, false);

        // Slutbillede
        resultView = (ImageView)root.findViewById(R.id.result);
        resultView.setImageResource(setResultImageResource());

        // viser ordet der skulle gættes
        word = (TextView) root.findViewById(R.id.text);
        word.setText("Ord: " + spil.logik.getOrdet());

        // Viser hvor mange sekunder der blev brugt
        time = (TextView) root.findViewById(R.id.time);
        time.setText(spil.logik.getTid() + " sekunder");

        // Opdaterer point, samt skriver til firebase og til prefs
        points = (TextView) root.findViewById(R.id.points);
        points.setText("" + spil.logik.tælPoint());

        again = (Button) root.findViewById(R.id.again);
        again.setOnClickListener(this);
        highScoreDAO.gemHighScore(App.prefs.getString("username", "findes ikke"),spil.logik.getPoint());
        App.editor.putInt("highscore", spil.logik.tælPoint()).commit();

        return root;
    }

    public int setResultImageResource(){
        if(spil.logik.erSpilletTabt()){
            return R.mipmap.finalpic;
        }
        else{
            return R.mipmap.happy;
        }
    }


    @Override
    public void onClick(View v) {
        spil.fragmentFrame(new Level_fragment());
    }
}
