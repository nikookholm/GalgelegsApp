package s112011.galgelegsapp;


import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Result_Fragment extends Fragment {

    View root;
    TextView  points, time, word;
    Spil spil;
    ImageView resultView;
    SharedPreferences prefs;

    public Result_Fragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        spil = (Spil)getActivity();
        prefs = PreferenceManager.getDefaultSharedPreferences(spil.getApplicationContext());
        root =  inflater.inflate(R.layout.fragment_result_, container, false);
        System.out.println(spil.logik.getOrdet());

        resultView = (ImageView)root.findViewById(R.id.result);
        resultView.setImageResource(setResultImageResource());

        // ikke implementeret endnu
        points = (TextView) root.findViewById(R.id.points);
        points.setText("" + spil.logik.tælPoint());

        word = (TextView) root.findViewById(R.id.text);
        word.setText("Ordet der skulle gættes var " + spil.logik.getOrdet());

        time = (TextView) root.findViewById(R.id.time);
        time.setText(spil.logik.getTid() + " sekunder");


        spil.logik.gemHighScore(prefs.getString("username", "findes ikke"));
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
