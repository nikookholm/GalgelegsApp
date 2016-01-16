package s112011.galgelegsapp;


import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;


public class Spil extends AppCompatActivity {
    TextView timer;
    static GalgeLogik logik = new GalgeLogik();

    // Diverse variabler til tid
    long startTime = 0L;
    long updateTime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;

    Handler timeHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        timer = (TextView) toolbar.findViewById(R.id.timer);

        Fragment fragment = new Level_fragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentKeyboard, fragment).commit();
    }

    // Runnable der opdaterer tiden
    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updateTime = timeSwapBuff + timeInMilliseconds;
            logik.opdaterTid(updateTime);
            System.out.println(logik.getTid());

            int secs = (int) (updateTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;

            timer.setText("" + mins + ":" + String.format("%02d", secs));
            timeHandler.postDelayed(this, 0);

            //Afbryder runnable når spillet er slut
            if(logik.erSpilletSlut()){
                timeHandler.removeCallbacks(updateTimerThread);

            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

   // Metoder der tillader andre andre objecter at sætte fragmentet
    public void fragmentFrame(Fragment frag){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentKeyboard, frag).commit();
}

    // Starter logikken, sætter starttid., og begynder runnable;
    public void startSpil(){
        logik.nulstil();
        startTime = SystemClock.uptimeMillis();
        timeHandler.postDelayed(updateTimerThread, 0);

    }






}
