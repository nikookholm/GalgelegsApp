package s112011.galgelegsapp.views;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import s112011.galgelegsapp.R;
import s112011.galgelegsapp.diverse.App;

// Aktivitet der starter op og kigger på om der er en bruger allerede, og starte det skærmbillede op der skal være

public class MainActivity extends AppCompatActivity {

    ProgressDialog progress;
    String use;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);

        progress = new  ProgressDialog(this);
      use = App.prefs.getString("username", "sorry");

        Intent i;
        if (use == "sorry") {
            i = new Intent(this, Login.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "Ingen tidligere bruger", Toast.LENGTH_SHORT).show();
            finish();
        } else {

            progress.setMessage("Finder spillet frem.... ");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.show();
            setProgressBarIndeterminate(true);
            new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] params) {
                    while(App.fc.getEasy().size() == 0){
                        System.out.println(" Størrelsen af getEasy er " +App.fc.getEasy().size());
                        try {
                            Thread.sleep(40);
                        }catch (InterruptedException e){
                        // burde ikke ske
                        }

                    }
                    return null;

                }

                @Override
                protected void onPostExecute(Object o) {
                    super.onPostExecute(o);
                    Intent i = new Intent(getApplicationContext(), HovedmenuActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Loggin in as " + use, Toast.LENGTH_SHORT).show();
                    progress.hide();
                    finish();
                }
            }.execute();

        }

    }
}
