package psychoteam.disnoire.Graph;

/**
 * Created by DisNoire on 25.05.2017.
 */
import psychoteam.disnoire.Maker.QuestBrick;
import java.io.Serializable;
import java.util.*;

public class DirectedGraph implements Serializable {

    private DirectedGraph upperVortex = null;
    private QuestBrick data = new QuestBrick();
    private HashMap<String, DirectedGraph> lowerGraphs = new HashMap<String,DirectedGraph>();
    private String id = "00000000";

    public DirectedGraph(){
        setId();
    }

    public void addVertex(String vertexName) {
        if (!lowerGraphs.containsKey(vertexName)) {
            lowerGraphs.put(vertexName, new DirectedGraph());
            lowerGraphs.get(vertexName).setUpperVortex(this);
        }
        else new IllegalArgumentException("Already has that answer!");
    }

    public void addVertex(String vertexName, DirectedGraph directedGraph) {
        if (!lowerGraphs.containsKey(vertexName)) {
            lowerGraphs.put(vertexName, directedGraph);
        }
        else new IllegalArgumentException("Already has that answer!");

    }

    public DirectedGraph getLowerGraph(String key) {
        return lowerGraphs.get(key);
    }

    public HashMap<String, DirectedGraph> getLowerGraphs() {
        return lowerGraphs;
    }

    public DirectedGraph getUpperVortex() {
        return upperVortex;
    }

    public void setUpperVortex(DirectedGraph upperVortex) {
        this.upperVortex = upperVortex;
    }

    public DirectedGraph previousVertex(){
        return upperVortex;
    }

    public DirectedGraph nextVertex(String key){
        return lowerGraphs.get(key);
    }

    public void setData(String data) {
        this.data.setMainText(data);
    }

    public QuestBrick getData() {
        return data;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        String temp = "";
        for (int i = 0; i<8; i++){
            temp += Integer.toString(new Random().nextInt(10));
        }
        this.id = temp;
    }
}
