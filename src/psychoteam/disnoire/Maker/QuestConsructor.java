package psychoteam.disnoire.Maker;

import psychoteam.disnoire.Graph.DirectedGraph;

import java.util.HashMap;

/**
 * Created by DisNoire on 19.05.2017.
 */
public class QuestConsructor {

    private DirectedGraph mainDirectedGraph = new DirectedGraph();
    private DirectedGraph currentGraph = mainDirectedGraph;
    private HashMap<String, DirectedGraph> graphs = new HashMap<String, DirectedGraph>();


    public void newBrick(String mainText, String answer){
        if (null == currentGraph.getUpperVortex()) graphs.put(currentGraph.getId(), currentGraph);
        currentGraph.setData(mainText);
        currentGraph.addVertex(answer);
        currentGraph.getLowerGraph(answer).setId();
        graphs.put(currentGraph.getLowerGraph(answer).getId(), currentGraph.getLowerGraph(answer));
    }

    public void newBrick(String answer){
        currentGraph.addVertex(answer);
        currentGraph.getLowerGraph(answer).setId();
        graphs.put(currentGraph.getLowerGraph(answer).getId(), currentGraph.getLowerGraph(answer));
    }

    public void newBrick(String answer, String id, boolean smth){
        currentGraph.addVertex(answer,graphs.get(id));
    }

    public DirectedGraph getMainDirectedGraph() {
        return mainDirectedGraph;
    }

    public void setMainDirectedGraph(DirectedGraph mainDirectedGraph) {
        this.mainDirectedGraph = mainDirectedGraph;
    }

    public DirectedGraph getCurrentGraph() {
        return currentGraph;
    }

    public void setCurrentGraph(DirectedGraph currentGraph) {
        this.currentGraph = currentGraph;
    }

    public HashMap<String, DirectedGraph> getGraphs() {
        return graphs;
    }

    public void setGraphs(HashMap<String, DirectedGraph> graphs) {
        this.graphs = graphs;
    }

    public DirectedGraph getGraphById(String id){
        return graphs.get(id);
    }

}
