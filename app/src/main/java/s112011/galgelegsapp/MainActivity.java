package s112011.galgelegsapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    SharedPreferences myPrefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = myPrefs.edit();


        String use = myPrefs.getString("username", "sorry");

        Intent i;
        if(use == "sorry"){
             i = new Intent(this, Login.class);
             startActivity(i);
             Toast.makeText(getApplicationContext(), "Ingen tidliger bruger", Toast.LENGTH_SHORT).show();
            }
        else{
             i = new Intent(this, MainManuActivity.class);
             startActivity(i);
             Toast.makeText(getApplicationContext(), "Loggin in as " + use, Toast.LENGTH_SHORT).show();
        }

    }
}
