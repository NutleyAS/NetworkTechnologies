package ie.gmit.dip.server;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * 
 * @author Aron Nutley
 * @version 1.0
 * @since 2020-09
 * @references See DesignDocument.pdf
 *
 *ApplicationServer starts listening on a specific port. When a client connects an instance of UserThread is created to serve that client.
 *Each connection is process in a separate thread.
 *
 *Press Ctrl + C to terminate the program
 */

public class ApplicationServer {
	private int portNumber;
	private Set<String> userNames = new HashSet<>(); //Set used as it doesn't allow duplication
	private Set<UserThread> userThreads = new HashSet<>(); //Two Set collections keeps track of the names and threads of connected clients.
	
	public ApplicationServer(int port) {
		this.portNumber = port;
	}
	
	public void execute() {
		try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
			
			System.out.println("Application Server Is Listening On Port" + portNumber);
			
			while(true) {
				Socket socket = serverSocket.accept();
				System.out.println("A New User Joined"); 
				
				UserThread newUser = new UserThread(socket, this);
				userThreads.add(newUser);
				newUser.start();
				
			}
			
		} catch (IOException e) {
			System.out.println("Error In The Server: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
    public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Syntax: Java ChatServer <Port-Number>");
			System.exit(0);
		}
		
		int port = Integer.parseInt(args[0]);
		
		ApplicationServer server = new ApplicationServer(port);
		server.execute();
	}
    
    void broadcast(String message, UserThread excludeUser) { //This method delivers a message from one client to all other clients
    	for (UserThread aUser : userThreads) {
    		if (aUser != excludeUser) {
    			aUser.sendMessage(message);
    		}
    	}
    }
    
    void addUserName (String userName ) {
    	userNames.add(userName);
    }
    
    void removeUser(String userName, UserThread aUser) {
    	boolean removed = userNames.remove(userName);
    	if(removed) {
    		userThreads.remove(aUser);
    		System.out.println("The User" + userName + "Has Left The Chat"); 
    	}
    }
    
    Set<String> getUserNames(){
    	return this.userNames;
    }
    
    boolean hasUsers() {
    	return !this.userNames.isEmpty();
    }
}
