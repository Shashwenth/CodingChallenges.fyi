package sortTool.algorithms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class MSDStringSort implements SortInterface {
	
	private boolean isUnique=false;
	
	private String FILENAME;
	
	public static int R=256;
	
	private String[] aux;
	private String[] arr;
	//private char[] alphabetArray;
	
	public MSDStringSort(String FileName) {
		this.FILENAME=FileName;
	}
	
	public MSDStringSort(String FileName, boolean isUnique) {
		this.FILENAME=FileName;
		this.isUnique=isUnique;
	}
	
	
	public void PrintSortedList() {
//		System.out.println("This is the array");
//		for(String s:arr) {
//			System.out.printf("%s ",s);
//		}
//		System.out.println();
		MSDSortAlgorithm(arr);
		
	}
	
	
	public void MSDSortAlgorithm(String[] arr) {
		
		int n=arr.length;
		
		aux= new String[arr.length];
//		System.out.println("Array Length");
//		System.out.println(arr.length);
		sort(arr, 0, n-1, 0);
		
		//System.out.println("After Sort");
		String prev="";
		if(isUnique) {
			for(String s:arr) {
				if(prev.equals(s)) {
					continue;
				}
				System.out.println(s);
				prev=s;
			}
		}else {
			for(String s:arr) {
				System.out.println(s);
			}
		}
		
		
	}
	
	public void sort(String[] arr, int low, int high, int d) {
	    if (high <= low) {
	        return;
	    }

	    int[] count = new int[R + 2];  
	    Arrays.fill(count, 0);

	    for (int i = low; i <= high; i++) {
	        int charAtIndex;
	        if (arr[i].length() <= d) {
	            charAtIndex = -1;  
	        } else {
	            charAtIndex = arr[i].getBytes(StandardCharsets.UTF_8)[d] & 0xFF;  
	        }
	        count[charAtIndex + 2] += 1;  
	    }

	    
	    for (int i = 0; i < count.length - 1; i++) {
	        count[i + 1] += count[i]; 
	    }

	    for (int i = low; i <= high; i++) {
	        int charAtIndex;
	        if (arr[i].length() <= d) {
	            charAtIndex = -1; 
	        } else {
	            charAtIndex = arr[i].getBytes(StandardCharsets.UTF_8)[d] & 0xFF;  
	        }
	        aux[count[charAtIndex + 1]++] = arr[i]; 
	    }

	   
	    for (int i = low; i <= high; i++) {
	        arr[i] = aux[i - low];
	    }
	    for (int i = 0; i < count.length - 1; i++) {
	        sort(arr, low + count[i], low + count[i + 1] - 1, d + 1);  
	    }
	}

	
	
	public void ReadFile() {
	    File file = new File(FILENAME);
	    
	    
	    int lineCount = 0;
	    try (Scanner sc = new Scanner(new FileInputStream(file))) {
	        while (sc.hasNextLine()) {
	            sc.nextLine();
	            lineCount++;
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }

	    
	    arr = new String[lineCount];

	    
	    try (Scanner sc = new Scanner(new FileInputStream(file))) {
	        int i = 0;
	        while (sc.hasNextLine()) {
	            String currentLine = sc.nextLine().trim();
	            arr[i++] = currentLine;
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}


	

}
