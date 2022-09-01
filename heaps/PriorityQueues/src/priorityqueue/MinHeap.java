package priorityqueue;

import java.util.ArrayList;

public class MinHeap<T extends Comparable<T>> implements PriorityQueue<T> {

	/* The actual heap of data */
	private ArrayList<T> heap;
	private int heap_size;
	
	public MinHeap() {
		/* TODO: IMPLEMENT THIS METHOD */
		heap_size=0;
		heap = new ArrayList<T>();
		heap.add(null);
	}
	
	public MinHeap(ArrayList<T> data) {
		/* TODO: IMPLEMENT THIS METHOD */
		this.heap = (ArrayList<T>)data.clone();
		heap.add(0, null);
		heap_size = heap.size()-1;
		heapify();
	}
	
	private void heapify() {
		/* TODO: IMPLEMENT THIS METHOD */
		for(int i=heap_size;i>=1;i--) {
			percolateDown(i);
		}
	}
	
	private void percolateUp(int index) {
		/* TODO: IMPLEMENT THIS METHOD */
		if(index<=1) return;
		
		int pIndex = index/2;
		if(heap.get(index).compareTo(heap.get(pIndex))<0) {
			swap(index, pIndex);
			percolateUp(pIndex);
		}
	}
	
	private void percolateDown(int index) {
		/* TODO: IMPLEMENT THIS METHOD */
		int right = index*2+1;
		int left = index*2;
		if(right>heap_size && left>heap_size)
			return;
		else if(right>heap_size) {
			if(heap.get(index).compareTo(heap.get(left))>0) {
				swap(index,left);
				percolateDown(left);
			}
		}
		else if(left>heap_size) {
			if(heap.get(index).compareTo(heap.get(right))>0) {
				swap(index,right);
				percolateDown(right);
			}
		}
		else {
			if(heap.get(left).compareTo(heap.get(right))<0) {
				if(heap.get(index).compareTo(heap.get(left))>0) {
					swap(index,left);
					percolateDown(left);
				}
			}
			else {
				if(heap.get(index).compareTo(heap.get(right))>0) {
					swap(index,right);
					percolateDown(right);
				}
			}
		}
	}
	
	@Override
	public void push(T data) {
		/* TODO: IMPLEMENT THIS METHOD */
		heap.add(data);
		heap_size++;
		percolateUp(heap_size);
		
	}

	@Override
	public T poll() {
		/* TODO: IMPLEMENT THIS METHOD */
		if(heap_size==0) return null;
		
		T ret = heap.get(1);
		heap.set(1, heap.get(heap_size));
		heap.remove(heap_size);
		heap_size--;
		percolateDown(1);
		//System.out.println(ret);
		return ret;
	}

	@Override
	public T peek() {
		/* TODO: IMPLEMENT THIS METHOD */
		return heap.get(1);
	}
	
	@Override
	public int size() {
		/* TODO: IMPLEMENT THIS METHOD */
		return heap_size;
	}
	
	public void swap(int ind, int pind) {
		T temp = heap.get(ind);
		heap.set(ind, heap.get(pind));
		heap.set(pind, temp);
	}
	
	private boolean checkLeaf(int index) {
		if (index >= (heap_size / 2) && index <= heap_size)
			return true;
		return false;
	}
}
