package s112011.galgelegsapp;


import android.os.Bundle;;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Keyboard extends Fragment implements View.OnClickListener {

    private Button bq,bw,be,br,bt,by,bu,bi,bo,bp,bå,ba,bs,bd,bf,bg,bh,bj,bk,bl,bæ,bø,
    bz,bx,bc,bv,bb,bn,bm;

    private ArrayList<String> Keys = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_keyboard, container, false);

        Button bq = (Button) root.findViewById(R.id.btnQ);
        bq.setOnClickListener(this);

        Button bw = (Button) root.findViewById(R.id.btnW);
        bw.setOnClickListener(this);

        Button be = (Button) root.findViewById(R.id.btnE);
        bw.setOnClickListener(this);

        Button br = (Button) root.findViewById(R.id.btnR);
        bw.setOnClickListener(this);

        Button bt = (Button) root.findViewById(R.id.btnT);
        bw.setOnClickListener(this);

        Button by = (Button) root.findViewById(R.id.btnY);
        bw.setOnClickListener(this);

        Button bu = (Button) root.findViewById(R.id.btnU);
        bw.setOnClickListener(this);

        Button bi = (Button) root.findViewById(R.id.btnI);
        bw.setOnClickListener(this);

        Button bo = (Button) root.findViewById(R.id.btnO);
        bw.setOnClickListener(this);

        Button bp = (Button) root.findViewById(R.id.btnP);
        bw.setOnClickListener(this);

        Button bå = (Button) root.findViewById(R.id.btnÅ);
        bw.setOnClickListener(this);

        Button ba = (Button) root.findViewById(R.id.btnA);
        bw.setOnClickListener(this);

        Button bs = (Button) root.findViewById(R.id.btnS);
        bw.setOnClickListener(this);

        Button bd = (Button) root.findViewById(R.id.btnD);
        bw.setOnClickListener(this);

        Button bf = (Button) root.findViewById(R.id.btnF);
        bw.setOnClickListener(this);

        Button bg = (Button) root.findViewById(R.id.btnG);
        bw.setOnClickListener(this);

        Button bh = (Button) root.findViewById(R.id.btnH);
        bw.setOnClickListener(this);

        Button bj = (Button) root.findViewById(R.id.btnJ);
        bw.setOnClickListener(this);

        Button bk = (Button) root.findViewById(R.id.btnK);
        bw.setOnClickListener(this);

        Button bl = (Button) root.findViewById(R.id.btnL);
        bw.setOnClickListener(this);

        Button bæ = (Button) root.findViewById(R.id.btnÆ);
        bw.setOnClickListener(this);

        Button bø = (Button) root.findViewById(R.id.btnØ);
        bw.setOnClickListener(this);

        Button bz = (Button) root.findViewById(R.id.btnZ);
        bw.setOnClickListener(this);

        Button bx = (Button) root.findViewById(R.id.btnX);
        bw.setOnClickListener(this);

        Button bc = (Button) root.findViewById(R.id.btnC);
        bw.setOnClickListener(this);

        Button bv = (Button) root.findViewById(R.id.btnV);
        bw.setOnClickListener(this);

        Button bb = (Button) root.findViewById(R.id.btnB);
        bw.setOnClickListener(this);

        Button bn = (Button) root.findViewById(R.id.btnN);
        bw.setOnClickListener(this);

        Button bm = (Button) root.findViewById(R.id.btnM);
        bw.setOnClickListener(this);
        return root;
    }


    @Override
    public void onClick(View v) {



    }
}
