package assignment1;
import java.io.File;
import java.util.Date;

import assignment1.TCPServer;

public class LIST {
	public static String LISTCommand(String parameters) {
		String format;
		String path;
		
		if (TCPServer.loggedIn == 1) { // Check the login
			try {
				format = parameters.substring(0, 1); // Split into verbose flag and path
				if (parameters.length() < 3) {
					path = TCPServer.currentDir;
				} else {
					path = parameters.substring(2, parameters.length());
				}
			} catch (Exception e) {
				return "-Format or directory not valid";
			}
			
			if (format.equals("F")) { // Call listDir()
				return listDir(path, false);
			} else if (format.equals("V")) {
				return listDir(path, true);
			} else {
				return "-Format not valid";
			}
		} else {
			return "-Please Login";
		}
	}
	
	public static String listDir(String pathString, boolean verbose) {
		String output = "";
		
		File path = new File(pathString); // Create a path
		try {
			File[] files = path.listFiles(); // List all files
			output = output + "+" + pathString + "\r\n"; // Append directory name
			for (File file : files) {
				output = output + file.getName(); // Append file/folder names
				if (verbose) {
					if (file.isFile()) { // Append if it is a file or folder
						output = output + "\t\t File"; 
					} else {
						output = output + "\t\t Folder";
					}
					output = output + "\t\t Size: " + file.length() +  " B \t\t Last Modified: " + new Date(file.lastModified()); // Append size and date
				}
				output = output + "\r\n";
			}
		} catch (Exception e) {
			if (e.getMessage() == null) { // Path doesn't exist
				return "-Directory path doesn't exist";
			}
			return "-" + e.getMessage(); // Some other error
		}
		
		return output;
	}
}
