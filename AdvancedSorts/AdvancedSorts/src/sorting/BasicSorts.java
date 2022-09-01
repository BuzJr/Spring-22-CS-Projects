package sorting;

public class BasicSorts {
	
	/*
	 * Swaps the elements and indices i and j in list
	 * */
	private static<T> void swap(T[] list, int i, int j) {
		/*OPTIONAL TODO: IMPLEMENT THIS METHOD*/
	}
	
	/*
	 * Updates the elements of list to be in sorted order. Uses "bubble sort"
	 * */
	public static<T extends Comparable<T>> void bubbleSort(T[] list) {
		/* TODO: IMPLEMENT THIS METHOD */
		for(int i=0;i<list.length-2;i++) {
			for(int j=0;j<list.length-i-1;j++) {
				if(list[j].compareTo(list[j+1])>0) {
					T temp = list[j];
					list[j] = list[j+1];
					list[j+1] = temp;
				}
			}
		}
	}
	
	/*
	 * Updates the elements of list to be in sorted order. Uses "insertion sort"
	 * */
	public static<T extends Comparable<T>> void insertionSort(T[] list) {
		/* TODO: IMPLEMENT THIS METHOD */
		int size = list.length;

	    for (int i = 1; i < size; i++) {
	      T temp = list[i];
	      int j = i - 1;
	      while (j >= 0 && temp.compareTo(list[j])<0) {
	        list[j + 1] = list[j];
	        --j;
	      }
	      list[j + 1] = temp;
		}
	}
	
}
