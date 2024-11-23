package cutTool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class hyphen_f implements InputProcessor {

	private String fileName;
	
	public hyphen_f() {
		super();
	}
	
	public hyphen_f(String fileName) {
		this.fileName=fileName;
	}
	
	@Override	
	public void calculate(int[] range, String delimiter) {
		File file=new File(fileName);
		
		try(Scanner sc=new Scanner(new FileInputStream(file), "UTF-8");){
			
			while(sc.hasNextLine()) {
				String[] temp=sc.nextLine().split(delimiter);
				for(int r=range[0];r<=range[1] && r<temp.length ;r++) {
					System.out.printf("%s ",temp[r-1]);
				}
				System.out.println();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
