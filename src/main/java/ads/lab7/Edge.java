package ads.lab7;

/**
 * A class for the edges of the maze.
 * Notice that we have to carefully redefine the equals and hasCode
 * methods so we can use the HashSet class later in the Maze class
 */
public class Edge {

	public int x;
	public int y;
	
	/**
	 * builds the edge (x,y)
	 */
	public Edge(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	@Override
	public boolean equals(Object o) {
		Edge e = (Edge) o;
		return x == e.x && y == e.y;
	}
	
	@Override
	public int hashCode() {
		return x + 37*y;
	}
}
