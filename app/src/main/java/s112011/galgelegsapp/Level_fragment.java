package s112011.galgelegsapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */


public class Level_fragment extends Fragment implements View.OnClickListener {

    private Button letButton, middelButton, sværButton;
    private Spil spil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        spil = (Spil)getActivity();

        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_level, container, false);

        Button letButton = (Button) root.findViewById(R.id.letButton);
        letButton.setOnClickListener(this);

        Button midButton = (Button) root.findViewById(R.id.middelButton);
        middelButton.setOnClickListener(this);

        Button svæButton = (Button) root.findViewById(R.id.sværtButton);
        svæButton.setOnClickListener(this);
        return root;


    }


    @Override
    public void onClick(View v) {
        if(v==letButton){
            spil.logik.setLevel(1);
        }
        if(v==middelButton){
            spil.logik.setLevel(2);
        }
        if(v==sværButton) {
            spil.logik.setLevel(3);
        }
    }
}
