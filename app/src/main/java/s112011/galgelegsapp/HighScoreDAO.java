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

    ArrayList<HighScoreDTO> highscores;


    public HighScoreDAO(){
        highscores = new ArrayList<>();
        for (int i = 0; i<10; i++ ){
            highscores.add(new HighScoreDTO("Navn"+i, 68*i));
        }

        Collections.sort(highscores, HighScoreDTO.highScoreCompare);

      for (int i = 0; i < 10; i++){
          System.out.println(highscores.get(i).getName() + " " + highscores.get(i).getPoints());
      }
    }

    public ArrayList<HighScoreDTO> getScores(){
        return highscores;
    }
}

