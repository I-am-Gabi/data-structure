package ads.lab10;

import java.util.*;

import ads.exception.EmptyHeapException;
import ads.exception.FullHeapException;
import ads.graph.*;
import ads.lab6.heap.BinaryHeap;

/**
 * A class for the Prim algorithm for unconnected graph
 */
public class PrimForest {

	/**
	 * returns the set all edges of an MST of the graph G
	 * the graph G may net be connected
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
		BinaryHeap<WeightedEdge> minHeap = new BinaryHeap<WeightedEdge>(G.nbEdges(),c);

		// known[u] == true <==> u is known
		boolean known[] = new boolean[G.nbVertices()];

		int node = hasUnknown(known);
		Edge e;

		while (node != -1) {
			known[node] = true;
			visit(G, minHeap, node, known);

			while (!minHeap.isEmpty()) {
				e = minHeap.deleteExtreme();
				if (known[e.destination()]) continue;

				known[e.destination()] = true;
				mst.add(e);
				visit(G, minHeap, e.destination(), known);
			}
			node = hasUnknown(known);
		}
		return mst;
	}

	private static int hasUnknown(boolean[] known) {
		for (int node = 0; node < known.length; node++) {
			if (!known[node]) return node;
		}
		return -1;
	}

	private static void visit(WeightedUnDiGraph G, BinaryHeap<WeightedEdge> minHeap, int node, boolean[] known) throws FullHeapException {
		for (Edge adj : G.incidents(node)) {
			if (known[adj.destination()]) continue;
			minHeap.add(new WeightedEdge(adj, G.weight(node, adj.destination())));
		}
	}

	/**
	 * for testing
	 */
	public static void main(String args[]) throws FullHeapException, EmptyHeapException {
		WeightedUnDiGraph G = new WeightedUnDiGraph(9);
		G.addEdge(0,2,4);
		G.addEdge(0,5,8);
		G.addEdge(2,5,11);
		
		G.addEdge(1,4,2);
		G.addEdge(1,7,6);
		G.addEdge(3,1,1);
		G.addEdge(3,4,4);
		G.addEdge(3,7,2);
		
		G.addEdge(6,8,9);

		Set<Edge> mst = mst(G);

		for ( Edge e : mst ) {
			System.out.print(e + " ");
		}
		System.out.println();
	}
	// expected output (the edges could show up in a different order)
	//
	// (0, 5) (3, 7) (1, 3) (1, 4) (6, 8) (0, 2)
	// (1, 3) (1, 4) (3, 7) (0, 5) (0, 2) (6, 8)
}
