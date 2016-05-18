package ads.lab7;

import ads.util.TestClass;
import java.util.Scanner;

public class TestDisjointSets extends TestClass<TestDisjointSets> {

	private DisjointSets ds;
	private Scanner input;
	
	public TestDisjointSets() {
		input = new Scanner(System.in);
		System.out.print("Size of the disjoint set: ");
		int n = input.nextInt();
		ds = new DisjointSets(n);
		System.out.println(ds);
	}
	
	public void find() {
		System.out.print("find element: ");
		int n = input.nextInt();
		System.out.println("find(" + n + ") = " + ds.find(n));
		System.out.println();
		System.out.println(ds);
	}
	
	public void union() {
		System.out.print("first root: ");
		int r1 = input.nextInt();
		System.out.print("second root: ");
		int r2 = input.nextInt();		
		System.out.println("performing union(" + r1 + "," + r2 + ")");
		ds.union(r1, r2);
		System.out.println();
		System.out.println(ds);
	}

    public static void main(String[] args) {
    	TestDisjointSets test = new TestDisjointSets();
        test.tester();
    }	
}
