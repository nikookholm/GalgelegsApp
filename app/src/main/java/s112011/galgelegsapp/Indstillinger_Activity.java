package s112011.galgelegsapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Indstillinger_Activity extends AppCompatActivity implements OnClickListener {

    Button skiftBruger, signout;
    TextView brugerNavn;
    Login login = new Login();
    public Activity a = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indstillinger_);

        skiftBruger = (Button)findViewById(R.id.skiftBruger);
        skiftBruger.setOnClickListener(this);

        brugerNavn =(TextView)findViewById(R.id.brugerNavn);

        signout = (Button)findViewById(R.id.button_sign_out);
        signout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    private class SignoutListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

                if(v == signout){
                    login.signOut();
                    Intent goToMainMenu = new Intent(a, Login.class);
                    startActivity(goToMainMenu);

                }
            }
        }
    }
