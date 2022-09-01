package sorting;

public class AdvancedSorts {

	
	
	public static<T extends Comparable<T>> void mergeSort(T[] list) {
		mergeSort(list, 0, list.length-1);
	}
	
	private static<T extends Comparable<T>> void mergeSort(T[] list, int i, int j) {
		/* TODO: IMPLEMENT THIS METHOD */
		if(i<j) {
			int mid = (i+j)/2;
			mergeSort(list,i,mid);
			mergeSort(list,mid+1,j);
			merge(list,i,mid,j);
		}
	}
	
	private static<T extends Comparable<T>> void merge(T[] list, int i, int mid, int j) {
		/* TODO: IMPLEMENT THIS METHOD */
			int lsize = mid-i+1;
			int rsize = j-mid;
			
			//Create left and right arrays
			T[] A = (T[])new Comparable[lsize];
			T[] B = (T[])new Comparable[rsize];
			
			//Copy from list into the arrays
			for(int x=0;x<lsize;x++)
				A[x] = list[i+x];
			for(int y=0;y<rsize;y++)
				B[y] = list[mid+1+y];
			
			//Pointers
			int a_ptr=0;
			int b_ptr=0;
			int k=i;
			while(a_ptr<lsize && b_ptr<rsize ) {
				if(A[a_ptr].compareTo(B[b_ptr])<=0) {
					list[k] = A[a_ptr];
					a_ptr++;
				}
				else {
					list[k] = B[b_ptr];
					b_ptr++;
				}
				k++;
			}
			while(a_ptr<lsize) {
				list[k] = A[a_ptr];
				a_ptr++;
				k++;
			}
			while(b_ptr<rsize) {
				list[k] = B[b_ptr];
				b_ptr++;
				k++;
			}
	}
	
	
	
	
	
	
	
	public static<T extends Comparable<T>> void quickSort(T[] list) {
		quickSort(list, 0, list.length-1);
	}
	
	private static<T extends Comparable<T>> void quickSort(T[] list, int i, int j) {
		/* TODO: IMPLEMENT THIS METHOD */
		if(i<j) {
		int pivotindex = partition(list, i, j);
		quickSort(list, i, pivotindex-1);
		quickSort(list, pivotindex+1, j);}
	}
	
	private static<T extends Comparable<T>> int partition(T[] list, int i, int j) {
		/* TODO: IMPLEMENT THIS METHOD */
		T pivot = list[j]; //for now I'm just choosing the median as the partition
		int h=i;
		//Examine up to j
		for(int k=i;k<=j-1;k++) {
			if(list[k].compareTo(pivot)<=0) {
				
				swap(list, h, k);
				h++;
			}
		}
		//After examining, swap pivot and h
		swap(list, h, j);
		
		//return pivots index i.e. h
		return h;
		
	}
	
	//Helper Methods
	static <T extends Comparable<T>> void swap(T[] list, int p1, int p2) {
		T temp = list[p1];
		list[p1] = list[p2];
		list[p2] = temp;
	}
	
	
	
	
	
	

}
