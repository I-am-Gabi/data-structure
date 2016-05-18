package ads.lab6.merge;

import sun.font.TrueTypeFont;

/**
 * A class for the recursive merge sort algorithm.
 */
public class MergeSort {

	/**
	 * Sort the array using the recursive merge sort algorithm.
	 * This algorithm is not in place and needs an auxiliary array of
	 * the same size than the array to sort
	 * Complexity: THETA( n.log(n) ) where n is the size of the array
	 */		
	public static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array) {
		AnyType[] tmp = (AnyType[]) new Comparable[array.length];
		sort(array, tmp, 0, array.length - 1);
	}
	
	/**
	 * Sort the array in the range [lo, hi] using the portion [lo, hi]
	 * of the auxiliary array tmp
	 * Complexity: THETA( n.log(n) )
	 * where n = hi - lo + 1
	 */
	private static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array, AnyType[] tmp, int lo, int hi) {
		if (hi <= lo) return;
		int m = lo + (hi - lo)/2;

		sort(array, tmp, lo, m);
		sort(array, tmp, m + 1, hi);
		merge(array, tmp, lo, m, hi);
	}
	
	/**
	 * Merge array[lo, mid] and array[mid+1, hi] into tmp[lo, hi]
	 * and copy back the result into array[lo, hi]
	 * Precondition: array[lo, mid] and array[mid+1, hi] are sorted
	 * Complexity: THETA( n ) where n = hi - lo + 1
	 */
	private static <AnyType extends Comparable<AnyType>> void merge(AnyType[] array, AnyType[] tmp, int lo, int mid, int hi) {
		int first_array, second_array, index_tmp;
		first_array = lo;
		second_array = mid + 1;
		index_tmp = 0;

		while (first_array <= mid && second_array <= hi) {
			if (less(array[first_array], array[second_array]))
				tmp[index_tmp] = array[first_array++];
			else
				tmp[index_tmp] = array[second_array++];
			index_tmp++;
		}

		while (first_array <= mid)
			tmp[index_tmp++] = array[first_array++];

		while (second_array <= hi) {
			tmp[index_tmp++] = array[second_array++];
		}

		transfer(tmp, array, lo, hi);
	}
	
	/**
	 * Copy the elements from tmp[lo, hi] into array[lo, hi]
	 * Complexity: THETA( n ) where n = hi - lo + 1
	 */
	private static <AnyType> void transfer(AnyType[] tmp, AnyType[] array, int lo, int hi) {
		for (int i = lo, j = 0; i <= hi; i++, j++) {
			array[i] = tmp[j];
		}
	}

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}


	public static void main(String[] args) {
		Integer[] array = { 212, 35, 9, 231, 106, 109, 199, 67, 138, 221, 117, 152, 127, 65, 194, 36, 37, 93, 9, 173, 211, 39, 222, 183, 154, 166, 126, 134, 176, 31, 15, 48, 226, 159, 209, 90, 120, 243, 73, 240, 230, 13, 91, 235, 174, 145, 126, 103, 208, 36, 47, 34, 84, 174, 66, 91, 138, 41, 232, 134, 244, 75, 43, 44, 109, 97, 59, 141, 95, 197, 231, 136, 135, 136, 185, 191, 143, 101, 83, 67, 7, 183, 63, 45, 141, 178, 195, 132, 147, 41, 112, 1, 45, 14, 240, 127, 215, 111, 95, 230, 177, 78, 207, 96, 75, 27, 115, 205, 87, 80, 25, 250, 248, 169, 212, 204, 153, 207, 201, 189, 70, 119, 87, 181, 52, 224, 152, 138, 71, 228, 52, 114, 8, 243, 8, 195, 164, 2, 127, 241, 71, 46, 182, 180, 25, 93, 249, 178, 229, 141, 98, 219, 144, 224, 130, 201, 201, 37, 17, 3, 43, 216, 115, 57, 114, 71, 220, 248, 19, 73, 198, 216, 25, 135, 102, 179, 34, 153, 178, 52, 113, 250, 79, 166, 227, 19, 226, 239, 139, 239, 224, 225, 63, 128, 227, 232, 112, 39, 162, 134, 197, 63, 51, 234, 144, 53, 22, 7, 203, 95, 224, 142, 48, 193, 88, 187, 136, 158, 40, 21, 38, 141, 87, 187, 172, 155, 213, 192, 223, 121, 118, 175, 186, 41, 226, 128, 110, 59, 37, 76, 147, 223, 105, 121, 194, 58, 191, 190, 45, 124 };
		sort(array);
	}
}
