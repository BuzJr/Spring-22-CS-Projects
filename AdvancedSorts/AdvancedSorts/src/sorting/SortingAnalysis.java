package sorting;
import java.util.random.*;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Random;

public class SortingAnalysis {

	public static void main(String[] args) {
		
		//Create an array of random integers
				int n = 10000; //Array Size
				Integer[] arr = new Integer[n];
				int max = 1000000;
				int min = 1;
				Random rand = new Random();
				for(int i=0;i<n; i++) {
					int num = min + rand.nextInt(max);
					arr[i] = num;
				}
				
				//reverse sort the Array
				Collections.sort(Arrays.asList(arr));
				Collections.reverse(Arrays.asList(arr));
				
				//Almost Sort the Array, i.e. every element is 1 position1 away from it's correct location
				Collections.sort(Arrays.asList(arr));
				for(int r=0;r<arr.length-1;r+=2) {
					swap(arr,r,r+1);
				}
				
				
				//Test Bubble Sort
				long t1 = System.currentTimeMillis();
				bubbleSort(arr);
				long t2 = System.currentTimeMillis();
				System.out.println("Bubble Sort Time: " + (t2-t1));
				
				//Test Insert Sort
				long t3 = System.currentTimeMillis();
				insertionSort(arr);
				long t4 = System.currentTimeMillis();
				System.out.println("Insertion Sort Time: " + (t4-t3));
				
				//Test Merge Sort
				long t5 = System.currentTimeMillis();
				mergeSort(arr);
				long t6 = System.currentTimeMillis();
				System.out.println("Merge Sort Time: " + (t6-t5));
				
				//Test Quick Sort
				long t7 = System.currentTimeMillis();
				quickSort(arr);
				long t8 = System.currentTimeMillis();
				System.out.println("Quick Sort Time: " + (t8-t7));
				
				//Test Hybrid Sort
				long t9 = System.currentTimeMillis();
				hybridSort(arr);
				long t10 = System.currentTimeMillis();
				System.out.println("Hybrid Sort Time: " + (t10-t9));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//Advanced Sorts
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
	
	// Basic Sorts 
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
	//overloaded
	public static<T extends Comparable<T>> void insertionSort(T[] list, int beg, int end) {
		/* TODO: IMPLEMENT THIS METHOD */

	    for (int i = beg; i < end; i++) {
	      T temp = list[i];
	      int j = i - 1;
	      while (j >= 0 && temp.compareTo(list[j])<0) {
	        list[j + 1] = list[j];
	        --j;
	      }
	      list[j + 1] = temp;
		}
	}
	public static<T extends Comparable<T>> void hybridSort(T[] list) {
		hybridSort(list, 0, list.length-1);
	}
	
	private static<T extends Comparable<T>> void hybridSort(T[] list, int i, int j) {
		/* TODO: IMPLEMENT THIS METHOD */
		if(j-i<=40)
			insertionSort(list,i,j);
		else if(i<j) {
			int mid = (i+j)/2;
			mergeSort(list,i,mid);
			mergeSort(list,mid+1,j);
			merge(list,i,mid,j);
		}
	}
}