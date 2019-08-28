package assignment1;

public class Test {

	public static void main(String argv[]) {
		int port = 115;
		
		// Start server
		Thread serverThread = new Thread(){
			public void run(){
				System.out.println("Ready!");
				try {			
					TCPServer myServer = new TCPServer(port);
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		};
		serverThread.start();
				
		// Start client
		try {
			TCPClient myClient = new TCPClient(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("\nSFTP Protocol Ended");
	}
}
