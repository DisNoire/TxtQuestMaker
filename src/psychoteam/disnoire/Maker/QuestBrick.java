package psychoteam.disnoire.Maker;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * Created by DisNoire on 19.05.2017.
 */
public class QuestBrick implements Serializable{
    private String mainText = "empty";

    public QuestBrick(){}

    public void setMainText(String mainText) {
        this.mainText = TextFormater.format(mainText);
    }

    public String getMainText() {
        return mainText;
    }
}
