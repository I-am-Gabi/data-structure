package ads.lab8;

import ads.lab8.graph.DiGraph;
import ads.lab8.graph.UnDiGraph;
import sun.security.provider.certpath.Vertex;

import java.util.Collection;
import java.util.Iterator;

/**
 * A class to find cycle in directed and undirected graphs
 */
public class Cycles {

    /**
     * returns true if graph 'G' has a cycle
     */
    public static boolean hasCycle(DiGraph G) {
        boolean[] marked;

        if (G.nbVertices() == 0) return false;

        for (int v = 0; v < G.nbVertices(); v++) {
            marked = new boolean[G.nbVertices()];
            marked[v] = true;

            for (Integer adj : G.adjacents(v)) {
                if (hasCycle(G, v, adj, marked))
                    return true;
            }
        }
        return false;
    }

    /**
     * Private method to find a cycle
     */
    private static boolean hasCycle(DiGraph G, int root, int current_node, boolean[] marked) {
        if (current_node == root)
            return true;
        if (!G.adjacents(current_node).iterator().hasNext())
            return false;

        if (marked[current_node]) return true;

        for (Integer node : G.adjacents(current_node)) {
            marked[node] = true;
            return hasCycle(G, root, node, marked);
        }
        return false;
    }
	
	/**
	 * returns true if graph 'G' has a cycle
	 */
	public static boolean hasCycle(UnDiGraph G) {
        boolean[] marked;
        for (int v = 0; v < G.nbVertices(); v++) {
            marked = new boolean[G.nbVertices()];
            if (!marked[v]) {
                if (hasCycle(G, v, v, marked))
                    return true;
            }
        }
        return false;
	}

    private static boolean hasCycle(UnDiGraph G, int current_node, int old_node, boolean[] marked) {
        if (marked[current_node])
            return true;
        marked[current_node] = true;
        for (Integer adj : G.adjacents(current_node)) {
            if (hasCycle(G, adj, current_node, marked)) {
                if (adj != old_node) return true;
            }
        }
        return false;
    }
}
