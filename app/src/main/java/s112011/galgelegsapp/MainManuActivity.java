package s112011.galgelegsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainManuActivity extends AppCompatActivity implements View.OnClickListener {

    Button spilBtn, highScoresBtn, indstillingBtn, feedbackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manu);

        spilBtn = (Button) findViewById(R.id.spilKnap);
        spilBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == spilBtn){
            Intent visLevels = new Intent(this, Level_Activity.class);
            startActivity(visLevels);
            }
    }
}
