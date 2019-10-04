package shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;


public class CSVReader{
	
	public static void searchCSV() {
		List<bankLocations> bankInfo = readFromCSV("bankLocation.csv");
		
		// all the choices here
		
	}
	
	
	private static List<bankLocations> readFromCSV(String fileName){
		List<bankLocations> bankInfo = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		
		try(BufferedReader br = Files.newBufferedReader(pathToFile,
				StandardCharsets.US_ASCII)){
			
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

class Location{
	private String address;
	private String street;
	private String phoneNum;
	private String type;
	
	public Location(String address, String street, String phoneNum, String type) {
		this.address = address;
		this.street = street;
		this.phoneNum = phoneNum;
		this.type = type;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getStreet() {
		return street;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public String getType() {
		return type;
	}
}