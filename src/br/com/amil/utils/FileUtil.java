package br.com.amil.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static List<String> readFileRows (String path){
		try {
			FileReader fileReader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String row;
			List<String> rows = new ArrayList<String>();
			while ((row = bufferedReader.readLine()) != null){
				rows.add(row);
			}
			fileReader.close();
			
			return rows;
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
}
