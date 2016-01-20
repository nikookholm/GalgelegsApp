package s112011.galgelegsapp.views;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import s112011.galgelegsapp.diverse.App;

// Aktivitet der starter op og kigger på om der er en bruger allerede, og starte det skærmbillede op der skal være

public class MainActivity extends AppCompatActivity {

    SharedPreferences myPrefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String use = App.prefs.getString("username", "sorry");

        Intent i;
        if (use == "sorry") {
            i = new Intent(this, Login.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "Ingen tidligere bruger", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            i = new Intent(this, HovedmenuActivity.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "Loggin in as " + use, Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
