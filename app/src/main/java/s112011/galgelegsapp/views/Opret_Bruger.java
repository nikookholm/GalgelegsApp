package s112011.galgelegsapp.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import s112011.galgelegsapp.App;
import s112011.galgelegsapp.R;
import s112011.galgelegsapp.views.HovedmenuActivity;

public class Opret_Bruger extends AppCompatActivity implements View.OnClickListener{

EditText name;
    Button accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opret__bruger);

        name = (EditText) findViewById(R.id.name);

        accept = (Button) findViewById(R.id.accept);
        accept.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


       App.editor.putString("username", name.getText().toString()).commit();
        App.editor.putInt("highscore", 0).commit();
        Intent i = new Intent(this, HovedmenuActivity.class);
        startActivity(i);
    }
}
