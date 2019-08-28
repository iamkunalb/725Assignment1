package assignment1;
import assignment1.TCPServer;
import java.io.File;

public class RETR {
	public static String RETRCommand(String fileSpec) {
		if (TCPServer.loggedIn == 1) { 
			File path = new File(TCPServer.currentDir + "/" + fileSpec); // Local files only. Absolute paths don't work
			if (path.exists()) { // Check if file exists
				TCPServer.fileToSend = fileSpec;
				return Integer.toString((int) path.length());
			} else {
				return "-File doesn't exist";
			}
		} else { 
			return "-Please log in";
		}
	}
}
