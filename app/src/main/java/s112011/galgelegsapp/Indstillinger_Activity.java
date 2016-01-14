package s112011.galgelegsapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

public class Indstillinger_Activity extends AppCompatActivity implements OnClickListener {

    Button skiftBruger, signout;
    TextView brugerNavn;
    Login login;
    public Activity a = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indstillinger_);
        skiftBruger = (Button)findViewById(R.id.skiftBruger);
        skiftBruger.setOnClickListener(this);

        brugerNavn =(TextView)findViewById(R.id.brugerNavn);

        signout = (Button)findViewById(R.id.button_sign_out);
        signout.setOnClickListener(new SignOutListener());
    }

    @Override
    public void onClick(View v) {

    }

    private class SignOutListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

                if(v == signout){
                    System.out.println(login.googleApiClient.isConnected());
//                    login.VerifyLogOut();
                    login.connectionCallbacks.onConnected(Bundle.EMPTY);
                    login.signOut();
                    Intent goToMainMenu = new Intent(a, Login.class);
                    startActivity(goToMainMenu);
                    System.out.println("vi er inde");

                }
            }
        }
    }
