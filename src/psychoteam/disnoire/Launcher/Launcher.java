package psychoteam.disnoire.Launcher;

import psychoteam.disnoire.Controllers.Controller;
import psychoteam.disnoire.Maker.QuestConsructor;
import java.util.Scanner;

/**
 * Created by DisNoire on 29.05.2017.
 */
public class Launcher {
    private static Scanner scanner = new Scanner(System.in);
    private static String consoleEnterString = "";
    private static QuestConsructor questConsructor = Controller.getQuestConsructor();

    public static void launchProject(){
        Object[] tempkeys = questConsructor.getGraphs().keySet().toArray();
        for (int i = 0; i < questConsructor.getGraphs().size(); i++) {
            if (questConsructor.getGraphs().get(tempkeys[i]).getUpperVortex() == null)
                questConsructor.setCurrentGraph(questConsructor.getGraphs().get(tempkeys[i]));
        }
        while (!consoleEnterString.equals("end")) {
            if (questConsructor.getCurrentGraph().getData().getMainText().equals("empty")) System.out.println("the end");
            else System.out.println(questConsructor.getCurrentGraph().getData().getMainText());
            Object[] keys = questConsructor.getCurrentGraph().getLowerGraphs().keySet().toArray();
            for (int i = 1; i < questConsructor.getCurrentGraph().getLowerGraphs().size() + 1; i++) {
                System.out.println(i + ". " + keys[i - 1]);
            }
            consoleEnterString = scanner.nextLine();
            try {
                questConsructor.setCurrentGraph(questConsructor.getCurrentGraph().getLowerGraphs().
                        get(keys[Integer.parseInt(consoleEnterString)-1]));
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.err.println("No such answer");
            }

        }
    }
}
