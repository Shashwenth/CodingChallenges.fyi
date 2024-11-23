package cutTool;

import java.util.Scanner;

public class ProcessSystemInput implements InputProcessor {
	
	
	public ProcessSystemInput() {
		super();
	}
	
	@Override
	public void calculate(int[] range, String delimiter) {
		
		try(Scanner sc=new Scanner(System.in);){
			
			while(sc.hasNextLine()) {
				String[] temp=sc.nextLine().split(delimiter);
				for(int r=range[0];r<=range[1] && r<temp.length ;r++) {
					System.out.printf("%s ",temp[r-1]);
				}
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
