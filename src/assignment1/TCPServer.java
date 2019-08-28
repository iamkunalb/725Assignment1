package assignment1;

import java.io.*; 
import java.net.*;

class TCPServer { 
	public static final boolean supportsGenerations = true;
	private Login loginFileHandler = new Login();
	public static int loggedIn = 0; // 0 = not logged in, 1 = logged in, 2 = supplied user id, 3 = supplied account name, 4 = password correct, but no account name
	public static int loggedInUserID = 0;
	public static String loggedInAccount = null;
	public static int fileType = 1; // 0 = Ascii, 1 = Binary, 2 = Continuous
	public static String currentDir = "/Users/kunalbhatia/Desktop/UniSem2/725Ass1/";
	private String saveDir = "/Users/kunalbhatia/eclipse-workspace/Ass1/Recieved";
	public static String requestedDir = null;
	public static String renameFile = null;
	public static String fileToSend = null;
	public static OutputStream outputStream;
	public static int storeType = 0; // 0 = no store requested, 1 = new file requested, 2 = new generation requested, 3 = overwrite file requested, 4 = append to file requested
	public static String storeName = null;
	public static long fileLength = 0;
	
	public void init() {
    	storeName = null;
		storeType = 0;
		fileLength = 0;
    }
	
    public TCPServer(int port) throws Exception{
    	String clientInput;
		String parameters;
		String command;
		String response;
		boolean connected = true;
		
		// connected socket for receiving
		ServerSocket welcomeSocket = new ServerSocket(port);
		welcomeSocket.setReuseAddress(true);
		Socket connectionSocket = welcomeSocket.accept();
		
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		PrintWriter outToClient = new PrintWriter(connectionSocket.getOutputStream(), true);
		outputStream = connectionSocket.getOutputStream();
		
		outToClient.println("COMPSYS725 Assignment 1 - KBHA192");
		
		while(connected) {
			// If we have a file to receive
			if (fileLength != 0) {
				// Create and read data into byte array
				byte[] receivedFile = new byte[(int) fileLength];
				
				for (int i=0; i<fileLength; i++) {
					receivedFile[i] = (byte) connectionSocket.getInputStream().read();
				}
				
				try {
					// Write byte array to a file
					if ((storeType == 1)) {
						FileOutputStream stream = new FileOutputStream(saveDir + "/" + storeName);
					    stream.write(receivedFile);
					    stream.close();
					} else if (storeType == 2) {
						FileOutputStream stream = new FileOutputStream(saveDir + "/" + "new_" + storeName);
					    stream.write(receivedFile);
					    stream.close();
					} else if ((storeType == 3)) {
						FileOutputStream stream = new FileOutputStream(saveDir + "/" + storeName);
					    stream.write(receivedFile);
					    stream.close();
					} else {
						FileOutputStream stream = new FileOutputStream(saveDir + "/" + storeName, true);
					    stream.write(receivedFile);
					    stream.close();
					}
				} catch (Exception e) {
					// If it fails
					init();
					outToClient.println("-Couldn't save\0");
					continue;
				}
				outToClient.println("+Saved " + storeName + "\0");
				init();
				
			} else {
				// Get first 4 characters (the command)
				clientInput = inFromClient.readLine();
				command = clientInput.substring(0, Math.min(clientInput.length(), 4));
				
				if (command.equals("DONE")) {
					connected = false;
					response = "+";
				} else if (command.equals("SEND")) {
					response = SEND.SENDCommand();
				} else if (command.equals("STOP")) {
					response = STOP.STOPCommand();	
				} else {
					// Get characters after 5th (the parameters)
					try {
						parameters = clientInput.substring(5, clientInput.length()-1);
					} catch (Exception e) {
						outToClient.println("-Unkown Command. Please Try Again!\0");
						continue;
					}
										
					// Choose a command
					if (command.equals("USER")) {
						response = USER.USERCommand(parameters);
					} 
					else if (command.equals("ACCT")) {
						response = ACCT.ACCTCommand(parameters);
					} 
					else if (command.equals("PASS")) {
						response = PASS.PASSCommand(parameters);
					}
					else if (command.equals("CDIR")) {
						response = CDIR.CDIRCommand(parameters);
					} 
					else if (command.equals("LIST")) {
						response = LIST.LISTCommand(parameters);
					} 
					else if (command.equals("TYPE")) {
						response = TYPE.TYPECommand(parameters);
					} 
					else if (command.equals("KILL")) {
						response = KILL.KILLCommand(parameters);
					} 
					else if (command.equals("NAME")) {
						response = NAME.NAMECommand(parameters);
					} 
					else if (command.equals("TOBE")) {
						response = TOBE.TOBECommand(parameters);
					} 
					else if (command.equals("RETR")) {
						response = RETR.RETRCommand(parameters);
					} 
					else if (command.equals("STOR")) {
						response = STOR.STORCommand(parameters);
					} 
					else if (command.equals("SIZE")) {
						response = SIZE.SIZECommand(parameters);
					} else {
						response = "-Unkown Command. Please Try Again!";
					}
				} 
				outToClient.println(response + "\0");
			}	
		}			
		welcomeSocket.close();
	}
    
    
} 

