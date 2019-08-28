package assignment1;
import assignment1.TCPServer;

import java.io.File;

public class KILL {
	public static String KILLCommand(String fileSpec) {
		if (TCPServer.loggedIn == 1) { 
			File path = new File(TCPServer.currentDir + "/" + fileSpec); // Local files only. Absolute paths don't work
			if (path.exists()) { // Check if file exists
				if (path.delete()) { // Attempt to delete file
					return "+" + fileSpec + " deleted";
				} else {
					return "-Not deleted because of an unknown error";
				}
			} else {
				return "-Not deleted because file doesn't exist";
			}
		} else { 
			return "-Not deleted because client is not logged in";
		}
	}
}
