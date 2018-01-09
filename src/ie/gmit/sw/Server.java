package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface Server {
	public boolean authenticate(String title, String firstLine);
	public void readDocument(String title, BufferedReader br) throws IOException;
	public void processDocument(HashingMethod hashingMethod);
	public List<String> displayDocument();
	public boolean addDocument();
	public double compareSim();
	public void finish();
	public double process(String title, BufferedReader br,HashingMethod hashingMethod) throws IOException;
}
