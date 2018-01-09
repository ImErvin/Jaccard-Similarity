package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * ProxyServer is an implementation of Server. This is the proxy server that is
 * responsible for connecting to the main server, this is used as a gateway if
 * authentication is met. This allows a client to simply use this class to use
 * the main server.
 * 
 * The methods here are just delegated to the main server - this is just used
 * as a gateway to the main server if condition is met.
 */
public class ProxyServer implements Server {
	// A composition of realServer
	private ServerImpl realServer;
	// Authentication token kind of thing
	private boolean sessionActive;

	/**
	 * Constructor creates an instance of ServerImpl.
	 */
	public ProxyServer() {
		this.realServer = new ServerImpl();
		// Sets token to false at the start.
		sessionActive = false;
	}

	@Override
	public boolean authenticate(String title, String firstLine) {
		// If the title is null or the firstLine of the document is null
		if (title.hashCode() == 0 || firstLine == null) {
			// Forbid access
			return false;
		} else {
			// Or else authenticate user and grant access
			realServer.authenticate(title, firstLine);
			sessionActive = true;
			return true;
		}
	}

	@Override
	public void readDocument(String title, BufferedReader br) throws IOException {
		if (sessionActive) {
			realServer.readDocument(title, br);
		} else {
			System.out.println("Invalid Session");
		}
	}

	@Override
	public void processDocument(HashingMethod hashingMethod) {
		if (sessionActive) {
			realServer.processDocument(hashingMethod);
		} else {
			System.out.println("Invalid Session");
		}
	}

	@Override
	public List<String> displayDocument() {
		if (sessionActive) {
			System.out.println(realServer.displayDocument());
			return realServer.displayDocument();
		} else {
			System.out.println("Invalid Session");
			return null;
		}
	}

	@Override
	public boolean addDocument() {
		if (sessionActive) {
			return realServer.addDocument();
		} else {
			System.out.println("Invalid Session");
			return false;
		}
	}

	@Override
	public double compareSim() {
		if (sessionActive) {
			return realServer.compareSim();
		} else {
			System.out.println("Invalid Session");
			return 0;
		}
	}

	@Override
	public void finish() {
		// Delete token
		sessionActive = false;
	}

	@Override
	public double process(String title, BufferedReader br, HashingMethod hashingMethod) throws IOException {
		if (sessionActive) {
			return realServer.process(title, br, hashingMethod);
		} else {
			System.out.println("Invalid Session");
			return 0;
		}
	}
}
