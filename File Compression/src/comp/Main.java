package comp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import encode.Decoder;
import encode.Encoder;
import tree.HuffmanClass;

public class Main {

	public static void main(String[] args) throws IOException {

		Charset defaultCharset1 = Charset.defaultCharset();
		String defaultCharset2 = System.getProperty("file.encoding");
		// printing the name of default charset
		System.out.println("Default charset1: " + defaultCharset1.name());
		System.out.println("Default charset2: " + defaultCharset2);

		File file = new File("src/test.txt");

		TreeMap<Character, Long> h = new TreeMap<>();
		h.put('\n', h.getOrDefault('\n', 0L) + 1);
		Scanner sc = null;

		try {
			sc = new Scanner(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int i = 0;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();

			// Count each character in the line
			for (char c : line.toCharArray()) {
				h.put(c, h.getOrDefault(c, 0L) + 1);
			}

			// Only add a newline if there's another line to follow
			if (sc.hasNextLine()) {
				h.put('\n', h.getOrDefault('\n', 0L) + 1);
			}
		}

		HuffmanClass hc = new HuffmanClass();

		HashMap<Character, Long> lookUpTable = new HashMap<Character, Long>();
		hc.getEncoding(hc.BuildTree(h), lookUpTable);

		HashMap<Character, String> pathTable = new HashMap<Character, String>();
		hc.TraverseNode(hc.BuildTree(h), pathTable, "");

//		char[] charArray = { 'C', 'D', 'E', 'K', 'L', 'M' , 'U', 'Z' };
//        int[] charfreq = { 32, 42, 120, 7, 42, 24, 37, 2 };
//
//        TreeMap<Character, Integer> h = new TreeMap<>();
//
//        for (int i = 0; i < charArray.length; i++) {
//            h.put(charArray[i], charfreq[i]);
//        }
//        
//        HuffmanClass hc=new HuffmanClass();
//        hc.BuildTree(h);
//        

		for (Map.Entry<Character, Long> m : lookUpTable.entrySet()) {
			System.out.printf("%c %d\n", m.getKey(), m.getValue());
		}
		
		for (Map.Entry<Character, String> m : pathTable.entrySet()) {
			System.out.printf("%c %s\n", m.getKey(), m.getValue());
		}

//	    System.out.println(lookUpTable.get('X'));
//		System.out.println(lookUpTable.get('t'));
//		
//		System.out.println(pathTable.get('X'));
//		System.out.println(pathTable.get('t'));

//		Encoder ec=new Encoder();
//		ec.encodeHuffman(pathTable);
////		Decoder dc=new Decoder();
////		dc.decode();

		Encoder ec = new Encoder();
		ec.encodeHuffman(pathTable);
		Decoder dc = new Decoder();
		dc.decode();

		return;

	}

}
