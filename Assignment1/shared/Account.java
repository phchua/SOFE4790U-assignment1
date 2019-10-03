
package shared;

public class Account {
	private int accNumber;
	private String accOwner;
	private int accPin;
	private double accBalance;
	

	public Account() {}
	public Account(int accNumber, String accOwner, int accPin, double accBalance) {
		this.accNumber = accNumber;
		this.accOwner = accOwner;
		this.accPin = accPin;
		this.accBalance = accBalance;
	}
	
	public int getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(int accNumber) {
		this.accNumber = accNumber;
	}
	public String getAccOwner() {
		return accOwner;
	}
	public void setAccOwner(String accOwner) {
		this.accOwner = accOwner;
	}
	public double getAccBalance() {
		return accBalance;
	}
	public void setAccBalance(double accBalance) {
		this.accBalance = accBalance;
	}
	
	public int getAccPin() {
		return accPin;
	}

	public void setAccPin(int accPin) {
		this.accPin = accPin;
	}


}
