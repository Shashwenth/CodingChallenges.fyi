package encode;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Encoder {

    public Encoder() {
        super();
    }

    String filePath = "src/hyphen_encoded.txt";
    String originalPath = "src/test.txt";

    public void encodeHuffman(HashMap<Character, String> lookUpTable) {
        try (Scanner sc = new Scanner(new FileInputStream(originalPath), StandardCharsets.UTF_8);
             FileOutputStream fos = new FileOutputStream(filePath);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            // Write the header with UTF-8 encoding
            writeHeader(lookUpTable, fos);

            // Encode and write the file content in binary format
            writeFile(lookUpTable, bos, sc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeFile(HashMap<Character, String> lookUpTable, BufferedOutputStream bos, Scanner sc)
            throws IOException {
        StringBuilder bitBuffer = new StringBuilder();
        int remainingBits = 0;

        while (sc.hasNextLine()) {
            String originalText = sc.nextLine();

            // Encode each character based on Huffman lookup table
            for (int i = 0; i < originalText.length(); i++) {
                bitBuffer.append(lookUpTable.get(originalText.charAt(i)));
            }
            //System.out.println("Writiing Bitbuffwe");
            //System.out.println(bitBuffer.toString());
            // Append encoding for newline character
            bitBuffer.append(lookUpTable.get('\n'));

            // Write complete bytes to the file
            while (bitBuffer.length() >= 8) {
                String byteString = bitBuffer.substring(0, 8);
                bitBuffer.delete(0, 8);
                byte b = (byte) Integer.parseInt(byteString, 2);
                //System.out.println(b);
                bos.write(b);
            }
        }

        // Handle any remaining bits by padding to create a full byte
        remainingBits = bitBuffer.length();
        if (remainingBits > 0) {
            String lastBits = bitBuffer.toString();
            while (lastBits.length() < 8) {
                lastBits += "0"; // Pad with zeros
            }
            byte lastByte = (byte) Integer.parseInt(lastBits, 2);
            bos.write(lastByte);
            bos.write(remainingBits); // Save number of valid bits for decoding
        }
    }

    public void writeHeader(HashMap<Character, String> lookUpTable, FileOutputStream fos) throws IOException {
        // Use OutputStreamWriter to handle character encoding properly
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        StringBuilder meta = new StringBuilder();
        meta.append(lookUpTable.size()).append("\n");

        for (Map.Entry<Character, String> entry : lookUpTable.entrySet()) {
            if (entry.getKey() == '\n') {
                meta.append("newLine").append(":").append(entry.getValue()).append("\n");
            } else {
                meta.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
            }
        }

        System.out.println("Before Writing:");
        System.out.println(meta.toString());

        // Write the metadata as a UTF-8 encoded string
        osw.write(meta.toString());
        osw.flush();  // Ensure data is written before binary content
    }
}
