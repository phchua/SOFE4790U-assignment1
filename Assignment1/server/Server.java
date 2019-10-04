package server;

import java.io.*;
import java.net.*;

public class Server 
{ 
	//initialize socket and input stream 
	private Socket socket = null; 
	private ServerSocket server = null; 
	private DataInputStream in = null;

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

			// takes input from the client socket 
			in = new DataInputStream( 
					new BufferedInputStream(socket.getInputStream())); 


			String line = "";
			//use keyword "End" as condition to end while loop
			while (!line.equals("End")) 
			{ 
				try
				{ 
					//constantly listening for messages from client-side
					//user menu choices are integers
					//an if loop is used to only print important information
					
					//after every user choice, the switch case is reset and the server
					//will listen for the next user input

					line = in.readUTF(); 
					if (isStringInt(line)) {					
						int result = Integer.parseInt(line);
						boolean shouldStop = false;
						while (!shouldStop) {
							switch (result) {
							case 1:			  
								line = String.valueOf(result);
								result = 0;								  
								break;

							case 2:
								line = String.valueOf(result);			  
								result = 0;		
								break;

							case 3:
								line = String.valueOf(result);
								result = 0;		
								break;
								
							case 4:
								line = String.valueOf(result);
								result = 0;
								break;
								
							case 5:
								line = String.valueOf(result);
								result = 0;
								break;
								
							case 6:
								line = String.valueOf(result);
								result = 0;
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
					
					// statement here prints the logging information from the client and bank
					else {
						System.out.println(line);
					}
				} catch(IOException i) 
				{ 
					System.out.println(i);
					System.out.println("Closing connection"); 
					socket.close();
				}
				
				// this ensures that the switch case will loop
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
	
	// method is used to check if user input (in String) is actually an integer
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
