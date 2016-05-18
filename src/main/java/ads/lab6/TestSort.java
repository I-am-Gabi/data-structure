package ads.lab6;

import ads.exception.EmptyHeapException;
import ads.lab6.heap.HeapSort;
import ads.lab6.merge.MergeSort;
import ads.lab6.quick.QuickSort;
import ads.lab6.simple.SimpleSorting;
import ads.util.SlowInteger;
import ads.util.TestClass;

/**
 * A class for interactive testing of sorting algorithms
 */
public class TestSort extends TestClass<TestSort> {
	
	// the size of the array
	private static final int SIZE = 250;
	private SlowInteger[] array;
	private SlowInteger[] unsorted;
	
	/**
	 * Build a ads.lab6.TestSort object to test various
	 * sorting methods
	 */
	public TestSort() {
		array = new SlowInteger[SIZE];
		unsorted = new SlowInteger[SIZE];
		// set the slowness of the compareTo
		// method on ads.util.SlowInteger objects
		SlowInteger.setSlowness(1);
		newArray();
	}
	
	public void heapsort() throws EmptyHeapException {
		long startTime = System.nanoTime();
		HeapSort.sort(array);
		long endTime = System.nanoTime();
		showArray();
		System.out.println("Execution time: " + ((endTime - startTime)/1000000) + " ms");
		restoreArray();
	}
	
	public void mergesort() {
		long startTime = System.nanoTime();
		MergeSort.sort(array);
		long endTime = System.nanoTime();
		showArray();
		System.out.println("Execution time: " + ((endTime - startTime)/1000000) + " ms");
		restoreArray();
	}
	
	public void quicksort() {
		long startTime = System.nanoTime();
		QuickSort.sort(array);
		long endTime = System.nanoTime();
		showArray();
		System.out.println("Execution time: " + ((endTime - startTime)/1000000) + " ms");
		restoreArray();
	}
	
	public void selectionsort() {
		long startTime = System.nanoTime();
		SimpleSorting.selection(array);
		long endTime = System.nanoTime();
		showArray();
		System.out.println("Execution time: " + ((endTime - startTime)/1000000) + " ms");
		restoreArray();
	}
	
	public void insertionsort() {
		long startTime = System.nanoTime();
		SimpleSorting.insertion(array);
		long endTime = System.nanoTime();
		showArray();
		System.out.println("Execution time: " + ((endTime - startTime)/1000000) + " ms");
		restoreArray();
	}

	
	public void showArray() {
		System.out.print("[ " + array[0]);
		for ( int i = 1; i < array.length; i++ )
			System.out.print(", " + array[i]);
		System.out.println("]");
	}
	
	public void newArray() {
		for ( int i = 0; i < array.length; i++ ) {
			array[i] = new SlowInteger(1 + (int) (Math.random() * SIZE));
			unsorted[i] = array[i];
		}
	}
	
	private void restoreArray() {
		for ( int i = 0; i < array.length; i++ ) {
			array[i] = unsorted[i];
		}
	}
	
    public static void main(String[] args) {
    	TestSort test = new TestSort();
        test.tester();
    }
}
