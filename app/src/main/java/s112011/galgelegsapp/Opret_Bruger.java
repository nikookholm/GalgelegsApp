package s112011.galgelegsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Opret_Bruger extends AppCompatActivity implements View.OnClickListener{

EditText name;
    Button accept;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opret__bruger);

        name = (EditText) findViewById(R.id.name);
        name.setText("Indtast bruger navn");

        accept = (Button) findViewById(R.id.accept);
        accept.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();

        editor.putString("username", name.getText().toString()).commit();
        editor.putInt("highscore",0).commit();
        Intent i = new Intent(this, MainManuActivity.class);
        startActivity(i);


    }
}
