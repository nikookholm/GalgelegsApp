package s112011.galgelegsapp;

import android.content.Context;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


/**
 * Created by KimDrewes on 17-01-2016.
 *
 * Klasse der skal sørge for forbindelser til nettet samt firebase
 * Der er stadig en del oprydning der mangler
 */
public class FireConn {
    ArrayList<Runnable> observatører = new ArrayList<>();

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
        for (Runnable r: observatører){
            r.run();
        }
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
        fb.addValueEventListener(new ValueEventListener() {

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

    public static String hentUrl(String url) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder sb = new StringBuilder();
        String linje = br.readLine();
        while (linje != null) {
            sb.append(linje + "\n");
            linje = br.readLine();
        }
        return sb.toString();
    }

    public void hentOrdFraDr() throws Exception {
       ArrayList<String> drOrd = new ArrayList<>();
        String data = hentUrl("http://blok4.dk");
        System.out.println("data = " + data);

        data = data.replaceAll("<.+?>", " ").toLowerCase().replaceAll("[^a-zæøå]", " ");
        System.out.println("data = " + data);
        drOrd.clear();
        drOrd.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));

        // kører listen igennem for at fjerne dem der for er for korte
        for (int i = 0; i < drOrd.size(); i++){
            if (drOrd.get(i).length() < 5){
                drOrd.remove(i);

            }

        }


    }
}