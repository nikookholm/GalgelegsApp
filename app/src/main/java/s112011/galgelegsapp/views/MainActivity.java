package s112011.galgelegsapp.views;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import s112011.galgelegsapp.App;


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
            Toast.makeText(getApplicationContext(), "Ingen tidliger bruger", Toast.LENGTH_SHORT).show();
        } else {
            i = new Intent(this, HovedmenuActivity.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "Loggin in as " + use, Toast.LENGTH_SHORT).show();
        }

    }
}
