package ads.lab9;

import java.util.*;
import ads.graph.DiGraph;

/**
 * A class to compute the topological sorting of an acyclic digraph
 */
public class TopSort {

	/**
     * returns a list of the vertices of G so that they
     * appear in topological order (from first to last)
     */
    public static List<Integer> sort(DiGraph G) {
        HashMap<Integer, Integer> graph = cloneGraph(G);
        List<Integer> sorted = new LinkedList<Integer>();

        while (!graph.isEmpty()) {
            Queue<Integer> zeros = find_zeros(graph, G.nbVertices());
            while (!zeros.isEmpty()) {
                int v = zeros.remove();
                sorted.add(v);
                graph.remove(v);
                for (int adj : G.adjacents(v)) {
                    if (graph.containsKey(adj)) {
                        graph.put(adj, graph.get(adj) - 1);
                    }
                }
            }
        }
        return sorted;
    }

    private static Queue<Integer> find_zeros(HashMap<Integer, Integer> graph, int vertices) {
        Queue<Integer> zeros = new LinkedList<Integer>();
        for (int v = 0; v < vertices; v++) {
            if (graph.containsKey(v) && graph.get(v) <= 0) zeros.add(v);
        }
        return zeros;
    }

	private static HashMap<Integer, Integer> cloneGraph(DiGraph G) {
        HashMap<Integer, Integer>queue = new HashMap<Integer, Integer>();
		for (int v = 0; v < G.nbVertices(); v++) {
			int degree = G.inDegree(v);
			queue.put(v, degree);
		}
		return queue;
	}
	
	/**
	 * a programm for testing
	 */
	public static void main(String[] args) {
		DiGraph a = new DiGraph(10);
		a.addEdge(0,9);
		a.addEdge(0,5);
		a.addEdge(1,3);
		a.addEdge(1,8);
		a.addEdge(2,6);
		a.addEdge(2,4);
		a.addEdge(5,9);
		a.addEdge(5,7);
		a.addEdge(7,9);
		a.addEdge(9,1);
		a.addEdge(3,2);
		a.addEdge(8,4);
		a.addEdge(7,6);
		System.out.println("topological sorting of a: " + sort(a));
		// expected output (not unique)
		// topological sorting of a: [0, 5, 7, 9, 1, 3, 8, 2, 6, 4]

		DiGraph b = new DiGraph(9);
		b.addEdge(0,5);
		b.addEdge(0,8);
		b.addEdge(1,3);
		b.addEdge(1,7);
		b.addEdge(3,7);
		b.addEdge(2,4);
		b.addEdge(4,6);
		b.addEdge(5,8);
		b.addEdge(1,0);
		b.addEdge(8,2);
		b.addEdge(7,6);
		b.addEdge(5,3);
		System.out.println("topological sorting of b: " + sort(b));
		// expected output (not unique)
		// topological sorting of b: [1, 0, 5, 8, 3, 2, 7, 4, 6]

		DiGraph c = new DiGraph(9);
		c.addEdge(6,7);
		c.addEdge(7,4);
		c.addEdge(4,8);
		c.addEdge(8,2);
		c.addEdge(2,1);
		c.addEdge(1,0);
		c.addEdge(0,5);
		c.addEdge(5,3);
		c.addEdge(6,4);
		c.addEdge(7,2);
		c.addEdge(8,3);
		c.addEdge(1,5);
		System.out.println("topological sorting of c: " + sort(c));
		// expected output (unique)
		// topological sorting of c: [6, 7, 4, 8, 2, 1, 0, 5, 3]
	}
}
