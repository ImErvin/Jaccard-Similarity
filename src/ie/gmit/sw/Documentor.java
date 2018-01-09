package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Documentor Interface describes a contract for all documentor objects.
 * Documentor objects are those used to work with Files. This enhances
 * abstraction and Single Responsibility Rule.
 */
public interface Documentor {
	/**
	 * This method is intended to process the contents of the BufferedReader
	 * parameter and produce a list of words that doesn't include " " (spaces).
	 * 
	 * @param br
	 *            The BufferedReader containing the document contents
	 * @return A List Collection of words
	 * @throws IOException
	 *             for File Handling
	 */
	public List<String> readDocument(BufferedReader br) throws IOException;
}
