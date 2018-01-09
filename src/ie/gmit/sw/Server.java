package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface Server {
	public boolean start(String title, String firstLine);
	public void readDocument(String title, BufferedReader br) throws IOException;
	public void processDocument(HashingMethod hashingMethod);
	public List<String> displayDocument();
	public boolean addDocument();
	public double compareSim();
	public void finish();
}
