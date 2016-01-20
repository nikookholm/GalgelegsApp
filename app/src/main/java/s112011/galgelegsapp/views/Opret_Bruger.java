package s112011.galgelegsapp.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import s112011.galgelegsapp.diverse.App;
import s112011.galgelegsapp.R;

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
        if(name.getText().toString().length() >= 1) {
            String text = name.getText().toString().replace(" ","");
            if(!text.toString().equals(text)){
                Toast.makeText(this, "Brugernavnet må ikke indeholde mellemrum", Toast.LENGTH_LONG)
                        .show();
            }

            App.editor.putString("username", name.getText().toString()).commit();
            App.editor.putInt("highscore", 0).commit();
            Intent i = new Intent(this, HovedmenuActivity.class);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(this, "Bruger navn skal indeholde mere end 1 tegn", Toast.LENGTH_SHORT)
                    .show();

        }
    }
}
