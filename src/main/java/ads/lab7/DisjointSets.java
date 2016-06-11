package ads.lab7;

/**
 * A class to handle disjoint sets
 */
public class DisjointSets {

	// Father or size: if faze[i] >= 0, faze[i] is the father of i in the up-tree
	// else i is the root of the up-tree and -father[i] is the size of the up-tree
	private int faze[];
	
	// the current number of subsets
	private int numSets;
	
	/**
	 * builds a disjoint set system of size n with
	 * the n initial single-element sets
	 * {0}, {1}, {2}, ..., {n-1}
	 */
	public DisjointSets(int n) {
		faze = new int[n];
		this.numSets = n;
		for (int i = 0; i < numSets; i++)
			faze[i] = -1;
	}
	
	/**
	 * returns the current number of
	 * subsets in the disjoint set system
	 */
	public int numSets() {
		int num_sets = 0, index = 0;
		while (index < faze.length) {
			if (faze[index] < 0) {
                num_sets++;
            }
            index++;
		}
		return num_sets;
	}
	
	/**
	 * returns the root of the
	 * up-tree containing i doing
	 * path compression
	 */
	public int find(int i) {
		if (faze[i] < 0 )
			return i;
		else
			return faze[i] = find(faze[i]);
	}

	/**
	 * performs the union of the two subsets
	 * of roots root1 and root2
	 */
	public void union(int root1, int root2) {
		if (faze[root1] <= faze[root2]) {
            int temp = faze[root2];
			faze[root2] = root1;
            faze[root1] += temp;
		} else {
            int temp = faze[root1];
			faze[root1] = root2;
            faze[root2] += temp;
		}
	}

	////////////// toString function, do not change this code!
	
	public String toString() {
		String s = "|";
		for ( int i = 0; i < faze.length; i++ )
			s += format(i) + "|";
		s += "\n+";
		for ( int i = 0; i < faze.length; i++ )
			s += "---+";
		s += "\n|";
		for ( int i = 0; i < faze.length; i++ )
			s += format(faze[i]) + "|";		
		return s;
	}
	
	private String format(int n) {
		if ( n >= 0 && n < 10 )
			return "  " + n;
		if ( n >= -9 )
			return " " + n;
		return Integer.toString(n);
	}	
}
