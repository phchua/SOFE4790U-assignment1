package shared;

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
	
	@Override
	public String toString() {
		return "Type: "+type+" Address: "+address+" Contact No: "+phoneNum;
	}
}