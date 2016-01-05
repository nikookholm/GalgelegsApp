package s112011.galgelegsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Spil extends AppCompatActivity {

   static GalgeLogik logik = new GalgeLogik();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);
    }
}
