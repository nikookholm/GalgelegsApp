package s112011.galgelegsapp.views;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import s112011.galgelegsapp.domæne.HighScoreDTO;
import s112011.galgelegsapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Highscore_fragmen extends Fragment implements Runnable{


    View rod;
    Button refresh;
    ListView list;
    HighScores_Activity ha = (HighScores_Activity)getActivity();
    ArrayAdapter adapter;


    public Highscore_fragmen() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rod = inflater.inflate(R.layout.highscore_fragment, container, false);

        list = (ListView) rod.findViewById(R.id.listView2);

        ha.highScoresDAO.observatører.add(this);
        run();



        return rod;
    }

    @Override
    public void onDestroyView() {
        ha.highScoresDAO.observatører.remove(this);
        super.onDestroyView();
    }

    @Override
    public void run() {

        adapter = new ArrayAdapter<HighScoreDTO>(getActivity(), R.layout.highscore_elements,R.id.rank, ha.highScoresDAO.getScores()){
            @Override
            public View getView(int position,View cachedView, ViewGroup parent ){

                View view =  super.getView(position, cachedView,parent);
if(position % 2 == 0){
    view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
}else{
    view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
}

                TextView rank = (TextView) view.findViewById(R.id.rank);
                rank.setText("" + (position +1));

                TextView name = (TextView) view.findViewById(R.id.playerName);
                name.setText(ha.highScoresDAO.getScores().get(position).getName());
                if (name.getText().toString().length() > 15){
                    name.setTextSize(20);
                }
                if (name.getText().toString().length() > 25){
                    name.setTextSize(25);
                }

                TextView points = (TextView) view.findViewById(R.id.antalPoint);
                points.setText("" + ha.highScoresDAO.getScores().get(position).getPoints());
                return view;
            }



        };
        list.setAdapter(adapter);
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
