package geeksdijkstra;

import dijkstrayusuf.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author yusufu
 */
public class DijkstraTest {

    public static void main(String[] args) throws IOException {

        Dijkstra dijkstra = new Dijkstra();
        ArrayList<List<String>> dataList = fillTestData();
        dataList.stream().forEach(data-> {
            dijkstra.insertNeighbor(data.get(0), data.get(1), Integer.parseInt(data.get(2)),true);
        });

        //Once data is filled don't waste it
        //I just re calculate the dijkstra then centrality closeness for alrady ready data
        dijkstra.getGraphInstance().keySet().stream().forEach(key-> {
            dijkstra.executeDijkstra(key.getVertex());
            dijkstra.printPaths();
            double closenessCentrality = dijkstra.closenessCentrality();

            DecimalFormat df = new DecimalFormat("#.###");
            System.out.printf("For Source %s Closeness Centrality: %s \n\n",key.getVertex(), df.format(closenessCentrality));
        });
    }

    public static ArrayList<List<String>> fillTestData() throws IOException {
        ArrayList<List<String>> records = new ArrayList<>();
        InputStream is = Graph.class.getResourceAsStream("/test3.csv");
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
