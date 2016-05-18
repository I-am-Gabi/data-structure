package ads.lab6.quick;

import ads.lab6.simple.SimpleSorting;

/**
 * A class for the quicksort algorithm
 */
public class QuickSort {
	
	private static final int CUTOFF = 10;
	
	/**
	 * Sort the array in place using the quicksort algorithm
	 */
	public static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array) {
		sort(array, 0, array.length - 1);
	}

	/**
	 * Sort the portion array[lo, hi] in place using the quicksort algorithm
	 */	
	private static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array, int lo, int hi) {
		if (lo + CUTOFF > hi) {
			insertion(array, lo, hi);
		} else {
			if (hi <= lo) return;
			int part = partition(array, lo, hi);

			sort(array, lo, part - 1);
			sort(array, part + 1, hi);
		}
	}

	/**
	 * Partition the portion array[lo, hi] and return the index of the pivot
	 */
	private static <AnyType extends Comparable<AnyType>> int partition(AnyType[] array, int lo, int hi) {
		int pivot = median(array, lo, (hi+lo)/2, hi);
		int i = pivot + 1, j = hi;
		while (i < j) {
			if (more(array[j], array[pivot])) j--;
			else if (less(array[i], array[pivot])) i++;
			else swap(array, i, j);
		}
		swap(array, i, pivot);
		return i;
	}

	private static <AnyType extends Comparable<AnyType>> int median(AnyType[] array, int lo, int mid, int hi) {
		if (less(array[mid], array[lo]))
			swap(array, lo, mid);
		if (less(array[hi], array[lo]))
			swap(array, lo, hi);
		if (less(array[hi], array[mid]))
			swap(array, mid, hi);
		swap(array, mid, lo);
		return lo;
	}
	
	/**
	 * Sort array[lo, hi] in place using the insertion sort algorithm
	 */
	private static <AnyType extends Comparable<AnyType>> void insertion(AnyType[] array, int lo, int hi) {
		SimpleSorting.insertion_part(array, lo, hi);
	}

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private static boolean more(Comparable v, Comparable w) {
		return v.compareTo(w) >= 0;
	}

	/**
	 * Swap array[i] and array[j]
	 */
	private static <AnyType> void swap(AnyType[] array, int i, int j) {
		AnyType tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	private static <AnyType> void print(AnyType[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		Integer[] array = { 212, 35, 9, 231, 106, 109, 199, 67, 138, 221, 117, 152, 127, 65, 194, 36, 37, 93, 9, 173, 211, 39, 222, 183, 154, 166, 126, 134, 176, 31, 15, 48, 226, 159, 209, 90, 120, 243, 73, 240, 230, 13, 91, 235, 174, 145, 126, 103, 208, 36, 47, 34, 84, 174, 66, 91, 138, 41, 232, 134, 244, 75, 43, 44, 109, 97, 59, 141, 95, 197, 231, 136, 135, 136, 185, 191, 143, 101, 83, 67, 7, 183, 63, 45, 141, 178, 195, 132, 147, 41, 112, 1, 45, 14, 240, 127, 215, 111, 95, 230, 177, 78, 207, 96, 75, 27, 115, 205, 87, 80, 25, 250, 248, 169, 212, 204, 153, 207, 201, 189, 70, 119, 87, 181, 52, 224, 152, 138, 71, 228, 52, 114, 8, 243, 8, 195, 164, 2, 127, 241, 71, 46, 182, 180, 25, 93, 249, 178, 229, 141, 98, 219, 144, 224, 130, 201, 201, 37, 17, 3, 43, 216, 115, 57, 114, 71, 220, 248, 19, 73, 198, 216, 25, 135, 102, 179, 34, 153, 178, 52, 113, 250, 79, 166, 227, 19, 226, 239, 139, 239, 224, 225, 63, 128, 227, 232, 112, 39, 162, 134, 197, 63, 51, 234, 144, 53, 22, 7, 203, 95, 224, 142, 48, 193, 88, 187, 136, 158, 40, 21, 38, 141, 87, 187, 172, 155, 213, 192, 223, 121, 118, 175, 186, 41, 226, 128, 110, 59, 37, 76, 147, 223, 105, 121, 194, 58, 191, 190, 45, 124 };
		sort(array);
		print(array);
	}
}
