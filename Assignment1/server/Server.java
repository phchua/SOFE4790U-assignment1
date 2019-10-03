package server;

import java.io.*;
import java.net.*;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Server 
{ 
	//initialize socket and input stream 
	private Socket socket = null; 
	private ServerSocket server = null; 
	private DataInputStream in = null;
	private DataOutputStream out = null;

	// constructor with port 
	public Server(int port) throws IOException 
	{ 
		// starts server and waits for a connection 

		try
		{ 
			server = new ServerSocket(port); 
			System.out.println("Server started"); 

			System.out.println("Waiting for a client ..."); 

			socket = server.accept(); 
			System.out.println("Client accepted"); 
//
//
//
//
			
			// takes input from the client socket 
			in = new DataInputStream( 
				new BufferedInputStream(socket.getInputStream())); 
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			String line = "";
				// reads message from client until "Over" is sent 
			while (!line.equals("Over")) 
			{ 
				try
				{ 
					line = in.readUTF(); 
					if (isStringInt(line)) {					
						int result = Integer.parseInt(line);
						boolean shouldStop = false;
						while (!shouldStop) {
							switch (result) {
							case 1:			  
								  line = String.valueOf(result);
								  result = 0;
								  line = "Over";
								  
								  break;
							  case 2:
								  line = String.valueOf(result);			  
								  result = 0;
								  line = "Over";
		
							    break;
							  case 3:
								  line = String.valueOf(result);
								  out.writeUTF(line);			  
								  System.out.println("bankClient.runDepositService()");
		
							    break;
							  case 4:
								  line = String.valueOf(result);
								  out.writeUTF(line);			  
								  System.out.println("bankClient.runWithdrawService()");
		
							    break;
							  case 5:
								  line = String.valueOf(result);
								  out.writeUTF(line);			  
								  System.out.println("bankClient.runMonitorService()");
		
							    break;
							  case 6:
								  line = String.valueOf(result);
								  out.writeUTF(line);
								  System.out.println("bankClient.runQueryService()");
								  
							    break;
							  case 7:
								  System.out.printf("bankClient.runMaintenanceService()");
								  line = String.valueOf(result);
								  out.writeUTF(line);
							    break;
							  case 8:
								System.out.println("hi");
							    break;
							  case 0:
							    shouldStop = true;
							    break;
							  default:
							    System.out.println("Invalid choice!");
							    break;
							}
						}
					}
					else {
						System.out.println(line);
					}
					

				} 
				catch(IOException i) 
				{ 
					System.out.println(i);
					System.out.println("Closing connection"); 
					socket.close();
				}
				line = "";
			} 
			
			System.out.println("Closing connection"); 

			// close connection 
			socket.close(); 
			in.close(); 
		} 
		catch(IOException i) 
		{ 
			System.out.println(i);
			socket.close();
		} 
	} 
	
//	public boolean loopsend(DataInputStream input, DataOutputStream output) throws IOException {
//		
//		try {
//				String sendLine = "";
//				sendLine = input.readUTF();
//				while (sendLine != "") {
//					
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
	public boolean isStringInt(String s)
	{
	    try
	    {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException ex)
	    {
	        return false;
	    }
	}

	public static void main(String args[]) throws IOException 
	{ 
		Server server = new Server(1666); 
	} 
} 

// client server interaction
// client focuses on input from user
// client input then transfers to server to parse as information
// functions would then return success messages through as output.
