package s112011.galgelegsapp;


import android.content.Intent;
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

    // Aktiviteten Spil kender til galgelogikken, derfor skal der oprettes et spilobjekt i fragmenten
    private Spil spil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        spil = (Spil)getActivity();

        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_level, container, false);

        Button letButton = (Button) root.findViewById(R.id.letButton);
        letButton.setOnClickListener(this);

        Button middelButton = (Button) root.findViewById(R.id.middelButton);
        middelButton.setOnClickListener(this);

        Button svæButton = (Button) root.findViewById(R.id.sværButton);
        svæButton.setOnClickListener(this);
        return root;

    }
    @Override
    public void onClick(View v) {
        if(v==letButton){
            Keyboard goToKeyboard = new Keyboard();
            getFragmentManager().beginTransaction().add(R.id.fragmentKeyboard, goToKeyboard);
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