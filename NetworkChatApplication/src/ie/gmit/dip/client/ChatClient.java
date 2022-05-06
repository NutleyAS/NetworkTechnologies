package ie.gmit.dip.client;

import java.net.*;
import java.io.*;

/**
 * 
 * @author Aron Nutley
 * @version 1.0
 * @since 2020-09
 * @references See DesignDocument.pdf
 *
 *This is the program for the ChatClient
 *Input "exit" to terminate the application
 *
 */

public class ChatClient {
	private String hostname;
	private int port;
	private String userName;

	public ChatClient(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public void execute() {
		try {
			Socket socket = new Socket(hostname, port);

			System.out.println("Connected To The Chat Server");

			new ReadThread(socket, this).start();
			new WriteThread(socket, this).start();

		} catch (UnknownHostException ex) {
			System.out.println("Server Not Found: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("I/O Error: " + ex.getMessage());
		}

	}

	void setUserName(String userName) {
		this.userName = userName;
	}

	String getUserName() {
		return this.userName;
	}

	public static void main(String[] args) {
		if (args.length < 2)
			return;

		String hostname = args[0];
		int port = Integer.parseInt(args[1]);

		ChatClient client = new ChatClient(hostname, port);
		client.execute();
	}
}



