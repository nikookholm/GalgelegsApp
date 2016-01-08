package s112011.galgelegsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

public class Feedback_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText feedbackBox;
    Button sendKnap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_);

        feedbackBox = (EditText) findViewById(R.id.feedBackText);

        sendKnap = (Button) findViewById(R.id.sendKnap);
        sendKnap.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v == sendKnap){
            String feedback = feedbackBox.getText().toString();
            System.out.println(feedback);
        }
    }
}
