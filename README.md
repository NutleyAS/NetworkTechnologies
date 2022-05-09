# NetworkTechnologies
This repository contains code for a Java project which was part of the Network Technologies module for a H.Dip in Software Development at GMIT.

## Project Overview
The main aim of this project was to design and write a simple network-based chat application in Java using the Java Socket API. In doing this the application had to be able to carry out a certain set of functions while also meeting certain requirements. They are as follows:

* Minimum Requirements

  - Application contains a client program that implements the client-side functionality.
  - Application contains a server program that facilitates the server-side functionality.
  - A design document that outlines the design/rationale for your programs that also includes references to external resources.

* Additional Requirements

  - In addition to the minimum requirements there is marks going for additional content. The following needed to be considered when designing the project:
    * How does the client know what address to find the server on?
    * What happens if the client can’t reach the server when it starts up?
    * What happens if the client and server connect initially but the connection is lost during the chat session?
    * Additional advanced functionality.

  * Functions

   - Server starts up and waits for socket connections on a specific port.
   - Client starts up and attempts to create a socket connection to the server.
   - If a connection is successfully formed, then the client and server should be able to facilitate a text-based chat session between Users at the client and server side, i.e.
     * Client and server should allow the User to enter text at the console (command line).
     * Messages entered by the User should be sent across the socket connection to the other application and displayed at the console.
   - Users should be able to gracefully end the chat session and close the connection by entering “\q”.

## Design

The network-based chat application was designed using a Java Project with two (2) packages and five (5) Classes. The two (2) packages can run independently on separate computers.

The application will be launched using a command line. The server and clients can run on different computers on the same network. There can be multiple clients connected to a server and they can all chat to each other. There is no functionality for privacy on the network. When connecting to the server, a User must enter their name. The server will send the User a list of Users who are currently online while every User who is currently online is notified of the arrival of a new User.

To exit the application the User enters “exit”. When a User leaves the application, all remaining Users and the server will receive a notification that they have left.

## Classes

The application is made up of five (5) Classes that are contained in two (2) separate packages.

  * Server Package (ie.gmit.dip.server)
  This package contains two classes:
    - ApplicationServer – The ApplicationServer is the driver Class within the package. The Class starts the server and then listens on a port specified by the User. When a new client connects to the Server an instance of UserThread is created to serve that client. Each connection is processed in a separate thread which allows the server to handle multiple clients at the same time.
    - UserThread – The UserThread Class is responsible for reading messages sent from the client, and broadcasts messages to all other clients on the network.

  * Other Design Information:
  Additional design information is included in comments in the source code.
  
## Compiling

For the project to launch using a command line it has to be compiled. This was done in the src folder using the “javac” command.

## Operation

Note: When operating either the ApplicationServer or ChatClient the User must ensure that this is done from the “src” directory.
  
**ApplicationServer:** When running the ApplicationServer you must specify the port number in the command line. When using my machine, I entered the following:

..src>java ie.gmit.dip.server.ApplicationServer 1234

The server starts listening on port 1234.

**ChatClient:** When running the ChatClient you must also specify the port number. When using my machine, the following steps were followed:

**Enter the following command:** ..src>java ie.gmit.dip.client.ChatClient localhost 1234 
This tells the client to connect to the server at localhost on port 1234. The following message will then display on the server console. 
“A New User Joined” The following will display on the client’s console. 
“Connected To The Chat Server”
“You Are The Only User Currently Connected/ Connected users: [User]”
  
**Enter your Username:** 
  
The command prompt will ask “Please Input Your Name:” 
Once you input your name the console will display the following “[Name]:” 
You can now start chatting with other users on the console.
  
**Exiting:**
  
If the user wants to exit the application, they must type the following into the console: 
“exit” 
When a user leaves the chat the server and other users will be made aware of this in their terminal

    
