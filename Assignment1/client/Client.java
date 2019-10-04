package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import shared.Bank;

public class Client 
{ 
	// initialize socket and output streams 
	private Socket socket = null; 
	private DataOutputStream out = null;
	public String address;
	public int port;

	// constructor to put ip address and port 
	public Client(String address, int port) throws IOException 
	{ 	
		this.address = address;
		this.port = port;
		// establish a connection 
		try
		{ 
			socket = new Socket(address, port); 
			System.out.println("Connected to server!"); 
			
			// sends output to the server 
			out = new DataOutputStream(socket.getOutputStream()); 
		} 
		catch(UnknownHostException u) 
		{ 
			System.out.println(u); 
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 

		// instantiate interface Bank
		Bank bank = new Bank();
		
		// instantiate condition
		boolean shouldStop = false;
		
		String MANUAL = "----------------------------------------------------------------\n" +
      "Please choose a service by typing [1-8]:\n" +
      "1: Open a new bank account\n" +
      "2: Close a bank account\n" +
      "3: Deposit or Withdraw from a bank account\n" +
      "4: Check account balance\n" +
      "5: Transfer funds to another account\n" +
      "6: Contact my nearest bank\n" +
      "0: Stop the client\n";
		
		String line = "";
		Scanner sc = new Scanner(System.in);
		while (!shouldStop) 
		{
			System.out.printf(MANUAL);
			System.out.printf("Input choice: ");
			int userChoice = sc.nextInt();
			
			// User selects function from the menu
			// DataOutputStream out is used here for communication with the server
	      switch (userChoice) {		
		  case 1:
			  System.out.println("====================================");
			  System.out.println("========== Create Account ==========");
			  System.out.println("====================================");
			  Bank.createAccountService(out);
		    break;
		  case 2:
			  System.out.println("===================================");
			  System.out.println("========== Close Account ==========");
			  System.out.println("===================================");
			  Bank.closeAccountService(out);

		    break;
		  case 3:
			  System.out.println("======================================");
			  System.out.println("========== Withdraw/Deposit ==========");
			  System.out.println("======================================");
			  Bank.updateBalanceService(out);

		    break;
		  case 4:
			  System.out.println("==================================");
			  System.out.println("========= Check Balance ==========");
			  System.out.println("==================================");
			  Bank.checkBalanceService(out);
			  
		    break;
		  case 5:
			  System.out.println("============================================");
			  System.out.println("========= Transfer to another Acc ==========");
			  System.out.println("============================================");
			  line = String.valueOf(userChoice);
			  Bank.transferBalanceService(out);
			  
		    break;
		  case 6:
			  System.out.println("==========================================");
			  System.out.println("========= Locate Nearest Branch ==========");
			  System.out.println("==========================================");
			  line = String.valueOf(userChoice);
			  Bank.contactNearestBank(out);
		    break;
		  case 0:
		    shouldStop = true;
		    break;
		  default:
		    System.out.println("Invalid choice!");
		    break;
	      }
		}		
		System.out.println("Stopping client...");
		
	}
	
	public static void main(String args[]) throws IOException
	{ 
		Client client = new Client("127.0.0.1", 1666);}}
