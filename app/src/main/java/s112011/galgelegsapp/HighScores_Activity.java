package s112011.galgelegsapp;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class HighScores_Activity extends AppCompatActivity {

   static HighScoreDAO highScoresDAO = new HighScoreDAO();
    ArrayList<HighScoreDTO> highScores = highScoresDAO.getScores();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores_);


        Fragment frag = new highscore_fragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame, frag)
                .commit();


        // Get a reference to our posts
        Firebase ref = new Firebase("https://galgeapp.firebaseio.com/highscores");

        // Attach an listener to read the data at our posts reference

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                ArrayList<HighScoreDTO> temp = new ArrayList();
                System.out.println("There are " + snapshot.getChildrenCount() + " entries");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    HighScoreDTO post = postSnapshot.getValue(HighScoreDTO.class);
                    temp.add(post);

                }

                highScoresDAO.highscores = temp;
                temp = null;

                for (Runnable r : highScoresDAO.observatører) {
                    r.run();
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }


}
