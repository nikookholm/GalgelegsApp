package s112011.galgelegsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button offlineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        offlineButton = (Button) findViewById(R.id.offlineButton);
        offlineButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        if(v==offlineButton){
            Intent goToMainMenu = new Intent(this, MainManuActivity.class);
            startActivity(goToMainMenu);
        }
    }
}
