package ads.lab6.heap;

import ads.exception.EmptyHeapException;

/**
 * A class for the heap sort algorithm.
 */
public class HeapSort {
	
	/**
	 * Sort the array in place using the heapsort algorithm
	 * Complexity: THETA( n.log(n) ) where n is the size of the array
	 */	
	public static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array) throws EmptyHeapException {
		BinaryHeap<AnyType> heap = new BinaryHeap<AnyType>(array);
		int index = array.length - 1;
		while (!heap.isEmpty())
			array[index--] = heap.deleteExtreme();
	}

	private static <AnyType> void print(AnyType[] array) {
		for (AnyType i: array) {
			System.out.print(i + " ");
		}
		System.out.println("");
	}

	public static void main(String[] args) throws EmptyHeapException {
		Integer[] array = { 11, 5, 10, 3, 6, 4, 7, 8, 9, 2, 1 };
		sort(array);
	}
}
