package s112011.galgelegsapp.views;



import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import s112011.galgelegsapp.diverse.App;
import s112011.galgelegsapp.R;



/**
 * A simple {@link Fragment} subclass.
 */


public class Level_fragment extends Fragment implements View.OnClickListener {

    private Button letButton, middelButton, sværButton, drOrd;
    private Spil spil;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_level, container, false);

        spil = (Spil)(getActivity());
        if (!App.prefs.getBoolean("drOrd", false)) {
            letButton = (Button) root.findViewById(R.id.letButton);
            letButton.setVisibility(View.VISIBLE);
            letButton.setOnClickListener(this);

            middelButton = (Button) root.findViewById(R.id.middelButton);
            middelButton.setVisibility(View.VISIBLE);
            middelButton.setOnClickListener(this);

            sværButton = (Button) root.findViewById(R.id.sværButton);
            sværButton.setVisibility(View.VISIBLE);
            sværButton.setOnClickListener(this);
        } else {

            drOrd = (Button) root.findViewById(R.id.dr);
            drOrd.setVisibility(View.VISIBLE);
            drOrd.setOnClickListener(this);
        }

        return root;


    }


    @Override
    public void onClick(View v) {

        if (v == letButton) {
            spil.fragmentFrame(new Spil_fragment());
            spil.logik.setLevel(1);
            spil.startSpil();

        }
        if (v == middelButton) {
            spil.logik.setLevel(2);
            spil.fragmentFrame(new Spil_fragment());
            spil.startSpil();
        }
        if (v == sværButton) {
            spil.logik.setLevel(3);
            spil.fragmentFrame(new Spil_fragment());
            spil.startSpil();
        }
        if(v == drOrd){
            spil.logik.setLevel(0);
            spil.startDRspil();


        }
    }


}
