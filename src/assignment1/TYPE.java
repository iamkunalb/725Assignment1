package assignment1;
import assignment1.TCPServer;

public class TYPE {
	public static String TYPECommand(String type) {
		if (TCPServer.loggedIn == 1) { // Check Login
			if (type.equals("A")) {
				TCPServer.fileType = 0; // Switch file type to ASCII
				return "+Using Ascii mode";
			} else if (type.equals("B")) {
				TCPServer.fileType = 1; // Switch file type to BINARY
				return "+Using Binary mode";
			} else if (type.equals("C")) {
				TCPServer.fileType = 2; // Switch file type to CONTINUOUS
				return "+Using Continuous mode";
			} else {
				return "-Type not valid";
			}
		} else {
			return "-Please Login";
		}
	}
}
