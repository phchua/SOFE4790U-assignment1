package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import shared.Bank;

public class Client 
{ 
	// initialize socket and input output streams 
	private Socket socket = null; 
	private DataInputStream input = null; 
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

			// takes input from server 
			DataInputStream input = new DataInputStream(System.in);
			DataInputStream fromServer = new DataInputStream(socket.getInputStream());			

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

		Bank bank = new Bank();
		boolean shouldStop = false;
		String MANUAL = "----------------------------------------------------------------\n" +
      "Please choose a service by typing [1-8]:\n" +
      "1: Open a new bank account\n" +
      "2: Close a bank account\n" +
      "3: Deposit to a bank account\n" +
      "4: Withdraw from a bank account\n" +
      "7: Print the manual\n" +
      "0: Stop the client\n";
		String line = "";
		Scanner sc = new Scanner(System.in);
		// BankClient bankClient = new BankClient(address,port);
		while (!shouldStop) 
		{
			System.out.printf(MANUAL);
			System.out.printf("Input choice: ");
			int userChoice = sc.nextInt();
			System.out.println("YOU CHOOSE: " + userChoice + "\n\n");
	      switch (userChoice) {		
		  case 1:			  
			  line = String.valueOf(userChoice);
			  out.writeUTF(line);
			  Bank.createAccountService(out);
		    break;
		  case 2:
			  line = String.valueOf(userChoice);
			  out.writeUTF(line);			  
			  Bank.closeAccountService(out);

		    break;
		  case 3:
			  line = String.valueOf(userChoice);
			  out.writeUTF(line);			  
			  Bank.updateBalanceService(out);

		    break;
		  case 4:
			  line = String.valueOf(userChoice);
			  out.writeUTF(line);
			  Bank.checkBalanceService(out);
			  
		    break;
		  case 5:
			  line = String.valueOf(userChoice);
			  out.writeUTF(line);
			  Bank.transferBalanceService(out);
			  
		    break;
//		  case 7:
//			  System.out.printf("bankClient.runMaintenanceService()");
//			  line = String.valueOf(userChoice);
//			  out.writeUTF(line);
//		    break;
//		  case 8:
//		    System.out.println(MANUAL);
//		    break;
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
	
//	public boolean loopreceive(DataInputStream input, DataOutputStream output) throws IOException {
//		
//		try {
//				String receiveLine = "";
//				receiveLine = input.readUTF();
//				while (receiveLine != "") {
//					output.writeUTF(receiveLine);
//					receiveLine = input.readUTF();
//				}
//				output.flush();				
//		}
//		catch(IOException i) 
//		{ 
//			System.out.println(i); 
//		} 
//		return true;
//
//	}
	
	public static void main(String args[]) throws IOException
	{ 
		Client client = new Client("127.0.0.1", 1666);}}
