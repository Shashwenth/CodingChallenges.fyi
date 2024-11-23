package cutTool;

import java.io.FileNotFoundException;
import java.util.Arrays;

/* 
 * 
 *  \src>javac cutTool/Main.java

	\src>java cutTool/Main cut -d , -f 2 fourchords.csv 
	
	\src>java cutTool/Main cut -f 2-4 sample.tsv     
 	
 	

    \src>tail -n 5 fourchords.csv | java cutTool/Main cut -d , -f 1-2
 *
 */


public class Main {

	public static void main(String[] args) throws FileNotFoundException {

			if(args[0].equals("cut")) {	
				
				String delimiter="\t";
				String fileName="";
				int sysInFlag=0;
				if(args[args.length-1].contains(".")) {
					fileName=args[args.length-1];
				}
				else {
					sysInFlag=1;
				}
				
				
				int[] range=new int[2];
				
				for(int i=1;i<args.length;i++) {
					
					if(args[i].startsWith("-d")) {
						if(args[i].equals("-d")) {
							delimiter=args[i+1];
						}
					}
					
					if(args[i].startsWith("-f")) {
						if(args[i].equals("-f")) {
							
							
							if(args[i+1].contains("-")) {
								String[] temp=args[i+1].split("-");
								range[0]=Integer.parseInt(temp[0]);
								range[1]=Integer.parseInt(temp[1]);
							}
							
							else {
								range[0]=Integer.parseInt(args[i+1]);
								range[1]=Integer.parseInt(args[i+1]);
							}
							
						}
					}
					
					
					
				
				}
				
//				System.out.println(fileName);
//				System.out.println(Arrays.toString(range));
				
				InputProcessor inputProcessor;
				
				if(sysInFlag==0) {
					inputProcessor=new hyphen_f(fileName);
					
				}
				else {
					inputProcessor=new ProcessSystemInput();
					
				}
				
				inputProcessor.calculate(range, delimiter);
				
				
			}
		
		
	
		
		
		
		

	}

}
