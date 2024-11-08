package encode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Decoder {

    public Decoder() {
    }

    String originalPath = "src/hyphen_final.txt";
    String filePath = "src/hyphen_encoded.txt";

    public void decode() throws IOException {
        try (Scanner sc = new Scanner(new FileInputStream(filePath));
             BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(originalPath))) {

            HashMap<String, Character> lookupTable = new HashMap<>();

            // Step 1: Read the Huffman code table from the file header
            writeTable(lookupTable, sc);

            // Step 2: Decode the file contents using the lookup table
            writeInFile(lookupTable, bis, bos);
        }
    }

    public void writeTable(HashMap<String, Character> lookupTable, Scanner sc) {
        int numLines = 0;
        if (sc.hasNextLine()) {
            numLines = Integer.parseInt(sc.nextLine().trim());
        }

        while (numLines > 0) {
            String temp = sc.nextLine().stripTrailing();
            if (temp.startsWith("newLine")) {
                String[] temp_arr = temp.split(":");
                lookupTable.put(temp_arr[1], '\n');
            } else {
                lookupTable.put(temp.substring(2), temp.charAt(0)); 
            }
            numLines--;
        }

        System.out.println("The Decoder Table");
        for (Map.Entry<String, Character> m : lookupTable.entrySet()) {
            System.out.printf("%s %c\n", m.getKey(), m.getValue());
        }
    }

    public void writeInFile(HashMap<String, Character> lookupTable, BufferedInputStream bis, BufferedOutputStream bos) throws IOException {
        StringBuilder bitBuffer = new StringBuilder();
        int byteRead;
        PrintWriter outFile = new PrintWriter(new FileWriter(originalPath));
        // Read bytes from the file
        Scanner sc = new Scanner(new FileInputStream(filePath));
        int numLines = 0;
        if (sc.hasNextLine()) {
            numLines = Integer.parseInt(sc.nextLine().trim());
        }
        System.out.println(numLines);
        
        while ((byteRead = bis.read()) != -1) {
            // Convert each byte to an 8-bit binary string
            char character = (char) byteRead;
            if (numLines >= 0) {
                if (character == '\n')
                    numLines--;
            } else {
                String byteString = String.format("%8s", Integer.toBinaryString(byteRead & 0xFF)).replace(' ', '0');
                bitBuffer.append(byteString);

                // Decode bits to characters as they match the Huffman codes
                StringBuilder tempCode = new StringBuilder();
                for (int i = 0; i < bitBuffer.length(); i++) {
                    tempCode.append(bitBuffer.charAt(i));

                    // If the accumulated bits match a character in the lookup table
                    if (lookupTable.containsKey(tempCode.toString())) {
                        char decodedChar = lookupTable.get(tempCode.toString());
                        //System.out.println(decodedChar);
                        outFile.write(decodedChar);  // Writing the decoded byte

                        tempCode.setLength(0);  // Reset the temporary code for next match
                    }
                }

                // Keep only the unmatched remaining bits in the buffer
                bitBuffer = new StringBuilder(tempCode);
            }
        }
        outFile.close();
        bos.flush(); // Ensure everything is written to the output file
    }
}
