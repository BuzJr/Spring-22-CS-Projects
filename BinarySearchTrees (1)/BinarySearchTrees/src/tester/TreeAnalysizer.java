package tester;
import tree.AVLTree;
import java.util.random.*;
import tree.BinarySearchTree;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class TreeAnalysizer<T extends Comparable<T>> extends AVLTree<T> {

	public static void main(String[] args) throws IOException{
		//Read File For String Test
		//Scanner scanner = new Scanner(new File("MetamorphoesesBook1-3.txt"));
		//scanner.useDelimiter(" |\r\n|\n");
		//int n = 23944;
		  //String [] arr = new String [n];
		  //int i = 0;
		  //while(scanner.hasNext() && i<n) { 
		   //arr[i] = scanner.next();  
		   //while (arr[i].equals("")) { arr[i] = scanner.next();}  
		   //i++;
		  //}
		 //scanner.close();
		 
		//Create an array of random integers
		int n = 25000000; //Array Size
		int[] arr = new int[n];
		int max = 10000;
		int min = 1;
		Random rand = new Random();
		for(int i=0;i<n; i++) {
			int num = min + rand.nextInt(max);
			arr[i] = num;
		}
		
		 //Create Trees
		AVLTree<Integer> avl = new AVLTree<Integer>();
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		
		//Test Insert AVL
		long t1 = System.currentTimeMillis();
		for(int s:arr)
			avl.insert(s);
		long t2 = System.currentTimeMillis();
		System.out.println("AVL Insert Time: " + (t2-t1));
		
		//Test BST Insert
		long t3 = System.currentTimeMillis();
		for(int s:arr)
			bst.insert(s);
		long t4 = System.currentTimeMillis();
		System.out.println("BST Insert Time: " + (t4-t3));
		
		//Test Find AVL
		long t5 = System.currentTimeMillis();
		for(int s:arr)
			avl.find(s);
		long t6 = System.currentTimeMillis();
		System.out.println("AVL Find Time: " + (t6-t5));
		
		//Test Find BST
		long t7 = System.currentTimeMillis();
		for(int s:arr)
			bst.find(s);
		long t8 = System.currentTimeMillis();
		System.out.println("BST Find Time: " +(t8-t7));
		
		//Height of Both Trees
		System.out.println("AVL Tree Height: " + avl.height());
		System.out.println("BST Tree Height: " + bst.height());
	}
}