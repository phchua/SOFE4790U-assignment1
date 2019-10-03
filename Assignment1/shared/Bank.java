package shared;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.io.DataOutputStream;
import java.io.IOException;


import shared.Account;

public class Bank {
	public static HashMap<Integer, Account> AllTheAccounts;
	
	public Bank(){
		AllTheAccounts = new HashMap<>();
	}
	
	
	public static void createAccountService(DataOutputStream output) throws IOException{
		String sendString = "---------------------Account creation---------------------------------\n";
		try {
			int accNumber = ThreadLocalRandom.current().nextInt(1000,9999);
			Scanner sc = new Scanner(System.in);
			System.out.printf(sendString);
			System.out.printf("Enter your name: ");
			String accOwner = sc.nextLine();
			System.out.printf("Enter your initial balance: ");
			double accBalance = sc.nextDouble();
			System.out.printf("Set your account pin: ");
			int accPin = sc.nextInt();
			int pinlength = String.valueOf(accPin).length();
				while (pinlength != 6) {
					System.out.printf("Your pin must be 6 digits long!");
					accPin = sc.nextInt();	
					pinlength = String.valueOf(accPin).length();
				}
			output.writeUTF("New Account added accNum:"+accNumber+" Owner:"+accOwner);
			Account newAcc = new Account(accNumber, accOwner, accPin, accBalance);
			AllTheAccounts.put(accNumber,newAcc);
			System.out.println("Success! Account is created.\n"	);
			System.out.println("Your Account Number is: "+accNumber);

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
	}
	public static void closeAccountService(DataOutputStream output) throws IOException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please key in your Account number: ");
		int accNumber = sc.nextInt();		
		Account temp = AllTheAccounts.get(accNumber);

		if(temp!=null){
			System.out.printf("Account found! Please key in your pin: ");
			int keyedPin = sc.nextInt();
			while (temp.getAccPin() !=keyedPin) {
				System.out.println("Wrong pin! Try again");
				System.out.printf("Key in your 6 digit pin: ");
				keyedPin = sc.nextInt();
			}
			String removedOwner = temp.getAccOwner();
			AllTheAccounts.remove(accNumber);
			System.out.println("Account successfully removed!");
			output.writeUTF("User "+removedOwner+" has successfully removed his/her account.");
		}
		else{
			System.out.println("Account not found! Please try again.");			
		}
	}
	
	public static void updateBalanceService(DataOutputStream output) throws IOException{
	Scanner sc = new Scanner(System.in);
	System.out.println("Please key in your Account number: ");
	int accNumber = sc.nextInt();		
	Account temp = AllTheAccounts.get(accNumber);
	if (temp!=null) {
		System.out.printf("Please key in your pin: ");
		int keyedPin = sc.nextInt();
		while (temp.getAccPin() !=keyedPin) {
			System.out.println("Wrong pin! Try again");
			System.out.printf("Key in your 6 digit pin: ");
			keyedPin = sc.nextInt();
		}
	}
	boolean counter = true;
	while (counter) {
		String welcomeMsg = "Welcome "+ temp.getAccOwner()+"! \n" +
		"Choose from the options below\n" +
		"1. Withdraw funds\n" +
		"2. Deposit funds\n" +
		"3. Quit\n ";
		System.out.println(welcomeMsg);
		System.out.printf("Your choice: ");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println("You have $"+ temp.getAccBalance()+" in your account.");
			System.out.println("How much are you withdrawing today?");
			System.out.printf("Amount: ");
			double withdrawAmt = sc.nextDouble();
			while (temp.getAccBalance() < withdrawAmt) {
				System.out.println("Insufficient funds! Try again!");
				System.out.printf("Amount: ");
				withdrawAmt = sc.nextDouble();
			}
			temp.setAccBalance(temp.getAccBalance() - withdrawAmt);
			System.out.println("Your new balance is: $"+ temp.getAccBalance());	
			output.writeUTF(temp.getAccOwner()+" successfully withdrew $"+withdrawAmt);
			break;
		case 2:
			System.out.println("How much are you depositing today?");
			System.out.printf("Amount: ");
			double depositAmt = sc.nextDouble();
			temp.setAccBalance(temp.getAccBalance() + depositAmt);
			System.out.println("Your new balance is: $"+ temp.getAccBalance());
			output.writeUTF(temp.getAccOwner()+" successfully deposited $"+depositAmt);
			break;
		case 3:
			counter = false;
			break;
		default:
			System.out.println("Invalid choice!");
		}
	}
	}

	
	public static void checkBalanceService(DataOutputStream output) throws IOException{
		double balance = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please key in your Account number: ");
		int accNumber = sc.nextInt();		
		Account temp = AllTheAccounts.get(accNumber);
		if (temp!=null) {
			System.out.printf("Please key in your pin: ");
			int keyedPin = sc.nextInt();
			while (temp.getAccPin() !=keyedPin) {
				System.out.println("Wrong pin! Try again");
				System.out.printf("Key in your 6 digit pin: ");
				keyedPin = sc.nextInt();
			}
		}
		balance = temp.getAccBalance();
		System.out.println("You have $"+ temp.getAccBalance()+" in your account.");
	}
	
	public static void transferBalanceService(DataOutputStream output) throws IOException{
		
		Account senderAcc, receiveAcc;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please key in your Account number: ");
		int senderNum = sc.nextInt();
		senderAcc = AllTheAccounts.get(senderNum);
		System.out.printf("Please key in your pin: ");
		int keyedPin = sc.nextInt();
		while (senderAcc.getAccPin() !=keyedPin) {
			System.out.println("Wrong pin! Try again");
			System.out.printf("Key in your 6 digit pin: ");
			keyedPin = sc.nextInt();
		}
		System.out.println("Please key in the receipient Account number: ");
		int receiveNum = sc.nextInt();
		receiveAcc = AllTheAccounts.get(receiveNum);
		
		System.out.println("You have $"+ senderAcc.getAccBalance()+" in your account.");
		System.out.println("How much are you transfering?");
		System.out.printf("Amount: ");
		double transferAmt = sc.nextDouble();
		while (senderAcc.getAccBalance() < transferAmt) {
			System.out.println("Insufficient funds! Try again!");
			System.out.printf("Amount: ");
			transferAmt = sc.nextDouble();
		}

		System.out.println("=============== CONFIRMATION ============== \n");
		System.out.println("You are sending $"+transferAmt+" to Account "+receiveNum+ " ("+receiveAcc.getAccOwner()+")");
		while(true) {
			System.out.println("Enter Y to proceed, N to cancel the transaction");
			String confirmChoice = sc.nextLine();
			if (confirmChoice == "Y") {
				senderAcc.setAccBalance(senderAcc.getAccBalance() - transferAmt);
				receiveAcc.setAccBalance(receiveAcc.getAccBalance() + transferAmt);		
				System.out.println("Your new balance is: $"+ senderAcc.getAccBalance());	
				output.writeUTF("Transfer of $"+transferAmt+" successful from "+senderNum+" to "+receiveNum);
				break;
			}
			else if (confirmChoice == "N") {
				System.out.println("Operation aborted.");
				break;
			}
			else {
				continue;
			}
		}
	
	}
}
	

