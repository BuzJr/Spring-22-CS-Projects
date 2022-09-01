package priorityqueue;

public class HeapSort {
	
	/*
	 * Accepts a list of type T and updates that list to be in sorted order.
	 * */
	public static <T extends Comparable<T>> void heapSort(T[] list) {
		/* TODO: IMPLEMENT THIS METHOD */
		MinHeap<T> mh = new MinHeap<T>();
		for(int i=0;i<list.length;i++)
			mh.push(list[i]);
		for(int i=0;i<list.length;i++)
			list[i] = mh.poll();
		
	}

}
