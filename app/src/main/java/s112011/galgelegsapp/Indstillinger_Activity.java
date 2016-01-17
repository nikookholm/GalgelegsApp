package s112011.galgelegsapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

public class Indstillinger_Activity extends AppCompatActivity implements OnClickListener {

    Button skiftBruger, signout, acceptBtn;
    EditText brugerNavn;
    Login login;
    public Activity a = this;
    TextView bruger, highscore;


    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indstillinger_);


        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();

        bruger = (TextView) findViewById(R.id.brugernavn);
        bruger.setText(prefs.getString("username", "unknown"));

        highscore = (TextView) findViewById(R.id.highscore);
        highscore.setText("" + prefs.getInt("highscore", 0));

        skiftBruger = (Button) findViewById(R.id.skiftBruger);
        skiftBruger.setOnClickListener(this);

        brugerNavn = (EditText) findViewById(R.id.brugerNavn);


        signout = (Button) findViewById(R.id.button_sign_out);
        signout.setOnClickListener(new SignOutListener());

    }

    @Override
    public void onClick(View v) {
        if (v == skiftBruger) {
            skiftBruger.setVisibility(View.GONE);
            brugerNavn.setVisibility(View.VISIBLE);

            brugerNavn.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        String navn = brugerNavn.getText().toString();
                        editor.putString("username", navn)
                                .commit();
                        editor.putInt("highscore", 0).commit();
                        skiftBruger.setVisibility(View.VISIBLE);
                        brugerNavn.setVisibility(View.GONE);

                        Toast.makeText(getApplicationContext(), prefs.getString("username", "ukendt"), Toast.LENGTH_SHORT).show();
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        }
    }

    private class SignOutListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            if (v == signout) {
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
