package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Server is an interface that states what a server should do. This is written
 * specific for the server this application uses. The server acts as a facade(?)
 * in the way it fully composes each of the other interfaces and is used as a
 * single place to do all the servery business. This seemed like it was the best
 * idea because there are many classes and refrain from making references to
 * each other within the classes, I decided to use this class to connect
 * everything.
 */
public interface Server {
	/**
	 * Used as error handling that returns true if the title and firstLine of
	 * the document exists. This is used for the proxy mainly to ensure that the
	 * server does not start creating documents if there is no valid input.
	 * 
	 * @param title
	 *            The title of the document
	 * @param firstLine
	 *            a BufferedReader of the first line of the document
	 * @return true if title and firstLine exist, false if title and firstLine
	 *         don't exist
	 */
	public boolean authenticate(String title, String firstLine);

	/**
	 * Is the class responsible for delegating work to the Documentor to parse
	 * the file.
	 * 
	 * @param title
	 *            Used to add a title to document to be processed and stored
	 * @param br
	 *            Used to add content to the document to be processed and stored
	 * @throws IOException
	 *             File operations
	 */
	public void readDocument(String title, BufferedReader br) throws IOException;

	/**
	 * Is the class responsible for delegating work to the Shingleator and
	 * Hashing class to shingle and hash the document.
	 * 
	 * @param hashingMethod
	 *            An enum to state which hashing method to use.
	 */
	public void processDocument(HashingMethod hashingMethod);

	/**
	 * Is the class responsible for returning the set of words that was
	 * processed by readDocument
	 * 
	 * @return The list of words
	 */
	public List<String> displayDocument();

	/**
	 * Is the class responsible for delegating the work to the database to add a
	 * document.
	 * 
	 * @return True if document was added successfully and false if
	 *         unsuccessfully.
	 */
	public boolean addDocument();

	/**
	 * Is the class responsible for delegating the work to the
	 * SimilarityCalculator to calculate the similarity of the document parsed
	 * in processDocument() against all other documents.
	 * 
	 * @return A value of 0-1 * 100 to represent the precentile of similarity.
	 */
	public double compareSim();

	/**
	 * A method of stopping the server mainly used for proxy to destroy a
	 * session.
	 */
	public void finish();

	/**
	 * This method is responsible for calling all the other messages in order,
	 * this is to allow the client to simply call Process instead of all of the
	 * commands above (except authenticate). Seemed like a better way of doing
	 * things plus it makes this server look more like a facade.
	 * 
	 * @param title
	 *            Title of the document
	 * @param br
	 *            BufferedReader containing the contents of the document
	 * @param hashingMethod
	 *            Enum to distinguish what hashingMethod to use
	 * @return A value of 0-1 * 100 to represent the precentile of similarity.
	 * @throws IOException
	 *             For File operations
	 */
	public double process(String title, BufferedReader br, HashingMethod hashingMethod) throws IOException;
}
