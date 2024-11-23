package sortTool;

import sortTool.algorithms.TrieImplementation;

public class SortMainClass {

	public static void main(String[] args) {
		
		//System.out.println("Welcome to Sort tool");
		
		//System.out.println("Current working directory: " + System.getProperty("user.dir"));

		
		if(args[0].equals("sort")) {
			
			
			
			String FILENAME=args[args.length-1];
			
//			System.out.println("Printing the FIleNAme");
//			System.out.println(FILENAME);
			
			TrieImplementation trieImplementation;
			if(args[1].equals("-u")) {
				trieImplementation= new TrieImplementation(FILENAME, true);
			}else {
				trieImplementation= new TrieImplementation(FILENAME);
			}
			
			
			
			trieImplementation.ReadFile();
			//System.out.println("DOne Read and Add");
			trieImplementation.PrintSortedList();
			
			
			
		}
		

	}

}
