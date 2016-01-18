package s112011.galgelegsapp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */


public class Level_fragment extends Fragment implements View.OnClickListener {

    private Button letButton, middelButton, sværButton, drOrd;
    private Spil spil;
    SharedPreferences prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        spil = (Spil) getActivity();

        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_level, container, false);

        if (!prefs.getBoolean("drOrd", false)) {
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
        Fragment spilFrag = new Spil_fragment();
        if (v == letButton) {
            spil.fragmentFrame(spilFrag);
            spil.logik.setLevel(1);
            spil.startSpil();

        }
        if (v == middelButton) {
            spil.logik.setLevel(2);
            spil.fragmentFrame(spilFrag);
            spil.startSpil();
        }
        if (v == sværButton) {
            spil.logik.setLevel(3);
            spil.fragmentFrame(spilFrag);
            spil.startSpil();
        }
        if(v == drOrd){
            spil.logik.setLevel(0);
            spil.startDRspil();
        }
    }


}
