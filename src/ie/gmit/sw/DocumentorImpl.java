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
		// Set a temp string to blank
		String line = "";
		try {
			// While there are lines to read, read each line
			while ((line = br.readLine()) != null) {
				// Create a mini array of words split up between spaces
				String[] split = line.split(" ");
				// Add each split word to the arraylist of words
				for (int i = 0; i < split.length; i++) {
					words.add(split[i]);
				}
			}
		} finally {
			// Close the buffered reader
			br.close();
		}

		// return the arraylist of words
		return words;
	}
}
