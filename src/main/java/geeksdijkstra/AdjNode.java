package geeksdijkstra;

/**
 * @author yusufu
 */
public class AdjNode {
    String vertex;
    int weight;
    boolean visited;

    AdjNode(String v, int w, boolean isVisited)
    {
        vertex = v;
        weight = w;
        visited = isVisited;
    }
    String getVertex() { return vertex; }
    int getWeight() { return weight; }
    public boolean isVisited() {return visited;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdjNode adjNode = (AdjNode) o;

        return vertex != null ? vertex.equals(adjNode.vertex) : adjNode.vertex == null;
    }

    @Override
    public int hashCode() {
        return vertex != null ? vertex.hashCode() : 0;
    }
}
