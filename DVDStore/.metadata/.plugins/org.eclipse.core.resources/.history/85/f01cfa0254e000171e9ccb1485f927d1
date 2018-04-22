package common;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class DVDService {
	
	public void createDVD(DVD dvd) throws IOException{

		// create a text file with info
		
		List<String> lines = Arrays.asList(dvd.toString(), "\n");
		Path file = Paths.get("DVD.txt");
		Files.write(file, lines, Charset.forName("UTF-8"));
		
		//send e-mail
		
	
	}
}
