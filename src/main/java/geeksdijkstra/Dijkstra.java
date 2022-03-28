package geeksdijkstra;


import java.util.*;




class Dijkstra {

    HashMap<AdjNode, ArrayList<AdjNode>> graphInstance;
    HashMap<String, Integer> paths;
    String source;

    public Dijkstra() {
        this.graphInstance = new HashMap<AdjNode, ArrayList<AdjNode>>();
    }

    public HashMap<String, Integer> getPaths() {
        return paths;
    }

    public HashMap<AdjNode, ArrayList<AdjNode>> getGraphInstance() {
        return graphInstance;
    }

    public void insertNeighbor(String from, String to, int weight, boolean twoway) {
        AdjNode fromNode = new AdjNode(from, 0,false);
        if(graphInstance.containsKey(fromNode)) {
            graphInstance.get(fromNode).add(new AdjNode(to, weight,false));
        } else {
            graphInstance.put(fromNode, new ArrayList<AdjNode>(Collections.singletonList(new AdjNode(to,weight,false))));
        }
        //maintain the 2 way edges 0 to 1 means 1 to 0 if it is undirected
        //since it is recursive function marked as false to not to do more recursion
        if(twoway) {
            insertNeighbor(to,from,weight,false);
        }
    }

    // Function to find the shortest path of all the
    // vertices from the source vertex for s source
    public void executeDijkstra(String s) {

        source = s;
        //reset paths cuz we will calculate it according to new source again
        paths = new HashMap<>();

        //to support character node names I had to use map, arraylist doesn't support chars
        graphInstance.entrySet().stream().forEach(entry -> {
            paths.put(entry.getKey().getVertex(),Integer.MAX_VALUE);
            entry.getValue().stream().forEach(adjNode -> {
                paths.put(adjNode.getVertex(),Integer.MAX_VALUE);
            });
        });
        int nodeCount = graphInstance.keySet().size();

        paths.put(source,0);


        PriorityQueue<AdjNode> distanceQueue = new PriorityQueue<>((v1, v2) -> v1.getWeight() - v2.getWeight());
        distanceQueue.add(new AdjNode(source, 0,false));

        while (distanceQueue.size() > 0) {
            //get smallest distance from queue
            AdjNode current = distanceQueue.poll();
            //System.out.printf("Go to smallest node in adj list %s weight: %d\n",current.getVertex(), current.getWeight());

            //dont try a node if it is visited already
            if(!graphInstance.containsKey(current) || current.isVisited()) {
                break;
            }
            //check node's neighbors
            for (AdjNode n : graphInstance.get(current)) {
                try {
                    //path holds total path from source to node + weight of node < total path of neighbor node
                    if (paths.get(current.getVertex()) + n.getWeight() < paths.get(n.getVertex())) {
                        paths.put(n.getVertex(),paths.get(current.getVertex()) + n.getWeight());
                        //add updated node paths to queue
                        distanceQueue.add(new AdjNode(n.getVertex(), paths.get(n.getVertex()),false));
                    }
                } catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }
    }

    public void printPaths() {
        System.out.println("Source   Vertex      Distance from Source");
        paths.entrySet().stream().forEach(entryset-> {
            System.out.println(source + "           "+ entryset.getKey() + "			 " + entryset.getValue());
        });
    }

    public double closenessCentrality() {
        double sum = paths.entrySet().stream().mapToInt(d-> d.getValue()).sum();
        return 1/sum;
    }

}


