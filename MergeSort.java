import java.util.Comparator;
import java.util.Arrays;

class MergeSort {

	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		int i = lo; int j = mid + 1;

		for (int k = lo; k <= hi; k++) {
			if (i > mid) aux[k] = a[j++];
			else if (j > hi) aux[k] = a[i++];
			else if (less(a[i], a[j])) aux[k] = a[i++];
			else aux[k] = a[j++];
		}
	}

	public static void sort(Comparable[] a) {
		int lo = 0; int hi = a.length - 1;
		Comparable[] aux = new Comparable[a.length];

		for (int i = 0; i < a.length; i++) {
			aux[i] = a[i];
		}
		
		sort(aux, a, lo, hi);
	}

	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		int mid = lo + (hi - lo) /2;

		if (lo == hi) return;
		
		sort(aux, a, lo, mid);
		sort(aux, a, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}

	// is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


	public static void main(String[] args) {
		Comparable[] a = new Comparable[]{7, 1};

		a = new Comparable[]{7, 2, 8};
		Comparable[] aux = new Comparable[a.length];

		for (int i = 0; i < a.length; i++) {
			aux[i] = a[i];
		}

		sort(a);
		System.out.println(Arrays.toString(a));
	}
}