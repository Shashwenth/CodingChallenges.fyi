package sortTool.algorithms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ThreeWayQuickSort implements SortInterface {
	
	
	private String FILENAME;
	
	private boolean isUnique;
	
	private String arr[];
	
	public ThreeWayQuickSort(String FileName, boolean isUnique) {
		
		this.FILENAME=FileName;
		
		this.isUnique=isUnique;
		
	}

	@Override
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


	@Override
	public void PrintSortedList() {
		// TODO Auto-generated method stub
		threeWayQuickSortALgo(arr, 0, arr.length-1, 0);
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
	
	private static int charAt(String s, int d)
	{ if (d < s.length()) return s.charAt(d); else return -1;}
	
	public void threeWayQuickSortALgo(String[] arr, int low, int high, int d) {
		
		if(high<=low) {
			return;
		}
		
			int l=low, h=high;
			
			int v= charAt(arr[low], d);
			
			int x=low+1;
			
			while(x<=h) {
				
				int xVal=charAt(arr[x], d);
				
				if(v>xVal) {
					swap(arr, l++ , x++);
				}else if(v<xVal) {
					swap(arr, x, h--);
				}
				else {
					x++;
				}
			}
				
			
			threeWayQuickSortALgo(arr, low, l-1, d);
			if(v>0) {
				threeWayQuickSortALgo(arr, l, h, d+1);
			}
			threeWayQuickSortALgo(arr, h+1, high, d);		
		
	}
	
	public void swap(String[] arr, int i, int j) {
		
		String temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
		
	}

	
	
	
}
