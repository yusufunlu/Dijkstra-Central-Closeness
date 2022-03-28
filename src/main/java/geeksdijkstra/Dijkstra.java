package geeksdijkstra;

import dijkstrayusuf.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

class Dijkstra {
    static class AdjNode {
        int vertex, weight;

        AdjNode(int v, int w)
        {
            vertex = v;
            weight = w;
        }
        int getVertex() { return vertex; }
        int getWeight() { return weight; }
    }

    // Function to find the shortest distance of all the
    // vertices from the source vertex S.
    public static int[] dijkstra(int V, ArrayList<ArrayList<AdjNode> > graph, int source) {
        int[] distance = new int[V];
        for (int i = 0; i < V; i++)
            distance[i] = Integer.MAX_VALUE;
        distance[0] = 0;

        PriorityQueue<AdjNode> distanceQueue = new PriorityQueue<>((v1, v2) -> v1.getWeight() - v2.getWeight());
        distanceQueue.add(new AdjNode(source, 0));

        while (distanceQueue.size() > 0) {
            AdjNode current = distanceQueue.poll();

            for (AdjNode n : graph.get(current.getVertex())) {
                if (distance[current.getVertex()] + n.getWeight() < distance[n.getVertex()]) {
                    distance[n.getVertex()] = n.getWeight() + distance[current.getVertex()];
                    distanceQueue.add(new AdjNode(n.getVertex(), distance[n.getVertex()]));
                }
            }
        }
        // If you want to calculate distance from source to
        // a particular target, you can return
        // distance[target]
        return distance;
    }

    public static void main(String[] args) throws IOException {
        int V = 9;
        ArrayList<ArrayList<AdjNode> > graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        int source = 0;

        ArrayList<List<String>> dataList = fillTestData();
        dataList.stream().forEach(data-> {
            graph.get(Integer.parseInt(data.get(0))).add(new AdjNode(Integer.parseInt(data.get(1)), Integer.parseInt(data.get(2))));
        });

        int[] distance = dijkstra(V, graph, source);
        // Printing the Output
        System.out.println("Source   Vertex      Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(source + "           "+ i + "			 " + distance[i]);
        }
    }

    public static ArrayList<List<String>> fillTestData() throws IOException {
        ArrayList<List<String>> records = new ArrayList<>();
        InputStream is = Graph.class.getResourceAsStream("/test.csv");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        for (String line; (line = reader.readLine()) != null;) {
            // Process line
            if(line.startsWith("#")) continue;
            String[] values = line.split(" ");
            records.add(Arrays.asList(values));
        }

        return records;
    }
}
