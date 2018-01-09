package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Documentor. The sole purpose of this class is to process
 * the document being uploaded. The intention is to decouple classes and uphold
 * the single responsibility rule. DocumentorImpl is-a Documentor (Inheritance).
 */
public class DocumentorImpl implements Documentor {
	// ArrayList of strings that make up the words of the document
	private List<String> words = new ArrayList<String>();

	/**
	 * Default Constructor
	 */
	public DocumentorImpl() {

	}

	@Override
	public List<String> readDocument(BufferedReader br) throws IOException {
		String line = "";
		try {
			while ((line = br.readLine()) != null) {

				String[] split = line.split(" ");
				for (int i = 0; i < split.length; i++) {
					words.add(split[i]);
				}
			}
		} finally {
			br.close();
		}

		return words;
	}
}
