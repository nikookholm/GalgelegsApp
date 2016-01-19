package s112011.galgelegsapp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Spil_fragment extends Fragment implements SensorEventListener, View.OnClickListener {

    private Button button;
    private ImageView imageView;
    private TextView synligtOrdText;
    private Spil spil;
    private View root;
    private SensorManager sManager;
    private long lastUpdate;

    // int array for billedeIDer
    private int[] galgeBilled = new int[]{R.mipmap.galge, R.mipmap.forkert1, R.mipmap.forkert2, R.mipmap.forkert3,
            R.mipmap.forkert4, R.mipmap.forkert5, R.mipmap.forkert6, R.mipmap.finalpic};


    // int array for buttonIDer
    int[] buttonId = new int[]{R.id.btnQ, R.id.btnW, R.id.btnE, R.id.btnR, R.id.btnT, R.id.btnY, R.id.btnU,
            R.id.btnI, R.id.btnO, R.id.btnP, R.id.btnÅ, R.id.btnA, R.id.btnS, R.id.btnD, R.id.btnF, R.id.btnG,
            R.id.btnH, R.id.btnJ, R.id.btnK, R.id.btnL, R.id.btnÆ, R.id.btnØ, R.id.btnZ, R.id.btnX, R.id.btnC,
            R.id.btnV, R.id.btnB, R.id.btnN, R.id.btnM};




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        spil = (Spil)getActivity();
        root = inflater.inflate(R.layout.spil_fragment, container, false);

        for (int i = 0; i < buttonId.length; i++) {
            button = (Button) root.findViewById(buttonId[i]);
            button.setOnClickListener(this);
        }

        imageView = (ImageView) root.findViewById(R.id.galgeView);
        imageView.setImageResource(galgeBilled[0]);
        synligtOrdText = (TextView) root.findViewById(R.id.ordTextView);
        synligtOrdText.setText(spil.logik.getSynligtOrd());

//        sManager = (SensorManager) getSystem

        return root;
    }



    @Override
    public void onClick(View v) {
        opdaterFragment(v);
        evaluerSpil();
    }

    private void opdaterFragment(View v){
        spil.logik.gætBogstav(((Button) v).getText().toString());
        imageView.setImageResource(galgeBilled[spil.logik.getAntalForkerteBogstaver()]);
        synligtOrdText.setText(spil.logik.getSynligtOrd());
        v.setVisibility(View.INVISIBLE);

    }
    private void evaluerSpil(){
        if(spil.logik.erSpilletSlut()){
            Fragment result = new Result_Fragment();
            spil.fragmentFrame(result);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float[] values = event.values;

        // Bevægelser

        float x = values[0];
        float y = values[1];
        float z = values [3];

        float accelerationSquareRoot = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH);

        long actualTime = event.timestamp;

        if(accelerationSquareRoot >= 2){
            if( actualTime - lastUpdate < 200){
                return;
            }
        }
        lastUpdate = actualTime;
//
//       Toast toast = Toast.makeText(this, "Du gav op", Toast.LENGTH_LONG).show());

        }

        String shake;
//        int sensorType = event.sensor.getType();

//        if(sensorType==Sensor.TYPE_ACCELEROMETER){
//            shake =
//w
//        }

//    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}


