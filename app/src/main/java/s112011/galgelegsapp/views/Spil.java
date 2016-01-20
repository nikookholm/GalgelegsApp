package s112011.galgelegsapp.views;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import s112011.galgelegsapp.logik.GalgeLogik;
import s112011.galgelegsapp.R;


public class Spil extends AppCompatActivity {
    TextView timer;
    GalgeLogik logik = new GalgeLogik();

    Menu menu;
    // Diverse varia bler til tid
    long startTime = 0L;
    long updateTime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;

    ProgressDialog progress;
    Handler timeHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);


        timer = (TextView) findViewById(R.id.timer);
        progress = new ProgressDialog(this);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);


        fragmentFrame(new Level_fragment());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId() ) {

                  case android.R.id.home:
                      if(logik.erSpilletErIGang()) {
                          new AlertDialog.Builder(this)
                                  .setTitle("Forlad spillet?")
                                  .setMessage("Ønsker du at forlade spillet?")
                                  .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                      public void onClick(DialogInterface dialog, int which) {
                                          finish();
                                      }
                                  })
                                  .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                      public void onClick(DialogInterface dialog, int which) {

                                      }
                                  })
                                  .setIcon(R.mipmap.finalpic)
                                  .show();
                      }else{
                          finish();
                      }

                break;
            case R.id.quit:
                logik.afslutSpil();
                fragmentFrame(new Result_Fragment());
                break;
            case R.id.hint:
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
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu, menu);
        if (!logik.erSpilletErIGang()){
            menu.findItem(R.id.quit).setVisible(false);
            menu.findItem(R.id.hint).setVisible(false);
        }
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
            if(logik.erSpilletErIGang()){
                onCreateOptionsMenu(menu);
            }
        } catch (IOException e) {
            // burde ikke kunne ske, da denne kaldes fra knapper der givere ranked spil
            Toast.makeText(getApplicationContext(), "Kunne ikke loade ord, check internetforbindelsen", Toast.LENGTH_SHORT).show();
            finish();
        }
        startTime = SystemClock.uptimeMillis();
        timeHandler.postDelayed(updateTimerThread, 0);
        timer.setVisibility(View.VISIBLE);

    }

    public void startDRspil() {

        progress.setMessage("Henter ord :) ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        setProgressBarIndeterminate(true);
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    logik.nulstil();
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    return e;
                }
            }

            @Override
            protected void onPostExecute(Object titler) {
                progress.hide();
                Toast.makeText(getApplicationContext(), "Loading completed", Toast.LENGTH_SHORT).show();
                fragmentFrame(new Spil_fragment());
                startTime = SystemClock.uptimeMillis();
                timeHandler.postDelayed(updateTimerThread, 0);
                timer.setVisibility(View.VISIBLE);
                if(logik.erSpilletErIGang()){
                    onCreateOptionsMenu(menu);
                }

            }

        }.execute();

    }
}







