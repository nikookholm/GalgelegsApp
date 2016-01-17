package s112011.galgelegsapp;

import android.content.Context;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by KimDrewes on 17-01-2016.
 */
public class FireConn {


    private ArrayList<OrdDTO> easy = new ArrayList<>();
    private ArrayList<OrdDTO> medium = new ArrayList<>();
    private ArrayList<OrdDTO> hard = new ArrayList<>();
    private ArrayList<HighScoreDTO> hsList;

    public FireConn() {
        læsOrdFraFB();
        hentScore();
    }

    public void læsOrdFraFB() {

        Firebase fb = new Firebase("https://galgeapp.firebaseio.com/ordlist");
        fb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<OrdDTO> temp = new ArrayList<>();
                for (DataSnapshot lette : dataSnapshot.child("let").getChildren()) {
                    OrdDTO ord = lette.getValue(OrdDTO.class);
                    temp.add(ord);
                }
                easy = temp;
                temp = new ArrayList<>();

                for (DataSnapshot medium : dataSnapshot.child("medium").getChildren()) {
                    OrdDTO ord = medium.getValue(OrdDTO.class);
                    temp.add(ord);
                }
                medium = temp;
                temp = new ArrayList<>();

                for (DataSnapshot hard : dataSnapshot.child("hard").getChildren()) {

                    OrdDTO ord = hard.getValue(OrdDTO.class);
                    temp.add(ord);
                }
                hard = temp;
                temp = null;
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public ArrayList<OrdDTO> getEasy() {
        return easy;
    }

    public ArrayList<OrdDTO> getMedium() {
        return medium;
    }

    public ArrayList<OrdDTO> getHard() {
        return hard;
    }

    public void gemScore(HighScoreDTO hs){
        Firebase fb = new Firebase("https://galgeapp.firebaseio.com/highscores");
        boolean userExists = false;
        for(HighScoreDTO dto : hsList){
          if (dto.getName().equals(hs.getName())){
              userExists = true;
              if(dto.getPoints()> hs.getPoints()){
                  fb.child(hs.getName()).setValue(hs);
              }
          }

        }
          if (!userExists){
              fb.child(hs.getName()).setValue(hs);
          }
    }

    public void hentScore(){
        Firebase fb = new Firebase("https://galgeapp.firebaseio.com/highscores");
        hsList = new ArrayList<>();
        fb.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot hs: dataSnapshot.getChildren()) {
                HighScoreDTO dto = hs.getValue(HighScoreDTO.class);
                    hsList.add(dto);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}