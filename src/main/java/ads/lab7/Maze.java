package ads.lab7;

import java.util.*;

/**
 * A class for generating a random maze using disjoint sets
 */
public class Maze {

	// the number of rows
	private int rows;
	
	// the number of columns
	private int columns;
	
	// the full grid (i.e. all the possible inner edges)
	// todas as coisas possiveis
	private List<Edge> grid;
	
	// the set of inner edges of the maze
	private Set<Edge> maze;
	
	/**
	 * generates a maze of size roiws X columns
	 */
	public Maze(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		maze = new HashSet<Edge>();
		grid = new ArrayList<Edge>();
		generateMaze();
	}
	
	/**
	 * This method generates the maze by filling the set maze.
	 * The method is the one explained during lecture.
	 * The grid is the collection from which the edges are randomly
	 * picked up, so it should be shuffled using the method
	 * Collections.shuffle
	 */
	private void generateMaze() {
		fillGrid();
		Collections.shuffle(grid);

		DisjointSets sets = new DisjointSets(columns * rows);
		Random rand = new Random();

		while (sets.numSets() > 1) {
			int position = rand.nextInt(grid.size());
			Edge edge = grid.remove(position);
			int u = sets.find(edge.x);
			int v = sets.find(edge.y);
			if (u == v)
				maze.add(new Edge(edge.x, edge.y));
			else {
				sets.union(u, v);
			}
		}

		while (!grid.isEmpty()) {
			Edge edge = grid.remove(0);
			maze.add(new Edge(edge.x, edge.y));
		}
	}

	private void fillGrid() {
		int cell = 0, next_col = 1, lineDown = cell + columns;
		for (; cell < (columns * (rows-1)); cell++, next_col++, lineDown++) {
			if (next_col % columns == 0) {
				grid.add(new Edge(cell, lineDown));
				continue;
			}
			grid.add(new Edge(cell, next_col));
			grid.add(new Edge(cell, lineDown));
		}
	}
	
	//////////////// toString (DON'T CHANGE THIS PART)
	
	/**
	 * returns a String representation of the maze
	 */
	public String toString() {
		String s = topbot();
		for ( int i = 0; i < rows - 1; i++ )
			s += vertical(i) + horizontal(i);
		return s += vertical(rows - 1) + topbot();
	}
	
	/**
	 * returns the top-bottom line of the maze
	 */
	private String topbot() {
		String s = "+";
	    for ( int i = 0; i < columns; i++ )
	    	s += "--+";
	    return s + "\n";
	}
	
	/**
	 * returns the String representation of
	 * the vertical edges of row number 'row'
	 */
	private String vertical(int row) {
		String s = null;
		if ( row == 0 )
			s = " ";
		else
			s = "|";
		for ( int i = row*columns; i < row*columns + columns - 1; i++ )
			if ( maze.contains(new Edge(i,i+1)) )
				s += "  |";
			else
				s += "   ";
	    if (row == rows - 1)
	        s += "   \n";
	    else
	        s += "  |\n";
		return s;
	}
	
	/**
	 * returns the String representation of
	 * the horizontal edges of row number 'row'
	 */	
	private String horizontal(int row) {
		String s = "+";
		for ( int i = row*columns; i < row*columns + columns; i++ )
			if ( maze.contains(new Edge(i,i+columns)) )
				s += "--+";
			else
				s += "  +";
		return s + "\n";
	}
	
	//////////////// main method: you can try various size of maze
	
	public static void main(String args[]) {
		Maze m = new Maze(10,20);
		System.out.println(m);
	}
}
