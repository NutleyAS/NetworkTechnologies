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
 *Class is responsible for reading servers input and printing it to the console
 *Runs an infinite loop until the client disconnects from the server
 *
 */

public class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private ChatClient client;
 
    public ReadThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;
 
        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException e) {
            System.out.println("Error Getting Input Stream: " + e.getMessage());
            e.printStackTrace();
        }
    }
 
    public void run() {
        while (true) {
            try {
                String response = reader.readLine();
                System.out.println("\n" + response);
 
                
                if (client.getUserName() != null) {
                    System.out.print("[" + client.getUserName() + "]: "); // prints the UserName after displaying the server's message
                }
            } catch (IOException e) {
                System.out.println("Error Reading From The Server: " + e.getMessage());
                e.printStackTrace();
                break;
            }
        }
    }
}