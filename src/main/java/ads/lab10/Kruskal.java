package ads.lab10;

import java.util.*;

import ads.exception.EmptyHeapException;
import ads.exception.FullHeapException;
import ads.graph.*;
import ads.lab6.heap.BinaryHeap;
import ads.lab7.DisjointSets;

/**
 * A class for the Kruskal algorithm
 */
public class Kruskal {
	
	/**
	 * adds all the weighted edges of graph G to the minimum heap minHeap
	 */
	private static void fillHeap(BinaryHeap<WeightedEdge> minHeap, WeightedUnDiGraph G) throws FullHeapException {
		for ( int u = 0; u < G.nbVertices(); u++ ) {
			for ( Integer a : G.adjacents(u) ) {
				if ( u < a )
					minHeap.add(new WeightedEdge(u,a,G.weight(u, a)));
			}
		}
	}
	
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
		
		// a minimum-heap	
		BinaryHeap<WeightedEdge> minHeap = new BinaryHeap<WeightedEdge>(G.nbEdges(),c);
		// fill the minimum-heap with all the weighted edges from the graph G	
		fillHeap(minHeap, G);
		// disjoint sets of all the vertices of the graph G
		DisjointSets ds = new DisjointSets(G.nbVertices());

		Edge e;
		while (ds.numSets() > 1) {
			e = minHeap.deleteExtreme();

            if (ds.find(e.origin()) == ds.find(e.destination())) continue;

			mst.add(e);
			ds.union(ds.find(e.origin()), ds.find(e.destination()));
		}
		return mst;
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

		System.out.println();
		for ( Edge e : mst )
			System.out.print(e + " ");
		System.out.println();

		// expected output (the edges could show up in a different order)
		//
		// (3, 7) (5, 7) (4, 8) (1, 4) (6, 8) (2, 4) (0, 2) (3, 4)
		// (1, 4) (3, 7) (2, 4) (3, 4) (5, 7) (0, 2) (4, 8) (6, 8)

        G = new WeightedUnDiGraph(7);
		G.addEdge(0,1,2);
		G.addEdge(0,2,2);
		G.addEdge(0,3,1);

		G.addEdge(1,3,5);
		G.addEdge(1,4,1);

		G.addEdge(2,3,1);
		G.addEdge(2,5,2);

		G.addEdge(3,4,1);
		G.addEdge(3,6,5);
		G.addEdge(3,5,6);

		G.addEdge(4,6,3);

		G.addEdge(5,6,10);

		mst = mst(G);

		System.out.println();
		for ( Edge e : mst )
			System.out.print(e + " ");
		System.out.println();

        // (2, 3) (4, 6) (1, 4) (3, 4) (2, 5) (0, 3)
	}
}
