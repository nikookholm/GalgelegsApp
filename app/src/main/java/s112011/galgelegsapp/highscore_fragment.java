package s112011.galgelegsapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class highscore_fragment extends Fragment implements Runnable{


    View rod;
    Button refresh;
    ListView list;
    HighScores_Activity ha = (HighScores_Activity)getActivity();
    ArrayAdapter adapter;

    public highscore_fragment() {
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
               TextView rank = (TextView) view.findViewById(R.id.rank);
               rank.setText("" + (position +1));

               TextView name = (TextView) view.findViewById(R.id.playerName);
               name.setText(ha.highScoresDAO.getScores().get(position).getName());

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
