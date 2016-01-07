package s112011.galgelegsapp;

import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.support.v4.app.Fragment;

public class Spil extends AppCompatActivity {

   static GalgeLogik logik = new GalgeLogik();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Toolbar will now take on default Action Bar characteristics
        setSupportActionBar(toolbar);

        Fragment fragment = new Level_fragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentGalge, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void startSpil(){

        Fragment keyboard = new Keyboard();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentKeyboard, keyboard).commit();

        logik.nulstil();
        //    starte galgelogikken
        //    skifte fragment level til billede
        //   Oprette Ordlinje
        //   skifte tomt fragment til tastetur

    }
}
