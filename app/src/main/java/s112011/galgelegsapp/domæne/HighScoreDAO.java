package s112011.galgelegsapp.domæne;

import java.util.ArrayList;
import java.util.Collections;

import s112011.galgelegsapp.connection.FireConn;
import s112011.galgelegsapp.domæne.HighScoreDTO;

/**
 * Created by KimDrewes on 07-01-2016.
 */
public class HighScoreDAO {

    public static ArrayList<HighScoreDTO> highscores  = new ArrayList<>();
    public static ArrayList<Runnable> observatører = new ArrayList<>();
    static FireConn fc = new FireConn();


    public HighScoreDAO(){

    }


    public ArrayList<HighScoreDTO> getScores(){
        Collections.sort(highscores, HighScoreDTO.highScoreCompare);
        return highscores;
    }

    public void addHighscore(HighScoreDTO score){
        highscores.add(score);
    }

    public static void gemHighScore(String username, int point){
        fc.gemScore(new HighScoreDTO(username, point));
    }



}

