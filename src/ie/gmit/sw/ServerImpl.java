package ie.gmit.sw;

import java.io.*;
import java.util.*;

/**
 * ServerImpl is an implementation of Server. This is the main server that is
 * responsible for connecting all the other classes together in one way or
 * another. This allows a client to simply use this class to do all the document
 * similarity related stuff such as parsing, processing, hashing, shingling,
 * saving to db etc.
 */
public class ServerImpl implements Server {
	// A list of the words that make up a document
	private List<String> words;
	// Compositions of classes
	private Documentor documentor;
	private Database db;
	private Shingleator shingleator;
	private Hashing hashing;
	private SimilarityCalculator sc;
	private Document d;

	/**
	 * Default Constructor creates instances of the member classes. The classes
	 * will be deleted if the the serverImpl is deleted.
	 */
	public ServerImpl() {
		documentor = new DocumentorImpl();
		db = Database.getInstance();
		shingleator = new ShingleatorImpl();
		sc = new SimilarityCalculator();
	}

	@Override
	public boolean authenticate(String title, String firstLine) {
		// Create a new document
		d = new Document();
		return true;
	}

	@Override
	public void readDocument(String title, BufferedReader br) throws IOException {

		try {
			// Set the words to the return of readDocumentor
			words = documentor.readDocument(br);
			// Set the title of the document
			d.setTitle(title);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	@Override
	public void processDocument(HashingMethod hashingMethod) {
		// Set the shingles in the hashing class by returning the delegated work
		// of shingler
		hashing = new Hashing(shingleator.shingler(words));
		// Use a ternary operator to create a new instance of MinHashHashing or
		// HashCodeHashing
		// and pass that into the hash function (Strategy pattern) and set the
		// dynamic set returned
		// as the hashes of the document
		d.setHashes(
				hashing.hash(hashingMethod == hashingMethod.MINHASH ? new MinHashHashing() : new HashCodeHashing()));
	}

	@Override
	public List<String> displayDocument() {
		// Return the words from readDocument
		return words;
	}

	@Override
	public boolean addDocument() {
		// Add the document to db
		return db.addDocument(d);
	}

	@Override
	public double compareSim() {
		// Delegate the calculation to the SimilarityCalculator by passing it a
		// new arrayList of documents
		// that is retrieved from the database class, the local document hashes
		// and a new instance of
		// jaccardalgorithm (if I had more algorithms to use i'd go pass an enum
		// parameter into comparesim
		// so that the client will choose which algorithm to use but to keep it
		// simpler I just created a fixed
		// jaccardalgorithm to be passed in.
		return sc.calculateAllDocs(new ArrayList<Document>(db.getAllDocuments()), d.getHashes(),
				new JaccardAlgorithm());
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

	@Override
	public double process(String title, BufferedReader br, HashingMethod hashingMethod) throws IOException {
		// First I need to get a list of words so I read the document and set
		// local words to the return
		readDocument(title, br);
		// Now that I have the words I can shingle them and hash them
		// accordingly
		processDocument(hashingMethod);
		// Now I can add the document to the database
		addDocument();
		// And finally compare the document to the other documents in the
		// database for a similarity result
		return compareSim();
	}

}
