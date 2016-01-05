package s112011.galgelegsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level_Activity extends AppCompatActivity implements View.OnClickListener {

    private Button letButton;
    private Button middelButton;
    private Button sværButton;
    GalgeLogik logik = new GalgeLogik();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_);

        letButton = (Button) findViewById(R.id.letButton);
        letButton.setOnClickListener(this);

        middelButton = (Button) findViewById(R.id.middelButton);
        middelButton.setOnClickListener(this);

        sværButton = (Button) findViewById(R.id.sværtButton);
        sværButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        if(v==letButton){

        }

        if(v==middelButton){

        }

        if(v==sværButton){

        }

    }
}
