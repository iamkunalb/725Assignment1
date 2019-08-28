package assignment1;
import assignment1.TCPServer;
import java.io.File;

public class SIZE {
	public static String SIZECommand(String sizeString) {
		long size = 0;
		
		if (TCPServer.loggedIn != 1) {
			return "-Please login";
		}
		
		if (TCPServer.storeType == 0) {
			return "-Please specify filename and store type";
		}
		
		try { // Get the size
			size = Long.parseLong(sizeString);
		} catch (Exception e) {
			// Any errors
			TCPServer.storeType = 0;
			TCPServer.storeName = null;
			return "-Invalid parameters";
		}
		
		// Check free space
		File file = new File(TCPServer.currentDir);
		if (size > file.getFreeSpace()) {
			TCPServer.storeType = 0;
			TCPServer.storeName = null;
			return "-Not enough room, don't send it";
		} else {
			TCPServer.fileLength = size;
			return "+ok, waiting for file";
		}
    } 
}
