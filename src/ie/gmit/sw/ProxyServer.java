package ie.gmit.sw;

import java.io.BufferedReader;
import java.util.List;

public class ProxyServer implements Server{
	private ServerImpl realServer;
	private boolean sessionActive;
	
	public ProxyServer() {
		this.realServer = new ServerImpl();
		sessionActive = false;
	}
	
	@Override
	public void authenticate() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void readDocument(String title, BufferedReader br) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void processDocument(HashingMethod hashingMethod) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> displayDocument() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addDocument() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double compareSim() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
	}
}
