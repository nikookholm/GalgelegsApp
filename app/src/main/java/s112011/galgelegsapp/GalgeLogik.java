package s112011.galgelegsapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class GalgeLogik {
    private ArrayList<String> muligeOrd = new ArrayList<String>();
    public String ordet;
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private String synligtOrd;
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt;
    private boolean spilletErVundet;
    private boolean spilletErTabt;
    private int level;
    private ArrayList<String> letOrd = new ArrayList<String>();
    private ArrayList<String> middelOrd = new ArrayList<String>();
    private ArrayList<String> sværOrd = new ArrayList<String>();
    public ArrayList<String> getBrugteBogstaver() {
        return brugteBogstaver;
    }

    public String getSynligtOrd() {return synligtOrd;}
    public int getLevel(){return level;}
    public String getOrdet() {
        return ordet;
    }
    public int getAntalForkerteBogstaver() {
        return antalForkerteBogstaver;
    }
    public boolean erSidsteBogstavKorrekt() {
        return sidsteBogstavVarKorrekt;
    }
    public boolean erSpilletVundet() {
        return spilletErVundet;
    }
    public boolean erSpilletTabt() {
        return spilletErTabt;
    }
    public boolean erSpilletSlut() {
        return spilletErTabt || spilletErVundet;
    }


    public GalgeLogik() {

        letOrdValg();
        middelOrdvalg();
        sværOrdValg();
    }

    public void letOrdValg(){
        letOrd.add("hej");
        letOrd.add("dav");
    }

    public void middelOrdvalg(){
        middelOrd.add("sjov");
        middelOrd.add("vinter");
    }

    public void sværOrdValg(){
        sværOrd.add("medlemskab");
        sværOrd.add("refleksion");

    }

    public void nulstil() {

        brugteBogstaver.clear();
        antalForkerteBogstaver = 0;
        spilletErTabt = false;
        spilletErVundet = false;

        switch(level){

            case 1: level = 1;

                ordet = letOrd.get(new Random().nextInt(letOrd.size()));
                System.out.println(getOrdet());

                break;

            case 2: level = 2;

                ordet = middelOrd.get(new Random().nextInt(middelOrd.size()));

                break;

            case 3: level = 3;

                ordet = sværOrd.get(new Random().nextInt(sværOrd.size()));

                break;
        }

        /*brugteBogstaver.clear();
        antalForkerteBogstaver = 0;
        spilletErVundet = false;
        spilletErTabt = false;
        ordet = muligeOrd.get(new Random().nextInt(muligeOrd.size()));
        opdaterSynligtOrd();*/
    }


    private void opdaterSynligtOrd() {
        synligtOrd = "";
        spilletErVundet = true;
        for (int n = 0; n <
                ordet.length(); n++) {
            String bogstav = ordet.substring(n, n + 1);
            if (brugteBogstaver.contains(bogstav)) {
                synligtOrd = synligtOrd + bogstav;
            } else {
                synligtOrd = synligtOrd + "_ ";
                spilletErVundet = false;
            }
        }
    }

    public void gætBogstav(String bogstav) {
        bogstav = bogstav.toLowerCase();
        if (bogstav.length() != 1) return;
        System.out.println("Der gættes på bogstavet: " + bogstav);
        if (brugteBogstaver.contains(bogstav)) return;
        if (spilletErVundet || spilletErTabt) return;

        brugteBogstaver.add(bogstav);

        if (ordet.contains(bogstav)) {
            sidsteBogstavVarKorrekt = true;
            System.out.println("Bogstavet var korrekt: " + bogstav);
        } else {
            // Vi gættede på et bogstav der ikke var i ordet.
            sidsteBogstavVarKorrekt = false;
            System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
            antalForkerteBogstaver = antalForkerteBogstaver + 1;
            if (antalForkerteBogstaver > 6) {
                spilletErTabt = true;
            }
        }
        opdaterSynligtOrd();
    }

    public void logStatus() {
        System.out.println("---------- ");
        System.out.println("- ordet (skult) = " + ordet);
        System.out.println("- synligtOrd = " + synligtOrd);
        System.out.println("- forkerteBogstaver = " + antalForkerteBogstaver);
        System.out.println("- brugeBogstaver = " + brugteBogstaver);
        if (spilletErTabt) System.out.println("- SPILLET ER TABT");
        if (spilletErVundet) System.out.println("- SPILLET ER VUNDET");
        System.out.println("---------- ");
    }
    public void setLevel(int level ){
        this.level = level; System.out.println("Whaaat");
    }
}
