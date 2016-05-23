package ads.lab10;

import java.util.*;

import ads.exception.EmptyHeapException;
import ads.exception.FullHeapException;
import ads.graph.*;
import ads.lab6.heap.BinaryHeap;

/**
 * A class for the Prim algorithm
 */
public class Prim {

    private static boolean[] market;

	/**
	 * returns the set all edges of an MST of the graph G
	 */
	public static Set<Edge> mst(WeightedUnDiGraph G) throws FullHeapException, EmptyHeapException {
		
		Set<Edge> mst = new HashSet<Edge>(); // the edges of the MST
		
		// to make a minimum-heap of weighted edges
		Comparator<WeightedEdge> c = new Comparator<WeightedEdge>() {
			public int compare(WeightedEdge e1, WeightedEdge e2) {
				return e2.compareTo(e1);
			}
		};
		
		// the minimum-heap of weighted edges
		BinaryHeap<WeightedEdge> minHeap = new BinaryHeap<WeightedEdge>(G.nbEdges(), c);
		
		// known[u] == true <==> u is known
		boolean known[] = new boolean[G.nbVertices()];

        int node = 0;
        known[node] = true;
        visit(G, minHeap, node);

        while (!minHeap.isEmpty()) {
            Edge e = minHeap.deleteExtreme();
            if (known[e.destination()]) continue;

            known[e.destination()] = true;
            System.out.println(e.origin() + " " + e.destination() + " " + G.weight(e.origin(), e.destination()));
            visit(G, minHeap, e.destination());
        }
        return mst;
	}

    private static void visit(WeightedUnDiGraph G, BinaryHeap<WeightedEdge> minHeap, int node) throws FullHeapException {
        for (int adj : G.adjacents(node))
            minHeap.add(new WeightedEdge(node, adj, G.weight(node, adj)));
    }

    /**
	 * for testing
	 */
	public static void main(String args[]) throws FullHeapException, EmptyHeapException {
		WeightedUnDiGraph G = new WeightedUnDiGraph(9);
		G.addEdge(0,2,4);
		G.addEdge(0,5,8);
		G.addEdge(1,4,2);
		G.addEdge(1,5,7);
		G.addEdge(1,7,6);
		G.addEdge(2,4,8);
		G.addEdge(2,5,11);
		G.addEdge(3,4,4);
		G.addEdge(3,6,10);
		G.addEdge(3,7,2);
		G.addEdge(3,8,14);
		G.addEdge(4,8,7);
		G.addEdge(5,7,1);
		G.addEdge(6,8,9);

		Set<Edge> mst = mst(G);
		
		for ( Edge e : mst )
			System.out.print(e + " ");
		System.out.println();
	}
	// expected output (the edges could show up in a different order)
	//
	// (7, 3) (0, 5) (8, 6) (5, 7) (4, 1) (4, 8) (0, 2) (3, 4)
}
