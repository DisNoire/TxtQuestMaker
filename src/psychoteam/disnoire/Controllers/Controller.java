package psychoteam.disnoire.Controllers;

import psychoteam.disnoire.Graph.DirectedGraph;
import psychoteam.disnoire.Launcher.Launcher;
import psychoteam.disnoire.Maker.QuestConsructor;
import java.util.*;

/**
 * Created by DisNoire on 28.05.2017.
 */
public class Controller {
    private static Scanner scanner = new Scanner(System.in);
    private static String consoleEnterString = "";
    private static QuestConsructor questConsructor = new QuestConsructor();

    public static void launch(){
        System.out.println("    Welcome to TxtQuestMaker v 2.0" + "\n" +
                            "You can use following commands:" + "\n" +
                            "create newBrick           ~ creating main text for current brick and 1st answer" + "\n" +
                            "create newAnswer          ~ add new answer" + "\n" +
                            "goto *id*                 ~ go to brick with entered id" + "\n" +
                            "changeText                ~ change text of current brick" + "\n" +
                            "nextBrick *answer number* ~ go to next brick linked to answer" + "\n" +
                            "previousBrick             ~ go to previous brick" + "\n" +
                            "save *file name*          ~ save current project with following name" + "\n" +
                            "load *file name*          ~ load file with following name" + "\n" +
                            "launch                    ~ launch current project" + "\n" +
                            "list                      ~ lists all bricks" + "\n" +
                            "help                      ~ list of commands" + "\n" +
                            "" + "\n" +
                            "Program based on bricks. Each brick is text and answers to it." + "\n" +
                            "" + "\n" +
                            "");

        while (!consoleEnterString.equals("exit")) {
            try {
                System.out.println("Current brick is                 " + "id:" + questConsructor.getCurrentGraph().getId() +
                        "\n" + questConsructor.getCurrentGraph().getData().getMainText());
                Object[] keys = questConsructor.getCurrentGraph().getLowerGraphs().keySet().toArray();
                for (int i = 1; i < questConsructor.getCurrentGraph().getLowerGraphs().size() + 1; i++) {
                    System.out.println(i + ". " + (String) keys[i - 1]);
                }
                consoleEnterString = scanner.nextLine();
                String[] temp = new String[2];
                if (consoleEnterString.indexOf(' ') == -1) temp[0] = consoleEnterString;
                else temp = consoleEnterString.split(" ");
                if (temp[0].equals("create")) {
                    if (temp[1].equals("newBrick")) {
                        System.out.println("enter main text");
                        String mainText = scanner.nextLine();
                        System.out.println("enter answer");
                        String answer = scanner.nextLine();
                        questConsructor.newBrick(mainText, answer);
                    }
                    if (temp[1].equals("newAnswer")) {
                        System.out.println("enter answer");
                        String answer = scanner.nextLine();
                        questConsructor.newBrick(answer);
                    }
                    if (temp[1].equals("newConnection")) {
                        System.out.println("enter answer");
                        String answer = scanner.nextLine();
                        questConsructor.newBrick(answer, temp[2], true);
                    }
                }
                if (temp[0].equals("goto")) {
                    questConsructor.setCurrentGraph(questConsructor.getGraphById(temp[1]));
                }
                if (temp[0].equals("changeText")) {
                    System.out.println("enter new text:");
                    questConsructor.getCurrentGraph().setData(scanner.nextLine());
                }
                if (temp[0].equals("nextBrick")) {
                    questConsructor.setCurrentGraph(questConsructor.getCurrentGraph().getLowerGraphs().
                            get((String) keys[Integer.parseInt(temp[1]) - 1]));
                }
                if (temp[0].equals("previousBrick")) {
                    questConsructor.setCurrentGraph(questConsructor.getCurrentGraph().getUpperVortex());
                }
                if (temp[0].equals("save")) {
                    SaveLoad.save(questConsructor.getGraphs(), temp[1]);
                }
                if (temp[0].equals("load")) {
                    try {
                        questConsructor.setGraphs(SaveLoad.load(temp[1]));
                    } catch (NullPointerException e) {
                        System.err.println("Wrong name or damaged file. Try again...");
                    }
                    Object[] tempkeys = questConsructor.getGraphs().keySet().toArray();
                    for (int i = 0; i < questConsructor.getGraphs().size(); i++) {
                        if (questConsructor.getGraphs().get((String) tempkeys[i]).getUpperVortex() == null)
                            questConsructor.setCurrentGraph(questConsructor.getGraphs().get((String) tempkeys[i]));
                    }
                }
                if (temp[0].equals("launch")) {
                    Launcher.launchProject();
                }
                if (temp[0].equals("help")){
                    System.out.println(
                            "You can use following commands:" + "\n" +
                            "create newBrick           ~ creating main text for current brick and 1st answer" + "\n" +
                            "create newAnswer          ~ add new answer" + "\n" +
                            "goto *id*                 ~ go to brick with entered id" + "\n" +
                            "changeText                ~ change text of current brick" + "\n" +
                            "nextBrick *answer number* ~ go to next brick linked to answer" + "\n" +
                            "previousBrick             ~ go to previous brick" + "\n" +
                            "save *file name*          ~ save current project with following name" + "\n" +
                            "load *file name*          ~ load file with following name" + "\n" +
                            "launch                    ~ launch current project" + "\n" +
                            "help                      ~ list of commands" + "\n" +
                            "" + "\n" +
                            "Program based on bricks. Each brick is text and answers to it." + "\n" +
                            "" + "\n" +
                            "");
                }
                if (temp[0].equals("list")){
                    list(questConsructor.getCurrentGraph());
                }
                if (temp[0].equals("deleteAnswer")){
                    questConsructor.getCurrentGraph().getLowerGraphs().remove(keys[Integer.parseInt(temp[1]) - 1]);
                }
            }
            catch (Exception e){
                System.err.println("wrong command");
            }
        }
    }

    public static void list(DirectedGraph graph){
        char[] chartemp = new char[10];
        graph.getData().getMainText().getChars(0,9,chartemp,0);
        String temp = "";
        for (int i = 0; i<chartemp.length;i++){
            temp += chartemp[i];
        }
        System.out.println("#" + graph.getId() + "|" + temp + "...");
        if (graph.getUpperVortex() != null) System.out.println("Upper:" + "#" + graph.getUpperVortex().getId());
        else System.out.println("Main brick.");
        System.out.println("Lowers:");
        Object[] keys = graph.getLowerGraphs().keySet().toArray();
        for (int i = 1; i < graph.getLowerGraphs().size(); i++) {
            System.out.println("#" + graph.getLowerGraphs().get(keys[i]).getId());
        }
        System.out.println();
        for (int i = 1; i < graph.getLowerGraphs().size(); i++) {
            list(graph.getLowerGraphs().get(keys[i]));
        }
    }

    public static QuestConsructor getQuestConsructor() {
        return questConsructor;
    }
}
