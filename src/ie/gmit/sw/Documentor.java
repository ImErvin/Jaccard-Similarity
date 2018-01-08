package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface Documentor {
	public List<String> readDocument(BufferedReader br) throws IOException;
}
