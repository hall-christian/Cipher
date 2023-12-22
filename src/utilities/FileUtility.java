package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtility implements FileManager {
	
	
	private File file;
	private String content;
	
	
	public FileUtility(String filePath) {
		super();
		this.file = new File(filePath);
	}


	@Override
	public void write() {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			bw.write(content);
		}
		catch (IOException e) {
			System.out.println("Error in writing out " + file.getName());
		}
	}


	@Override
	public void update(String content) {
		this.content = content;
	}


	@Override
	public void read() {
		StringBuilder content = new StringBuilder();
		
		if (this.file.exists()) {
			System.out.println("Reading " + file.getName());
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = br.readLine()) != null) {
					content.append(line);
					content.append(System.getProperty("line.separator"));
				}
			}
			catch (FileNotFoundException e) {
				System.out.println("File not found");
			}
			catch (IOException e) {
				System.out.println("Error in reading in " + file.getName());
			}
			this.content = content.toString();
		} else {
			System.out.println(file.getName() +  " not found");
		}
		
		
	}


	@Override
	public void delete() {
		file.delete();
	}
	
	
	@Override
	public String view()
	{
		return this.content;
	}
}
