package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashSet;

public class WordPuzzleSolver {

	//HashTable<String,String> words = new HashTable<String,String>();
	HashSet<String> words = new HashSet<String>();
	String[][] grid;
	int dimR;
	int dimC;

	public static void main(String[] args) {
		
		/* Kick everything off by calling solve() */
		try {
			Scanner in = new Scanner(System.in);
			//String dicFile = in.next();
			//String gridFile = in.next();
			in.close();
			new WordPuzzleSolver().solve("input/words.txt", "input/50x50.grid.txt");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/* Solve the puzzle */
	private void solve(String dictFile, String gridFile) throws IOException {
			
		/* Implement this method. Open the files and solve the word puzzle!! */
		
		//Creates Hash Dictionary
		HashSet<String> hashDict = new HashSet<String>();
		BufferedReader in = new BufferedReader ( new FileReader (dictFile));
		String s;
		while((s = in.readLine())!=null) {
			hashDict.add(s);
		}
		in.close();
		
		
		//Creates 2D Array of gridFile
		BufferedReader gridscan = new BufferedReader ( new FileReader (gridFile));
		String height;
		String width;
		width = gridscan.readLine();
		height = gridscan.readLine();
		dimR = Integer.valueOf(width);
		dimC = Integer.valueOf(height);
		int x = Integer.parseInt(width);
		int y = Integer.parseInt(height);
		String text = gridscan.readLine();
		grid = new String[x][y];
		int k = 0;
		for(int i=0;i<x;i++) {
			for(int r=0;r<y;r++) {
				grid[i][r] =  Character.toString(text.charAt(k));
				k++;
			}
		}
		gridscan.close();
		
		
		
		/*for(int rows=0;rows<grid.length;rows++){
		    for(int columns=0;columns <grid[rows].length;columns++){
		        System.out.print(grid[rows][columns] + "\t" );}
		    System.out.println();}*/
		
		//long t1 = System.currentTimeMillis();
		for(int i=0;i<dimC;i++)
			vertical(i, hashDict);
		for(int i=0;i<dimR;i++)
			horizontal(i, hashDict);
		for(int row=0;row<dimR;row++) {
			for(int col=0;col<dimC;col++) {
				posDiag(row,col,hashDict);
				negDiag(row,col,hashDict);
			}
		}
		/*for(int row=0;row<=grid.length;row++) {
			for(int col=grid[0].length-1;col>=0;col--) {
				
			}
		}*/
		
		//long t2 = System.currentTimeMillis();
		//System.out.println("Function Time: " + (t2-t1)+"ms");
		//System.out.println(words.getColCount()+" Number of Collisions");
		//System.out.println(words);
		System.out.println(words.size()+ " words found");
	}
	
	//Helper Functions
	
	//VERTICAL !!!
	
	public void vertical(int col, HashSet dict) {
		int limit = 23<=dimR ? 23 : dimR;
		
		for(int window=3;window<=limit;window++) {
				for(int row=0;row+window<=dimR;row++) {
					String word = "";
					int last = row+window;
					for(int i=row;i<last;i++) {
						word += grid[i][col];
					}
					search(word,row,col, "S", dict);
					search(new StringBuilder(word).reverse().toString(),last-1,col,"N", dict);
			}
		}	
	}
	
	public void horizontal(int row, HashSet dict) {
		int limit = 23<=dimC ? 23 : dimC;
		
		for(int window=3;window<=limit;window++) {
				for(int col=0;col+window<=dimC;col++) {
					String word = "";
					int last = col+window;
					for(int i=col;i<last;i++) {
						word += grid[row][i];
					}
					search(word,row,col, "E", dict);
					search(new StringBuilder(word).reverse().toString(),row,last-1,"W", dict);
			}
		}	
	}
	
	public void posDiag(int row, int col, HashSet dict) {
		int maxY = 23<=dimR-row ? 23 : dimR-row;
		int maxX = 23<=dimC-col ? 23 : dimC-col;
		int maxWindow = Math.min(maxY, maxX);
		
		for(int window=3;window<=maxWindow;window++) {
			for(int x=0;x+window<=maxWindow;x++) {
				String word = "";
				int last = x+window;
				for(int i=x;i<last;i++) {
					word+=grid[row+i][col+i];
				}
				search(word,row+x,col+x,"SE",dict);
				search(new StringBuilder(word).reverse().toString(),row+last-1,col+last-1,"NW", dict);
			}
		}
		}
	
	public void negDiag(int row, int col, HashSet dict) {
		int maxY = 23<=dimR-row ? 23 : dimR-row;
		int maxX = 23<=col+1 ? 23 : col+1;
		int maxWindow = Math.min(maxY, maxX);
		
		for(int window=3;window<=maxWindow;window++) {
			for(int x=0;x+window<=maxWindow;x++) {
				String word = "";
				int last = x+window;
				for(int i=x;i<last;i++) {
					word+=grid[row+i][col-i];
				}
				search(word,row+x,col-x,"SW",dict);
				search(new StringBuilder(word).reverse().toString(),row+last-1,col-last+1,"NE", dict);
			}
		}
	    
		}
	
	public void search(String word, int row, int col, String dir, HashSet hashDict) {
		if(hashDict.contains(word)) {
			words.add(dir+" ("+row+","+col+"):	"+word);
			//System.out.println(word);
			//System.out.println(dir+" ("+row+","+col+"):	"+word);

		}
	}
}
