package dijkstrayusuf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author yusufu
 */
public class Graph {

    //nodes and edges
    HashMap<String, ArrayList<AdjNode>> graphInstance;

    public Graph() {
        this.graphInstance = new HashMap<String, ArrayList<AdjNode>>();
    }
    //node 0 connected to node 1 with 2 distance
    //node 0 connected to node 2 with 3 distance

    public void insertNeighbor(String from, String to,int weight ) {
        if(graphInstance.containsKey(from)) {
            graphInstance.get(from).add(new AdjNode(to, weight,false));
        } else {
            graphInstance.put(from, new ArrayList<>(Collections.singletonList(new AdjNode(to,weight,false))));
        }
    }

    public void execute(String  sourceId) {

        int count = graphInstance.keySet().size();

        ArrayList<AdjNode> adjNodes = new ArrayList<>();

        adjNodes.set(0,new AdjNode("0",0,false));

        PriorityQueue<AdjNode> jobQ = new PriorityQueue<>((r1, r2) -> r1.weight - r2.weight);


        while (jobQ.size() > 0) {
            AdjNode visited = jobQ.poll();

            //get neighbors and iterate on them

        }
    }

    public static void main() throws IOException {
        Graph graph = new Graph();
        ArrayList<List<String>> dataList = fillTestData();
        dataList.stream().forEach(data-> {
                graph.insertNeighbor(data.get(0),data.get(1),Integer.valueOf(data.get(2)));
        });


    }
    public static ArrayList<List<String>> fillTestData() throws IOException {
        ArrayList<List<String>> records = new ArrayList<>();
        InputStream is = Graph.class.getResourceAsStream("/test2.csv");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        for (String line; (line = reader.readLine()) != null;) {
            // Process line
            String[] values = line.split(" ");
            records.add(Arrays.asList(values));
        }

        return records;
    }


}

class AdjNode {
    String toVertexId;
    int weight;
    boolean visited;

    public AdjNode(String toVertexId, int weight, boolean visited) {
        this.toVertexId = toVertexId;
        this.weight = weight;
        this.visited = visited;
    }
}
