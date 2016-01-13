package s112011.galgelegsapp;


import android.media.Image;
import android.os.Bundle;
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
    TextView result, points, time, word;
    Spil spil;
    ImageView resultView;

    public Result_Fragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root =  inflater.inflate(R.layout.fragment_result_, container, false);
        spil = (Spil)getActivity();

        resultView = (ImageView)root.findViewById(R.id.result);
        resultView.setImageResource(setResultImageResource());




        points = (TextView) root.findViewById(R.id.points);
        word = (TextView) root.findViewById(R.id.text);


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
