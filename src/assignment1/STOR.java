package assignment1;
import assignment1.TCPServer;
import java.io.File;

public class STOR {
	public static String STORCommand(String parameters) {
		String filePath;
		String type;
		boolean exists;
		
		if (TCPServer.loggedIn != 1) {
			return "-Please login";
		}
		
		try { // Get the separate parameters
			type = parameters.substring(0, 3);
			filePath = parameters.substring(4, parameters.length());
		} catch (Exception e) {
			return "-Invalid parameters";
		}
		
		File path = new File(TCPServer.currentDir + "/../Recieved/" + filePath); // Local files only. Absolute paths don't work
		System.out.println(path);
		exists = path.exists();
		// NEW store
		if (type.equals("NEW")) { 
			if (exists) {
				if (TCPServer.supportsGenerations) {
					TCPServer.storeName = filePath;
					TCPServer.storeType = 2;
					return "+File exists, will create new generation of file";
				} else {
					TCPServer.storeType = 0;
					return "-File exists, but system doesn't support generations";
				}
			} else {
				TCPServer.storeType = 1;
				TCPServer.storeName = filePath;
				return "+File does not exist, will create new file";
			}
		// OLD store
		} else if (type.equals("OLD")) { 
			TCPServer.storeName = filePath;
			if (exists) {
				TCPServer.storeType = 3;
				return "+Will write over old file";
			} else {
				TCPServer.storeType = 1;
				return "+Will create new file";
			}
		// APP store
		} else if (type.equals("APP")) { 
			TCPServer.storeName = filePath;
			if (exists) {
				TCPServer.storeType = 4;
				return "+Will append to file";
			} else {
				TCPServer.storeType = 1;
				return "+Will create file";
			}
		} else {
			return "-Invalid parameters";
		}
	}
}
