package assignment1;
import assignment1.TCPServer;

import java.io.File;

public class TOBE {
	public static String TOBECommand(String fileSpec) {
		if (TCPServer.loggedIn == 1) {
			if (TCPServer.renameFile == null) {
				return "-File wasn't renamed because NAME not specified";
			}
			File path = new File(TCPServer.currentDir + "/" + TCPServer.renameFile);
			File newPath = new File(TCPServer.currentDir + "/" + fileSpec);
			if (path.renameTo(newPath)) {
				return "+" + TCPServer.renameFile + " renamed to " + fileSpec;
			}
			return "-File wasn't renamed";
		} else { 
			return "-Please log in";
		}
	}
}
