package assignment1;
import assignment1.TCPServer;

import java.io.File;

public class NAME {
	public static String NAMECommand(String fileSpec) {
		if (TCPServer.loggedIn == 1) { 
			File path = new File(TCPServer.currentDir + "/" + fileSpec); // Local files only. Absolute paths don't work
			if (path.exists()) { // Check if file exists
				TCPServer.renameFile = fileSpec;
				return "+File exists";
			} else {
				TCPServer.renameFile = null;
				return "-Can't find " + fileSpec;
			}
		} else { 
			return "-Please log in";
		}
	}
}
