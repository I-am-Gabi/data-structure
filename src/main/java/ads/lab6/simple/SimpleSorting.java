package ads.lab6.simple;

/**
 * A class for simple sorting methods
 */
public class SimpleSorting {

	/**
	 * Sort the array in place using the selection sort algorithm
	 */
	public static <AnyType extends Comparable<AnyType>> void selection(AnyType[] array) {
		for (int i = 0; i < array.length; i++) {
			int less_element = i;
			for (int j = i + 1; j < array.length; j++) {
				if (less(array[j], array[less_element])) less_element = j;
			}
			swap(array, i, less_element);
		}
	}
	
	/**
	 * Sort the array in place using the insertion sort algorithm
	 */
	public static <AnyType extends Comparable<AnyType>> void insertion(AnyType[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = i; j > 0 && less(array[j], array[j - 1]); j--)
				swap(array, j, j-1);
		}
	}

	/**
	 * Sort the array in place using the insertion sort algorithm
	 */
	public static <AnyType extends Comparable<AnyType>> void insertion_part(AnyType[] array, int lo, int hi) {
		for (int i = lo+1; i <= hi; i++) {
			for (int j = i; j > 0 && less(array[j], array[j - 1]); j--)
				swap(array, j, j-1);
		}
	}

	private static <AnyType extends Comparable<AnyType>> void print(AnyType[] array) {
		for (AnyType i: array) {
			System.out.print(i + " ");
		}
		System.out.println("");
	}

	/**
	 * Swap array[i] and array[j]
	 */
	private static <AnyType> void swap(AnyType[] array, int i, int j) {
		AnyType tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	public static void main(String[] args) {
		Integer[] array = { 5, 1, 9, 0};
		selection(array);
	}
}
