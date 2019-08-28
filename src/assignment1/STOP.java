package assignment1;
import assignment1.TCPServer;
public class STOP {
	public static String STOPCommand() {
		TCPServer.fileToSend = null;
		return "+ok, RETR aborted";
	}
}
