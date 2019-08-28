package assignment1;
import assignment1.TCPServer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SEND {
	public static String SENDCommand() {
		if (TCPServer.fileToSend != null) {
			File path = new File(TCPServer.currentDir + "/" + TCPServer.fileToSend); // Get the file
			
			System.out.println(path);
			try {
				byte[] fileContent = Files.readAllBytes(path.toPath()); // Convert it to a byte array
				TCPServer.outputStream.write(fileContent); // Write the byte array to the output stream
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "";
		} else {
			return "-No File Specified";
		}
	}
}
