package s112011.galgelegsapp.views;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.firebase.client.Firebase;

import s112011.galgelegsapp.R;

public class Feedback_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText feedbackBox;
    RatingBar rating;
    Button sendKnap;
    SharedPreferences prefs;


    Firebase feedbackBase = new Firebase("https://galgeapp.firebaseio.com/feedback");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_);
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        feedbackBox = (EditText) findViewById(R.id.feedBackText);

        sendKnap = (Button) findViewById(R.id.sendKnap);
        sendKnap.setOnClickListener(this);

rating = (RatingBar) findViewById(R.id.rating);
        rating.setRating((float) 3);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        if (v == sendKnap) {

            feedbackBase.child(prefs.getString("username", "findes ikke")).child("message").setValue(feedbackBox.getText().toString());
            feedbackBase.child(prefs.getString("username", "findes ikke")).child("stars").setValue(rating.getRating());
            Toast.makeText(this, " Tak for din feedback", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menu){
        if (menu.getItemId() == android.R.id.home){
            finish();
        }
        return false;
    }

}
