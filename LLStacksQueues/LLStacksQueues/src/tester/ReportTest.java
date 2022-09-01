package tester;
import java.lang.System;
import java.util.LinkedList;

public class ReportTest {
	
	public static int n = 1000; //size of list
	public static int i = 50000; //Number of tests
	
	public static void main(String[] args) {
		
		//Create a new Linked List of size n
		list.LinkedList<Integer> testList = new list.LinkedList<Integer>();
		for(int j=0; j<n; j++) {
			int rand = (int)Math.floor(Math.random()*1000);
			testList.insertAtTail(rand);
		}
		
		//Starts timer
		long t1 = System.currentTimeMillis();
		
		//Runs test method i times
		for(int t=0;t<i;t++) {
			
			//Test get
			int rand = (int)Math.floor(Math.random()*testList.size());
			testList.get(rand);
			
		}
		//Prints elapsed time
		System.out.print("Done! Your Time Was: ");
		long time = System.currentTimeMillis() - t1 ;
		System.out.println(time +" milliseconds");
		
		//clear heap
		testList.clear();
	}
}

		/*
		 * 	//Test Insert At Tail
				int rand = (int)Math.floor(Math.random()*1000);
				testList.insertAtTail(rand);
			
		
			//Test insertAtHead
				int rand = (int)Math.floor(Math.random()*1000);
				testList.insertAtHead(rand);
				
		
			//Test insertAt
				int rand = (int)Math.floor(Math.random()*1000);
				int ind = (int)(Math.random()*testList.size());
				testList.insertAt(ind, rand);
				
		
			//Test insert
			list.ListIterator<Integer> studIt = testList.back();
			for(int i=n-2; i>0; i--) {
				boolean add = (int)(Math.random()*4) == 0;
				int rand = (int)Math.floor(Math.random()*10);
				if(add && !studIt.isPastBeginning()) {
					testList.insert(studIt, rand);
				}
				studIt.moveBackward();
			}
		
		//Test removeAtTail()
				testList.removeAtHead();
			
			
		//Test removeAtHead()
				testList.removeAtHead();
			
			
		//Test remove
		 * list.ListIterator<Integer> studIt = testList.back();
			for(int i=n-2; i>0; i--) {
				boolean add = (int)(Math.random()*4) == 0;
				if(add && !studIt.isPastBeginning()) {
					testList.remove(studIt);
				}
				studIt.moveBackward();
			}
			
		//Test find
			int rand = (int)Math.floor(Math.random()*1000);
			int ind2 = testList.find(rand);
			
		//Test get
			int rand = (int)Math.floor(Math.random()*compList.size());
			int ind2 = testList.get(rand);
	}
	*/
	

