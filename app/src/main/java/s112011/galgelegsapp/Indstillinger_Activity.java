package s112011.galgelegsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Indstillinger_Activity extends AppCompatActivity implements OnClickListener {

    Button skiftBruger;
    TextView brugerNavn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indstillinger_);

        skiftBruger = (Button)findViewById(R.id.skiftBruger);
        skiftBruger.setOnClickListener(this);

        brugerNavn =(TextView)findViewById(R.id.brugerNavn);
    }

    @Override
    public void onClick(View v) {


    }
}
