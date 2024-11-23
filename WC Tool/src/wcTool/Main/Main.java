package wcTool.Main;

import java.util.Scanner;

import wcTool.filePath.OperateFile;

public class Main {

	static String FILE_NAME = "src/";

	public static void main(String args[]) {
		

		
		//System.out.println("WC TOOl JAR Current working directory: " + System.getProperty("user.dir"));
		
		if(!args[0].equals("ccwc")) {
			System.out.println("Enter a Valid Command");
		}
		
		System.out.println("Welcome to ccwc tool");
		
		if(args.length<=3 && args[args.length-1].endsWith(".txt")) {
			OperateFile of=new OperateFile(args[args.length-1]);
			if(args.length==2) {
				long line=of.lineCount();
				long word=of.wordCount();
				long charCount=of.charCount();
				System.out.printf("%d %d %d %s",line,word,charCount,args[args.length-1]);
			}
			switch (args[1]) {
			case "-l": {
				System.out.printf("%d ",of.lineCount());
				break;
			}
			case "-w": {
				System.out.printf("%d ",of.wordCount());
				break;
			}
			case "-m": {
				System.out.printf("%d ",of.charCount());
				break;
			}
			case "-c": {
				System.out.printf("%d ",of.byteCount());
				break;
			}
			default:
				System.out.printf("%s",args[args.length-1]);
			}
		}
		
		else if(args.length>=1 && !args[args.length-1].endsWith(".txt")){
			long lineCount = 0;
	        long wordCount = 0;
	        long charCount = 0;
	        long byteCount = 0;
	        Scanner scanner = new Scanner(System.in);
	        // Read from standard input
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            lineCount++;
	            charCount += line.length();
	            byteCount += line.getBytes().length; // Count bytes
	            
	            // Split the line into words and count them
	            String[] words = line.trim().split("\\s+"); // Split by whitespace
	            wordCount += words.length;
	        }
	        scanner.close();
	        
	        if(args.length==1) {
				System.out.printf("%d %d %d %s",lineCount,wordCount,charCount,args[args.length-1]);
			}
	        else {
	        	switch (args[1]) {
				case "-l": {
					System.out.printf("%d ",lineCount);
					break;
				}
				case "-w": {
					System.out.printf("%d ",wordCount);
					break;
				}
				case "-m": {
					System.out.printf("%d ",charCount);
					break;
				}
				case "-c": {
					System.out.printf("%d ",byteCount);
					break;
				}
				default:
					System.out.printf("%s",args[args.length-1]);
				}
				
	        }
			
		}
		
		
		
		return;
	}

}
