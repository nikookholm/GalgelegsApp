package s112011.galgelegsapp;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;


public class Spil extends AppCompatActivity {

   static GalgeLogik logik = new GalgeLogik();
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Toolbar will now take on default Action Bar characteristics
        setSupportActionBar(toolbar);

HighScoreDAO dao = new HighScoreDAO();


        Fragment fragment = new Level_fragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentGalge, fragment).commit();

        transaction = getSupportFragmentManager().beginTransaction();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void setLowFrame(){
        Fragment frag = new Keyboard();
      transaction.add(R.id.fragmentKeyboard, new Keyboard()).commit();
}
}
