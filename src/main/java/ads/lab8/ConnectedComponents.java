package ads.lab8;

import ads.graph.UnDiGraph;

/**
 * A class to compute connected components of an undirected graph
 */
public class ConnectedComponents {
	/**
	 * returns the connected component
	 * label for each vertices of graph 'G'
	 */
	public static int[] find(UnDiGraph G) {
		int[] component = new int[G.nbVertices()];
		int label = 0, node = 0;
		while (node < component.length) {
			if (component[node] == 0) label++;
			setComponent(G, node++, label, component);
		}
		return component;
	}

	/**
	 * fill in the array 'component' with label 'label' for all vertices
	 * of graph 'G' which are in the same connected component than 'u'
	 */
	private static int[] setComponent(UnDiGraph G, int u, int label, int component[]) {
		if (component[u] != 0)
			return null;

		component[u] = label;
		for (Integer adj : G.adjacents(u)) {
			setComponent(G, adj, label, component);
		}
		return null;
	}
}
