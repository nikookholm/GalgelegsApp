package s112011.galgelegsapp.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import s112011.galgelegsapp.R;

public class HovedmenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button spilBtn, highScoresBtn, indstillingBtn, feedbackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manu);

        spilBtn = (Button) findViewById(R.id.spilKnap);
        spilBtn.setOnClickListener(this);

        highScoresBtn = (Button) findViewById(R.id.highscoreKnap);
        highScoresBtn.setOnClickListener(this);

        indstillingBtn = (Button) findViewById(R.id.IndstillingerKnap);
        indstillingBtn.setOnClickListener(this);

        feedbackBtn = (Button) findViewById(R.id.feedbackKnap);
        feedbackBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == spilBtn) {
            Intent visLevels = new Intent(this, Spil.class);
            startActivity(visLevels);
        } else if (v == indstillingBtn) {
            Intent visIndstillinger = new Intent(this, Indstillinger_Activity.class);
            startActivity(visIndstillinger);
        } else if (v == highScoresBtn) {
            Intent visHighScores = new Intent(this, HighScores_Activity.class);
            startActivity(visHighScores);
        } else if (v == feedbackBtn) {
            Intent visFeedbackActivty = new Intent(this, Feedback_Activity.class);
            startActivity(visFeedbackActivty);
        }
    }

    @Override
    public void onBackPressed() {
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
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
