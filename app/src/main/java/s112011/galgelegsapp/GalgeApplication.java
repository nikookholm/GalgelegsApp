package s112011.galgelegsapp;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by KimDrewes on 11-01-2016.
 */
public class GalgeApplication extends Application {
    SharedPreferences myPrefs;
    SharedPreferences.Editor editor;


    Firebase bs = new Firebase("https://galgeapp.firebaseio.com/ordlist/");

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

        myPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = myPrefs.edit();

//        uploadDefaultHighscore();
//        uploadDefaultOrd();
    }

    private void uploadDefaultOrd() {
        bs.child("let").child("" + 0).setValue(new OrdDTO("hund", "dyr", "Den gør"));
        bs.child("let").child("" + 1).setValue(new OrdDTO("hest", "dyr", "kan ride på den"));
        bs.child("let").child("" + 2).setValue(new OrdDTO("søløve", "dyr", "lever i vandet og er med i cirkus"));

        bs.child("medium").child("" + 0).setValue(new OrdDTO("flodhest", "dyr", "Aggressivt dyr"));
        bs.child("medium").child("" + 1).setValue(new OrdDTO("pingvin", "dyr", "lever i kolde egne"));
        bs.child("medium").child("" + 2).setValue(new OrdDTO("sommerfugl", "dyr", "flyver rundt når det er varmt"));

        bs.child("hard").child("" + 0).setValue(new OrdDTO("dronte", "dyr", "uddød race, der fangede mad ved at hoppe i vandet og håbede på det bedste"));
        bs.child("hard").child("" + 1).setValue(new OrdDTO("gråspurv", "dyr", "lille fulg der lever i Danmark bl.a."));
        bs.child("hard").child("" + 2).setValue(new OrdDTO("leopard", "dyr", "kattedyr"));

    }

    private void uploadDefaultHighscore() {
        HighScoreDTO a = new HighScoreDTO("Super Awesome", 100000);
        HighScoreDTO b = new HighScoreDTO("Awesome", 90000);
        HighScoreDTO c = new HighScoreDTO("Super", 80000);
        HighScoreDTO d = new HighScoreDTO("Good", 70000);
        HighScoreDTO e = new HighScoreDTO("Ok", 60000);
        HighScoreDTO f = new HighScoreDTO("Average", 50000);
        HighScoreDTO g = new HighScoreDTO("Super", 40000);
        HighScoreDTO h = new HighScoreDTO("Bad", 30000);
        bs = new Firebase("https://galgeapp.firebaseio.com/highscores");
        bs.child(a.getName()).setValue(a);
        bs.child(b.getName()).setValue(b);
        bs.child(c.getName()).setValue(c);
        bs.child(d.getName()).setValue(d);
        bs.child(e.getName()).setValue(e);
        bs.child(f.getName()).setValue(f);
        bs.child(g.getName()).setValue(g);
        bs.child(h.getName()).setValue(h);
    }


}
