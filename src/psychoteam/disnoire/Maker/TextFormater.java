package psychoteam.disnoire.Maker;

import java.util.Arrays;

/**
 * Created by DisNoire on 19.05.2017.
 */
public class TextFormater {

    public static String format(String string){
        char[] temp = string.toCharArray();
        string = "";
        int i = 0;
        int counter = 0;
        int lastCutPoint = 0;
        while (i != temp.length){
            if (temp[i] != ' ') {
                i++;
                continue;
            }
            if (temp[i] == ' ') counter++;
            if (counter == 10){
                char[] temp2 = Arrays.copyOfRange(temp,lastCutPoint,i);
                for (int l = 0; l<temp2.length; l++){
                    string += Character.toString(temp2[l]);
                }
                counter = 0;
                lastCutPoint = i;
                string += "\n";
            }
            i++;
        }
        char[] temp2 = Arrays.copyOfRange(temp,lastCutPoint,i);
        for (int l = 0; l<temp2.length; l++){
            string += Character.toString(temp2[l]);
        }
        return string;
    }


}
