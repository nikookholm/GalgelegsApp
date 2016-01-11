package s112011.galgelegsapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by KimDrewes on 07-01-2016.
 */
public class HighScoreDAO {

    public ArrayList<HighScoreDTO> highscores  = new ArrayList<>();
    ArrayList<Runnable> observatører = new ArrayList<>();


    public HighScoreDAO(){

//        for (int i = 0; i<10; i++ ){
//            highscores.add(new HighScoreDTO("Navn"+i, 68*i));
        }


    public ArrayList<HighScoreDTO> getScores(){
        return highscores;
    }

    public void addHighscore(HighScoreDTO score){
        highscores.add(score);
    }
}

