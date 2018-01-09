package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ProxyServer implements Server{
	private ServerImpl realServer;
	private boolean sessionActive;
	
	public ProxyServer() {
		this.realServer = new ServerImpl();
		sessionActive = false;
	}
	
	@Override
	public boolean authenticate(String title, String firstLine) {
		if(title.hashCode() == 0 || firstLine == null){
			return false;
		}else{
			realServer.authenticate(title, firstLine);
			sessionActive = true;
			return true;
		}
	}
	
	@Override
	public void readDocument(String title, BufferedReader br) throws IOException {
		if(sessionActive){
			realServer.readDocument(title, br);
		}else{
			System.out.println("Invalid Session");
		}
	}
	
	@Override
	public void processDocument(HashingMethod hashingMethod) {
		if(sessionActive){
			realServer.processDocument(hashingMethod);
		}else{
			System.out.println("Invalid Session");
		}
	}

	@Override
	public List<String> displayDocument() {
		if(sessionActive){
			System.out.println(realServer.displayDocument());
			return realServer.displayDocument();
		}else{
			System.out.println("Invalid Session");
			return null;
		}
	}

	@Override
	public boolean addDocument() {
		if(sessionActive){
			return realServer.addDocument();
		}else{
			System.out.println("Invalid Session");
			return false;
		}
	}

	@Override
	public double compareSim() {
		if(sessionActive){
			return realServer.compareSim();
		}else{
			System.out.println("Invalid Session");
			return 0;
		}
	}

	@Override
	public void finish() {
		sessionActive = false;
	}

	@Override
	public double process(String title, BufferedReader br, HashingMethod hashingMethod) throws IOException {
		if(sessionActive){
			return realServer.process(title, br, hashingMethod);
		}else{
			System.out.println("Invalid Session");
			return 0;
		}
	}
}
