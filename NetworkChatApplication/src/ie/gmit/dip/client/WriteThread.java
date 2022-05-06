package ie.gmit.dip.client;

import java.io.*;
import java.net.*;

/**
 * 
 * @author Aron Nutley
 * @version 1.0
 * @since 2020-09
 * @references See DesignDocument.pdf
 *
 *Class is responsible for reading users input and sending it to the server
 *Runs an infinite loop until the User typer "exit" to disconnect
 *
 */

public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private ChatClient client;
 
    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;
 
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error Getting Output Stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public void run() {
 
        Console console = System.console();
 
        String userName = console.readLine("\nPlease Input Your Name: ");
        client.setUserName(userName);
        writer.println(userName);
 
        String text;
 
        do {
            text = console.readLine("[" + userName + "]: ");
            writer.println(text);
 
     	   /** Typing 'exit' indicates that the user is going to quit
     			 * exits the application and informs other users.
     			 *             
                  * Brief wanted input "\q" to close the connection
                  * This gave error "Invalid escape sequence
                  * "exit" Used instead
                  */
            
        } while (!text.equals("exit")); 
 
        try {
            socket.close();
        } catch (IOException ex) {
 
            System.out.println("Error Writing To Server: " + ex.getMessage());
        }
    }
}
