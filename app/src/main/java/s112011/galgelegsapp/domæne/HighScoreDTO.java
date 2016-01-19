package s112011.galgelegsapp.domæne;

import java.util.Comparator;

/**
 * Created by KimDrewes on 07-01-2016.
 */
public class HighScoreDTO implements Comparable<HighScoreDTO> {

    String name;
    int points;
    public HighScoreDTO(){
    }


    public HighScoreDTO(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }


    @Override
    public int compareTo(HighScoreDTO another) {
        return this.getPoints() - another.getPoints();
    }

    public static Comparator<HighScoreDTO> highScoreCompare = new Comparator<HighScoreDTO>() {
        @Override
        public int compare(HighScoreDTO lhs, HighScoreDTO rhs) {
            return rhs.compareTo(lhs);
        }
    };
}
