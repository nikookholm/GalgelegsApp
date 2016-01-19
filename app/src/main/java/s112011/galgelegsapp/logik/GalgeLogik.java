package s112011.galgelegsapp.logik;

import s112011.galgelegsapp.diverse.App;
import s112011.galgelegsapp.domæne.OrdDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GalgeLogik implements Runnable {

    public OrdDTO ordet;
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private String synligtOrd;
    private int point = 0;
    private boolean spilletErIGang = false;
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt;
    private boolean spilletErVundet;
    private boolean spilletErTabt;
    private int level;
    private long tid;
    private int hintCount;


   public  boolean erSpilletErIGang(){
       return spilletErIGang;
   }
    public int getHintCount(){
        return hintCount;
    }
    public GalgeLogik(){
    }

    public ArrayList<String> getBrugteBogstaver() {
        return brugteBogstaver;
    }

    public String getSynligtOrd() {
        return synligtOrd;
    }

    public int getLevel() {
        return level;
    }

    public String getOrdet() {
        System.out.println(ordet);
        return ordet.getOrd();
    }

    public String getHint() {
        if (hintCount == 0) {
            hintCount++;
            return ordet.getKategori();

        } else if (hintCount == 1) {
            hintCount++;
            return ordet.getHint();
        }
        return "Du har brugt dine ledetråde";
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


    public void nulstil() throws IOException {

        brugteBogstaver.clear();
        antalForkerteBogstaver = 0;
        spilletErTabt = false;
        spilletErVundet = false;
        spilletErIGang = true;
        point = 0;
        hintCount = 0;
        tid = 0;

        if(level != 0){
            App.fc.observatører.add(this);
            run();
        }
        else{

               ordet = App.fc.hentOrdFraDr();
            opdaterSynligtOrd();
            getSynligtOrd();
        }

    }


    private void opdaterSynligtOrd() {
        synligtOrd = "";
        spilletErVundet = true;
        for (int n = 0; n <
                ordet.getOrd().length(); n++) {
            String bogstav = ordet.getOrd().substring(n, n + 1);
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

        if (ordet.getOrd().contains(bogstav)) {
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

    public void setLevel(int level) {
        this.level = level;

    }

    public void opdaterTid(long timeInMillis) {
        tid = timeInMillis / 1000;
    }

    public long getTid() {
        return tid;
    }

    public int tælPoint() {
        if(erSpilletTabt()) return 0;
        point = getOrdet().length() * 100 * level;

        if (tid <= 15) {
            point = point * 10;
        } else if ((tid > 15) && (tid <= 30)) {
            point = point * 5;
        } else if (tid > 30 && tid <= 60) {
            point = (int) (point * 2.5);
        } else if (tid > 60 && tid <= 90) {
            point = (int) (point * 1.25);
        } else {
            point = (int) (point * 0.9);
        }

        if (hintCount == 0) {
            point = point * 3;
        } else if (hintCount == 1) {
            point = point * 2;
        } else {
            point = (int) (point * 0.9);
        }

        return point;
    }

public int getPoint(){
    return point;
}



    @Override
    public void run() {
        switch (level) {

            case 1:
                ;
                ordet = App.fc.getEasy().get(new Random().nextInt(App.fc.getEasy().size()));
                opdaterSynligtOrd();
                getSynligtOrd();
                break;

            case 2:
                level = 2;

                ordet = App.fc.getMedium().get(new Random().nextInt(App.fc.getMedium().size()));
                opdaterSynligtOrd();
                getSynligtOrd();
                break;

            case 3:
                level = 3;

                ordet = App.fc.getHard().get(new Random().nextInt(App.fc.getHard().size()));
                opdaterSynligtOrd();
                getSynligtOrd();
                break;
        }
    }

    public void afslutSpil(){
        spilletErTabt = true;

    }
}
