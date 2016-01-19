package s112011.galgelegsapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

public class Indstillinger_Activity extends AppCompatActivity implements OnClickListener {

    ///*
    //
    // Skal gennemtænkes, da det måske vil være bedre at bruge et decideret preferenceskærm
    // med et preference.xml fil.
    // Hvis der er tid
    //
    // */

    Button skiftBruger, signout, acceptBtn;
    EditText brugerNavn;
    Login login;
    public Activity a = this;
    TextView bruger, highscore;
    Switch rankSwitch;


    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indstillinger_);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();

//        Viser nuværende bruger
        bruger = (TextView) findViewById(R.id.brugernavn);

        bruger.setText(prefs.getString("username", "unknown"));

        // Viser højeste score med nuværende bruger
        highscore = (TextView) findViewById(R.id.highscore);
        highscore.setText("" + prefs.getInt("highscore", 0));

//        Switch der tænder og slukker for ranked play, der vil hentes et ord fra DRs webside istedet
          rankSwitch = (Switch) findViewById(R.id.switch1);
        rankSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editor.putBoolean("drOrd", true).commit();
                }
                else{
                    editor.remove("drOrd").commit();
                }
            }
        });

//       knap der skiftes ud med brugernavn- edittexten når der klikkes
        skiftBruger = (Button) findViewById(R.id.skiftBruger);
        skiftBruger.setOnClickListener(this);
        brugerNavn = (EditText) findViewById(R.id.brugerNavn);


        // Signout button -- skal fjernes hvis ikke signout kommer til at virke
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
//                System.out.println(login.googleApiClient.isConnected());
//                    login.VerifyLogOut();
//                login.connectionCallbacks.onConnected(Bundle.EMPTY);
//                login.signOut();
                Intent goToMainMenu = new Intent(a, Login.class);
                startActivity(goToMainMenu);
                System.out.println("vi er inde");
            }
        }
    }
}
