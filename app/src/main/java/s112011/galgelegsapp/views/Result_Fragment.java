package s112011.galgelegsapp.views;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import s112011.galgelegsapp.App;
import s112011.galgelegsapp.HighScoreDAO;
import s112011.galgelegsapp.R;
import s112011.galgelegsapp.views.Spil;


/**
 * A simple {@link Fragment} subclass.
 */
public class Result_Fragment extends Fragment {

    HighScoreDAO highScoreDAO = new HighScoreDAO();
    View root;
    TextView  points, time, word;
    Spil spil;
    ImageView resultView;


    public Result_Fragment() {
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
        word.setText("Ordet der skulle gættes var " + spil.logik.getOrdet());

        // Viser hvor mange sekunder der blev brugt
        time = (TextView) root.findViewById(R.id.time);
        time.setText(spil.logik.getTid() + " sekunder");

        // Opdaterer point, samt skriver til firebase og til prefs
        points = (TextView) root.findViewById(R.id.points);
        points.setText("" + spil.logik.tælPoint());
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


}
