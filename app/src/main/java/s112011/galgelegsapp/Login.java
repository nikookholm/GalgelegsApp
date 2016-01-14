package s112011.galgelegsapp;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class Login extends AppCompatActivity{

    private Button offlineButton;
    GoogleSignInOptions googleOp;
    GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 0;
    SignInButton sign_in_button;
    public Activity a = this;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        offlineButton = (Button) findViewById(R.id.offlineButton);
        offlineButton.setOnClickListener(new offlineListener());

        // Anmoder om bruger id og email for at logge ind
        googleOp = new GoogleSignInOptions.Builder(GoogleSignInOptions.
                DEFAULT_SIGN_IN).requestEmail().build();

        // Opretter et GoogleAPI klient med adgang til google login
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new ConFailListener())
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleOp).build();

        sign_in_button = (SignInButton) findViewById(R.id.sign_in_button);
        findViewById(R.id.sign_in_button).setOnClickListener(new googleListener());

        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(googleOp.getScopeArray());
    }

    // Log ind verificering
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // Opretter en ny inner-class af ConFailListener
    private class ConFailListener implements GoogleApiClient.OnConnectionFailedListener{

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        }
    }
    // Opretter en ny inner-class af googleListener Button
    private class googleListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            signIn();
        }
    }
    // Opretter en ny inner-class af offlineListener Button
    private class offlineListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == offlineButton) {
                Intent goToMainMenu = new Intent(a , MainManuActivity.class);
                startActivity(goToMainMenu);
            }
        }
    }
}