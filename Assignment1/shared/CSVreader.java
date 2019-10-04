package shared;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import shared.Location;


public class CSVreader{
	
	public static void searchCSV(String streetName) {
		List<Location> bankInfo = readFromCSV("bankLocation.csv");
		
		List<Location> searchResults = new ArrayList<Location>();
		
		// searches the list for similar street names and displays them
		for (Location i : bankInfo) {
			if (i.getStreet().equals(streetName)) {
				searchResults.add(i);
			}				
		}
		if(searchResults.isEmpty()) {
			System.out.println("Sorry! Search returned no results!");
		}
		else {
			System.out.println("Success! The result(s) are as follows: ");
			for(Location a : searchResults) {
				System.out.println(a);
			}
		}
	}
	
	
	private static List<Location> readFromCSV(String fileName){
		List<Location> bankInfo = new ArrayList<>();
		File file = new File(fileName);
		Path pathToFile = file.getAbsoluteFile().toPath();
		try(BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.UTF_8)){
			
			// reads first line from the text file
			String line = br.readLine();
			
			//loops until end of file
			while(line!=null) {
				//use of string.split to load string array, using comma as delimiter
				String[] attributes = line.split(",");
				
				Location location = createLocation(attributes);
				
				//adding location into the ArrayList
				bankInfo.add(location);
				
				line = br.readLine();
				
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return bankInfo;
	}
	
	private static Location createLocation(String[] metadata) {
		String address = metadata[0];
		String street = metadata[1];
		String phoneNum = metadata[2];
		String type = metadata[3];
		
		
		// returns location of this metadata
		return new Location(address,street,phoneNum,type);
	}
		
}