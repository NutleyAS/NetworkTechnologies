package ie.gmit.dip.server;

import java.io.*;
import java.net.*;

/**
 * 
 * @author Aron Nutley
 * @version 1.0
 * @since 2020-09
 * @references See DesignDocument.pdf
 *
 *This thread handles the connection for each of the connected clients. This allows the server to
 *handle multiple clients at the same time. 
 *
 */

public class UserThread extends Thread {
	private Socket socket;
	private ApplicationServer server;
	private PrintWriter writer;
	
	public UserThread(Socket socket, ApplicationServer server) {
		this.socket = socket;
		this.server = server;
	}
	
	public void run() {
		try {
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader (new InputStreamReader(input));
			
			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter (output, true);
			
			printUsers();
			
			String userName = reader.readLine();
			server.addUserName(userName);
			
			String serverMessage = "New User Connected" + userName; 
			server.broadcast(serverMessage, this);
			
			String clientMessage;
			
			do {
				clientMessage = reader.readLine();
				serverMessage = "[" + userName + "]: " + clientMessage; 
				server.broadcast(serverMessage, this);
			
		   /** Typing 'exit' indicates that the user is going to quit
			 * exits the application and informs other users.
			 *             
             * Brief wanted input "\q" to close the connection
             * This gave error "Invalid escape sequence
             * "exit" Used instead
             */
			 
				
			} while (!clientMessage.equals("exit")); 
			
			server.removeUser(userName, this);
			socket.close();
			
			serverMessage = userName + "Has Left The Chat"; 
			server.broadcast(serverMessage, this);
			
	} catch (IOException e) {
		System.out.println("Error In UserThread" + e.getMessage()); 
		e.printStackTrace ();
	}
}
	/**
	 * Sends a list of Online users to newly connected User
	 */
	
	void printUsers() {
		if (server.hasUsers()) {
			writer.println("Connected users: " + server.getUserNames());
		} else {
			writer.println("You Are The Only User Currently Connected");
		}
	}

	/**
	 * @param message sends to client
	 */
	
	void sendMessage(String message) {
		writer.println(message);
	}
}	