package s112011.galgelegsapp;


import android.content.ClipData;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class Spil extends AppCompatActivity {
    TextView timer;
    GalgeLogik logik;


    // Diverse varia bler til tid
    long startTime = 0L;
    long updateTime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;

    Handler timeHandler = new Handler();
    MenuItem hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);
        logik = new GalgeLogik(getApplicationContext());

        timer = (TextView) findViewById(R.id.timer);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        Fragment fragment = new Level_fragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentKeyboard, fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.quit :
                Toast.makeText(getApplicationContext(), "Quit", Toast.LENGTH_SHORT).show();
                logik.afslutSpil();
                fragmentFrame(new Result_Fragment());
                break;
            case R.id.hint :
                Toast.makeText(getApplicationContext(), logik.getHint(), Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    // Runnable der opdaterer tiden
    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updateTime = timeSwapBuff + timeInMilliseconds;
            logik.opdaterTid(updateTime);


            int secs = (int) (updateTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;

            timer.setText("" + mins + ":" + String.format("%02d", secs));

            timeHandler.postDelayed(this, 0);

            //Afbryder runnable når spillet er slut
            if (logik.erSpilletSlut()) {
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
    public void fragmentFrame(Fragment frag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentKeyboard, frag).commit();
    }

    // Starter logikken, sætter starttid., og begynder runnable;
    public void startSpil() {
        try {
            logik.nulstil();
        } catch (Exception e) {
            // burde ikke kunne ske, da denne kaldes fra knapper der givere ranked spil
            Toast.makeText(getApplicationContext(), "Kunne ikke loade ord, check internetforbindelsen", Toast.LENGTH_SHORT).show();
            finish();
        }
        startTime = SystemClock.uptimeMillis();
        timeHandler.postDelayed(updateTimerThread, 0);
        timer.setVisibility(View.VISIBLE);

    }

    public void startDRspil() {
        setProgressBarIndeterminate(true);
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    logik.nulstil();
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    return e;
                }
            }

            @Override
            protected void onPostExecute(Object titler) {
                Toast.makeText(getApplicationContext(), "Loading completed", Toast.LENGTH_SHORT).show();
                fragmentFrame(new Spil_fragment());
                startTime = SystemClock.uptimeMillis();
                timeHandler.postDelayed(updateTimerThread, 0);
                timer.setVisibility(View.VISIBLE);
            }

        }.execute();

    }
}







