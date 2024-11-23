package wcTool.filePath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class OperateFile {

	
	private File file;
	private String path="";
	

	public OperateFile(String path) {
		this.path+=path;
		this.file=new File(this.path);
	}
	
	
	public long byteCount() {
		return file.length();
	}
	
	public long lineCount() {
		long lineCount=0;
		try(Stream<String> stream = Files.lines(Paths.get(path),StandardCharsets.UTF_8)){
			lineCount=stream.count();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lineCount;
	}
	
	
	public long wordCount() {
		long words=0;
		try(Scanner scr=new Scanner(new FileInputStream(file))){
			while(scr.hasNext()) {
				scr.next();
				words++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return words;
	}
	
	public long charCount() {
		long characters=0;
		try(Scanner scr=new Scanner(new FileInputStream(file))){
			while(scr.hasNext()) {
				String temp=scr.nextLine();
				characters+=temp.length();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return characters;
	}
	
	
}
