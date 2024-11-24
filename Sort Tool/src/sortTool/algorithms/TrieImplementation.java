package sortTool.algorithms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class TrieImplementation implements SortInterface {
	
	class Trie {

		char c;
		long count = 0l;
		HashMap<Character, Trie> children = new HashMap<>();

		public Trie(char c) {
			this.c = c;
		}

	}
	
	private boolean isUnique=false;
	
	private String FILENAME;
	
	private char[] charArray = new char[26 + 26];
	
	private Trie root=null;

	public TrieImplementation(String FileName) {
		this.FILENAME = FileName;
		initializeCharArray();
		root=new Trie('/');
	}
	
	public TrieImplementation(String FileName, boolean isUnique) {
		this.FILENAME = FileName;
		this.isUnique=true;
		initializeCharArray();
		root=new Trie('/');
	}

	

	public void add(Trie root, String s) {

		Trie currentNode = root;
		for (char currentCharacter : s.toCharArray()) {
			if (currentNode.children.containsKey(currentCharacter)) {
				currentNode = currentNode.children.get(currentCharacter);
			} else {
				Trie newNode = new Trie(currentCharacter);
				currentNode.children.put(currentCharacter, newNode);
				currentNode = newNode;
			}
		}

		currentNode.count += 1;
		//System.out.println(root.c);

	}

	public void initializeCharArray() {

		for (int i = 0; i < 26; i++) {
			charArray[i] = (char) ('A' + i);
		} 
		for (int i = 0; i < 26; i++) {
			charArray[i+26] = (char) ('a' + i);
		}
		

	}
	
	public void PrintSortedList() {
		SearchTriePrintWords(root, new StringBuilder());
	}
	
	public void SearchTriePrintWords(Trie root, StringBuilder currentString) {
		//System.out.println("Inside with curr"+currentString.toString());
		if(root.count>=1) {
			if(isUnique) {
				System.out.println(currentString.toString());
				
			}
			else if(!isUnique) {
				for(int i=0; i< root.count; i++) {
					System.out.println(currentString.toString());
				}
			}
			
		}
		
		for(int i=0; i<charArray.length; i++) {
			//System.out.println("Searching "+String.valueOf(charArray[i]));
			if(root.children.containsKey(charArray[i])) {
				StringBuilder newString = new StringBuilder(currentString); 
	            newString.append(charArray[i]);
	            SearchTriePrintWords(root.children.get(charArray[i]), newString);
			}
			
		}
		
	}
	
	
	public void ReadFile() {
		
		
		File file=new File(FILENAME);
		
		try {
			Scanner sc=new Scanner(new FileInputStream(file));
			while(sc.hasNextLine()) {
				String currentLine=sc.nextLine().trim();
				//System.out.println(currentLine);
				add(root, currentLine);
			}
			//System.out.println("Finished Read and ADD");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
