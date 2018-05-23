package psychoteam.disnoire.Controllers;

import psychoteam.disnoire.Graph.DirectedGraph;
import java.io.*;
import java.util.HashMap;

/**
 * Created by DisNoire on 29.05.2017.
 */
public class SaveLoad {

    public static void save(HashMap<String, DirectedGraph> graphs, String filename){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src\\psychoteam\\disnoire\\SavedFiles\\" + filename + ".ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(graphs);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.printf("Serialized HashMap data is saved in " + filename + ".ser" + "\n");
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public static HashMap<String, DirectedGraph> load(String  filename){
        try {
            HashMap<String, DirectedGraph> graphs = null;
            FileInputStream fileInputStream = new FileInputStream("src\\psychoteam\\disnoire\\SavedFiles\\" + filename + ".ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            graphs = (HashMap<String, DirectedGraph>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return graphs;
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
            return null;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
    }
}
