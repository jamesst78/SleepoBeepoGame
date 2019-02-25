package simulation;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadingCSVFile{
	
	public static ArrayList<String> readFile(String path)throws IOException {
		String currentLine="";
		ArrayList<String> returnPlis = new ArrayList<String>();
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		while((currentLine = br.readLine()) != null) {
			//System.out.println(currentLine);
			String [] result = currentLine.split(",");
			for(int i = 0 ; i<result.length ; i++) {
				returnPlis.add(result[i]);
			}
		}
		return returnPlis;
		
		
		
	}
	public static void main(String[] args) throws IOException {
		
		
		
		
		
	}
	

}