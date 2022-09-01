package test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import priorityqueue.HeapSort;
import priorityqueue.MinHeap;
import priorityqueue.NaiveHeap;
public class SuperTrooperPooper {
	public static void main(String[] args) throws IOException{
		int n = 20000; //Array Size
		int[] arr = new int[n];
		int max = 10000;
		int min = 1;
		Random rand = new Random();
		for(int i=0;i<n; i++) {
			int num = min + rand.nextInt(max);
			arr[i] = num;
		}
		
		MinHeap<Integer> mh = new MinHeap<Integer>();
		NaiveHeap<Integer> nh = new NaiveHeap<Integer>();
		
		//Test Push 
				long t1 = System.currentTimeMillis();
				for(int s:arr)
					mh.push(s);
				long t2 = System.currentTimeMillis();
				System.out.println("MinHeap Push Time: " + (t2-t1));
				
				long t3 = System.currentTimeMillis();
				for(int s:arr)
					nh.push(s);
				long t4 = System.currentTimeMillis();
				System.out.println("NaiveHeap Push Time: " + (t4-t3));
				
				
		//Test Peek
				long t9 = System.currentTimeMillis();
				for(int s:arr)
					mh.peek();
				long t10 = System.currentTimeMillis();
				System.out.println("MinHeap Peek Time: " + (t10-t9));
				long t11 = System.currentTimeMillis();
				for(int s:arr)
					nh.peek();
				long t12 = System.currentTimeMillis();
				System.out.println("Naive Peek Time: " + (t12-t11));
				
				//Test Poll
				long t5 = System.currentTimeMillis();
				for(int s:arr)
					mh.poll();
				long t6 = System.currentTimeMillis();
				System.out.println("MinHeap Poll Time: " + (t6-t5));
				long t7 = System.currentTimeMillis();
				for(int s:arr)
					nh.poll();
				long t8 = System.currentTimeMillis();
				System.out.println("NaiveHeap Poll Time: " + (t8-t7));
	}

}
