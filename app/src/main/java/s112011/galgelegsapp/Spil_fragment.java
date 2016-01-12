package s112011.galgelegsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Spil_fragment extends Fragment implements View.OnClickListener {

    private Button button, bq, bw, be, br, bt, by, bu, bi, bo, bp, bå, ba, bs, bd, bf, bg, bh, bj, bk, bl, bæ, bø,
            bz, bx, bc, bv, bb, bn, bm;
    private ImageView imageView;
    private TextView synligtOrdText;
    Spil spil = new Spil();

    private int[] galgeBilled = new int[]{R.mipmap.galge, R.mipmap.forkert1, R.mipmap.forkert2, R.mipmap.forkert3,
            R.mipmap.forkert4, R.mipmap.forkert5, R.mipmap.forkert6, R.mipmap.finalpic};
    View root;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         root = inflater.inflate(R.layout.spil_fragment, container, false);

        int[] buttonId = new int[]{R.id.btnQ, R.id.btnW, R.id.btnE, R.id.btnR, R.id.btnT, R.id.btnY, R.id.btnU,
                R.id.btnI, R.id.btnO, R.id.btnP, R.id.btnÅ, R.id.btnA, R.id.btnS, R.id.btnD, R.id.btnF, R.id.btnG,
                R.id.btnH, R.id.btnJ, R.id.btnK, R.id.btnL, R.id.btnÆ, R.id.btnØ, R.id.btnZ, R.id.btnX, R.id.btnC,
                R.id.btnV, R.id.btnB, R.id.btnN, R.id.btnM};

        for (int i = 0; i < buttonId.length; i++) {
            button = (Button) root.findViewById(buttonId[i]);
            button.setOnClickListener(this);
        }


        imageView = (ImageView) root.findViewById(R.id.galgeView);
        imageView.setImageResource(galgeBilled[0]);
        synligtOrdText = (TextView) root.findViewById(R.id.ordTextView);

        return root;
    }



    @Override
    public void onClick(View v) {
       spil.logik.gætBogstav(((Button) v).getText().toString());
        imageView.setImageResource(galgeBilled[spil.logik.getAntalForkerteBogstaver()]);
        synligtOrdText.setText(spil.logik.getSynligtOrd());


    }
}


