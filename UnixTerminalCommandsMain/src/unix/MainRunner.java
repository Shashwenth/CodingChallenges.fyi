package unix;

import java.io.FileNotFoundException;

public class MainRunner {
	public static void main(String[] args) {
		// System.out.println("Inside");
//    	System.out.println("Current working directory: " + System.getProperty("user.dir"));
//    	wcTool.Main.Main.main(args);

		switch (args[0]) {
		case "cut":
			try {
				cutTool.Main.main(args);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		case "ccwc":
			wcTool.Main.Main.main(args);
		}

		// System.out.println("Exiting");
	}
}